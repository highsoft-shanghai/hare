export class GrantedAuthorities {
  public readonly authorities: ReadonlySet<string>;

  public constructor(...authorities: string[]) {
    this.authorities = new Set(authorities);
  }
}

export function grantedAuthorities(...authorities: string[]): GrantedAuthorities {
  return new GrantedAuthorities('authenticated', ...authorities);
}
