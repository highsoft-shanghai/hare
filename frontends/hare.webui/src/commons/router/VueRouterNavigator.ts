import {Navigator} from 'commons/router/Navigator';
import {RouteLocationNormalized, Router} from 'vue-router';
import {Authorizer} from 'commons/security/Authorizer';
import {requiredAuthoritiesFromRoute} from 'commons/router/SecurityUtils';
import {RedirectType} from 'commons/security/AuthorizationResult';

export class VueRouterNavigator extends Navigator {
  private readonly router: Router;
  private readonly authorizer: Authorizer;

  public constructor(router: Router, authorizer: Authorizer) {
    super();
    this.router = router;
    this.authorizer = authorizer;
    this.router.beforeEach(async to => this.authorize(to));
  }

  public async goto(page: string): Promise<void> {
    await this.router.replace(this.router.hasRoute(page) ? {name: page} : '/404');
  }

  private authorize(to: RouteLocationNormalized): RedirectType {
    return this.authorizer.authorize(requiredAuthoritiesFromRoute(to)).redirect;
  }
}
