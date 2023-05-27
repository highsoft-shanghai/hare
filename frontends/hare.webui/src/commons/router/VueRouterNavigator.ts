import {Navigator} from 'commons/router/Navigator';
import {Router} from 'vue-router';
import {Authorizer} from 'commons/security/Authorizer';
import {requiredAuthoritiesFromRoute} from 'commons/router/SecurityUtils';

export class VueRouterNavigator extends Navigator {
  private readonly router: Router;
  private readonly authorizer: Authorizer;

  public constructor(router: Router, authorizer: Authorizer) {
    super();
    this.router = router;
    this.authorizer = authorizer;
    this.router.beforeEach(async (to, from, next) => {
      const authorization = this.authorizer.authorize(requiredAuthoritiesFromRoute(to));
      if (authorization.approved) return next();
      return next({name: 'route.login'});
    });
  }

  public async goto(page: string): Promise<void> {
    await this.router.replace(this.router.hasRoute(page) ? {name: page} : '/404');
  }
}
