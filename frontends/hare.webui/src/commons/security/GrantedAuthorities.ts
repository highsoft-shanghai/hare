import {Authorities} from 'commons/security/Authorities';

export class GrantedAuthorities {
  public static readonly ANONYMOUS = new GrantedAuthorities(Authorities.ANONYMOUS);
  public readonly authorities: ReadonlySet<string>;

  public constructor(...authorities: string[]) {
    this.authorities = new Set(authorities);
  }

  public get authenticated(): boolean {
    return this.authorities.has(Authorities.AUTHENTICATED);
  }

  public get anonymous(): boolean {
    return this.authorities.has(Authorities.ANONYMOUS);
  }
}

export function grantedAuthorities(...authorities: string[]): GrantedAuthorities {
  return new GrantedAuthorities(Authorities.AUTHENTICATED, ...authorities);
}
