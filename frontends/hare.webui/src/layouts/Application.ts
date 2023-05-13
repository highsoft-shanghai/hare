import { StartMenu } from 'layouts/StartMenu';
import { i18n } from 'commons/i18n/i18n';
import { AppFullscreen, Dialog } from 'quasar';
import { PageModel } from 'commons/page/PageModel';
import { PageHost } from 'commons/page/PageHost';
import { Culture } from 'commons/i18n/Culture';

export class Application {
  public readonly title = i18n('title.application');
  public readonly startMenu = new StartMenu();
  public readonly pageHost: PageHost;
  public readonly culture: Culture;

  public constructor(culture: Culture, home: PageModel) {
    this.culture = culture;
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
