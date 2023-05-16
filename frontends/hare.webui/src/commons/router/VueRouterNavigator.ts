import {Navigator} from 'commons/router/Navigator';
import {Router} from 'vue-router';

export class VueRouterNavigator implements Navigator {
  private readonly router: Router;

  public constructor(router: Router) {
    this.router = router;
  }

  public async goto(page: string): Promise<void> {
    await this.router.replace({name: page});
  }
}
