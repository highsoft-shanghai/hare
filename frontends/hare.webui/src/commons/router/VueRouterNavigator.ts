import {Navigator} from 'commons/router/Navigator';
import {Router} from 'vue-router';

export class VueRouterNavigator extends Navigator {
  private readonly router: Router;

  public constructor(router: Router) {
    super();
    this.router = router;
    this.router.beforeEach(async (to, from, next) => {
      if (to.meta['allowAnonymous']) return next();
      return next({name: 'route.login'});
    });
  }

  public async goto(page: string): Promise<void> {
    await this.router.replace(this.router.hasRoute(page) ? {name: page} : '/404');
  }
}
