export class RequiredAuthorities {
  public readonly authorities: ReadonlyArray<string>;

  public constructor(authorities: string[]) {
    this.authorities = authorities;
  }
}

export function requiredAuthorities(...authorities: string[]): RequiredAuthorities {
  return new RequiredAuthorities(authorities);
}
