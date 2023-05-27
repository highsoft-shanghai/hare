import {Navigator} from 'commons/router/Navigator';
import {RouteLocationNormalized, Router} from 'vue-router';
import {Context} from 'commons/context/Context';
import {requiredAuthoritiesFromRoute} from 'commons/router/ContextUtils';
import {RedirectType} from 'commons/context/AuthorizationResult';

export class VueRouterNavigator extends Navigator {
  private readonly router: Router;
  private readonly context: Context;

  public constructor(router: Router, context: Context) {
    super();
    this.router = router;
    this.context = context;
    this.router.beforeEach(async to => this.authorize(to));
  }

  public async goto(page: string): Promise<void> {
    await this.router.replace(this.router.hasRoute(page) ? {name: page} : '/404');
  }

  private authorize(to: RouteLocationNormalized): RedirectType {
    return this.context.authorize(requiredAuthoritiesFromRoute(to)).redirect;
  }
}
