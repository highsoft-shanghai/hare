import {GrantedAuthorities} from 'commons/context/GrantedAuthorities';

export class RequiredAuthorities {
  public readonly authorities: ReadonlyArray<string>;

  public constructor(authorities: string[]) {
    this.authorities = authorities;
  }

  public matches(grantedAuthorities: GrantedAuthorities): boolean {
    return this.authorities.some(authority => grantedAuthorities.contains(authority));
  }
}

export function requiredAuthorities(...authorities: string[]): RequiredAuthorities {
  return new RequiredAuthorities(authorities);
}
