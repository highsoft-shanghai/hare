export type RedirectType = string | undefined | { name: string | symbol | null | undefined };

export class AuthorizationResult {
  public readonly approved: boolean = false;
  public readonly redirect: RedirectType;

  public constructor(approved: boolean, redirect?: RedirectType) {
    this.approved = approved;
    this.redirect = redirect;
  }
}

export function success(): AuthorizationResult {
  return new AuthorizationResult(true);
}

export function forbidden(): AuthorizationResult {
  return new AuthorizationResult(false);
}

export function redirectToLogin(): AuthorizationResult {
  return new AuthorizationResult(false, {name: 'route.login'});
}
