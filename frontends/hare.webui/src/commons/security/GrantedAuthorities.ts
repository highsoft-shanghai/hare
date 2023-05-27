export class GrantedAuthorities {
  public constructor(authorities: string[]) {

  }
}

export function grantedAuthorities(...authorities: string[]): GrantedAuthorities {
  return new GrantedAuthorities(authorities);
}
