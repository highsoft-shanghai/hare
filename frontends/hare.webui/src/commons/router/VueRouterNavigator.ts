import {Navigator} from 'commons/router/Navigator';
import {RouteLocationRaw, Router} from 'vue-router';
import {Authorizer} from 'commons/security/Authorizer';
import {requiredAuthoritiesFromRoute} from 'commons/router/SecurityUtils';

export class VueRouterNavigator extends Navigator {
  private readonly router: Router;
  private readonly authorizer: Authorizer;

  public constructor(router: Router, authorizer: Authorizer) {
    super();
    this.router = router;
    this.authorizer = authorizer;
    this.router.beforeEach(async (to, from) => {
      const authorization = this.authorizer.authorize(requiredAuthoritiesFromRoute(to));
      return authorization.redirect as RouteLocationRaw;
    });
  }

  public async goto(page: string): Promise<void> {
    await this.router.replace(this.router.hasRoute(page) ? {name: page} : '/404');
  }
}
