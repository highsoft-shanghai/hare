import {RequiredAuthorities} from 'commons/context/RequiredAuthorities';
import {AuthorizationResult, forbidden, redirectToLogin, success} from 'commons/context/AuthorizationResult';
import {grantedAuthorities, GrantedAuthorities} from 'commons/context/GrantedAuthorities';
import {ContextApi} from 'commons/context/ContextApi';
import {string} from 'commons/payload/StringType';
import {array} from 'commons/payload/ArrayType';

export class Context {
  private api: ContextApi;
  private _accessToken?: string;
  private grantedAuthorities: GrantedAuthorities;

  public constructor(api: ContextApi) {
    this.api = api;
    this.grantedAuthorities = GrantedAuthorities.ANONYMOUS;
  }

  public authorize(requiredAuthorities: RequiredAuthorities): AuthorizationResult {
    if (requiredAuthorities.matches(this.grantedAuthorities)) return success();
    if (this.grantedAuthorities.anonymous) return redirectToLogin();
    return forbidden();
  }

  public async reset(accessToken: string): Promise<void> {
    this._accessToken = accessToken;
    const authorities = (await this.api.get(this._accessToken)).get('grantedAuthorities').as(array()).map(x => x.as(string()));
    this.grantedAuthorities = grantedAuthorities(...authorities);
  }

  public clear(): void {
    this.grantedAuthorities = GrantedAuthorities.ANONYMOUS;
  }

  public get accessToken(): string | undefined {
    return this._accessToken;
  }
}
