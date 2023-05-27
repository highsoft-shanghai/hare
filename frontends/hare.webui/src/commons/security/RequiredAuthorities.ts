export class RequiredAuthorities {
  public constructor(authorities: string[]) {

  }
}

export function requiredAuthorities(...authorities: string[]): RequiredAuthorities {
  return new RequiredAuthorities(authorities);
}
