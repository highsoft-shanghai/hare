export class AuthorizationResult {
  public readonly approved: boolean = false;

  public constructor(approved: boolean) {
    this.approved = approved;
  }
}

export function success(): AuthorizationResult {
  return new AuthorizationResult(true);
}

export function forbidden(): AuthorizationResult {
  return new AuthorizationResult(false);
}
