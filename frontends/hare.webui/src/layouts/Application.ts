import { StartMenu } from 'layouts/StartMenu';
import { i18n } from 'src/commons/i18n/I18nMessage';
import { AppFullscreen, Dialog } from 'quasar';
import { PageModel } from "commons/page/PageModel";
import { PageHost } from "commons/page/PageHost";

export class Application {
  public readonly title = i18n('title.application');
  public readonly startMenu = new StartMenu();
  public readonly pageHost: PageHost;

  public constructor(home: PageModel) {
    this.pageHost = new PageHost(home);
  }

  public toggleMiniMode(): void {
    Dialog.create({ title: '收起标题栏', message: '该功能正在建设中，敬请期待……', noBackdropDismiss: true });
  }

  public toggleViewList(): void {
    Dialog.create({ title: '显示视图列表', message: '该功能正在建设中，敬请期待……', noBackdropDismiss: true });
  }

  public async toggleFullscreen(): Promise<void> {
    await AppFullscreen.toggle();
  }

  public toggleProfileMenu(): void {
    Dialog.create({ title: '显示/隐藏用户菜单', message: '该功能正在建设中，敬请期待……', noBackdropDismiss: true });
  }

  public get fullscreen(): boolean {
    return AppFullscreen.isActive;
  }
}
