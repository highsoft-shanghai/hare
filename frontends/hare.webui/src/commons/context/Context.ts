import {RequiredAuthorities} from 'commons/context/RequiredAuthorities';
import {AuthorizationResult, forbidden, redirectToLogin, success} from 'commons/context/AuthorizationResult';
import {grantedAuthorities, GrantedAuthorities} from 'commons/context/GrantedAuthorities';
import {ContextApi} from 'commons/context/ContextApi';
import {string} from 'commons/payload/StringType';
import {array} from 'commons/payload/ArrayType';
import {globals} from 'commons/global/globals';

export class Context {
  private api: ContextApi;
  private _accessToken?: string | null;
  private grantedAuthorities: GrantedAuthorities;

  public constructor(api: ContextApi) {
    this.api = api;
    this.grantedAuthorities = GrantedAuthorities.ANONYMOUS;
  }

  public async restore(): Promise<void> {
    this._accessToken = globals.storage.get('accessToken');
    await this.reload();
  }

  private async reload(): Promise<void> {
    await (this.accessToken ? this.loadAuthenticated() : this.loadAnonymous());
  }

  public async authorize(requiredAuthorities: RequiredAuthorities): Promise<AuthorizationResult> {
    if (requiredAuthorities.matches(this.grantedAuthorities)) return success();
    if (this.grantedAuthorities.anonymous) return redirectToLogin();
    return forbidden();
  }

  public async reset(accessToken: string): Promise<void> {
    this._accessToken = accessToken;
    await this.reload();
    globals.storage.set('accessToken', this._accessToken);
  }

  public clear(): void {
    this.grantedAuthorities = GrantedAuthorities.ANONYMOUS;
  }

  public get accessToken(): string | undefined | null {
    return this._accessToken;
  }

  private async loadAuthenticated(): Promise<void> {
    const payload = await this.api.get();
    this.grantedAuthorities = grantedAuthorities(...(payload.get('authorities').as(array()).map(x => x.as(string()))));
  }

  private async loadAnonymous(): Promise<void> {
    this.grantedAuthorities = GrantedAuthorities.ANONYMOUS;
  }
}
