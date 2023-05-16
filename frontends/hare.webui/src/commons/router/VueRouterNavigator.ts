import {Navigator} from 'commons/router/Navigator';
import {Router} from 'vue-router';

export class VueRouterNavigator extends Navigator {
  private readonly router: Router;

  public constructor(router: Router) {
    super();
    this.router = router;
  }

  public async goto(page: string): Promise<void> {
    await this.router.replace({name: page});
  }
}
