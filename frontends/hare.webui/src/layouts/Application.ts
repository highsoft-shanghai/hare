import {reactive} from 'vue';
import {StartMenuModel} from 'layouts/StartMenuModel';
import {i18n} from 'src/commons/i18n/i18n';
import {AppFullscreen, Dialog} from 'quasar';

export class Application {
  public readonly title = i18n('title.application');
  public readonly startMenu = new StartMenuModel();

  public toggleMiniMode(): void {
    Dialog.create({title: '收起标题栏', message: '该功能正在建设中，敬请期待……', noBackdropDismiss: true});
  }

  public toggleViewList(): void {
    Dialog.create({title: '显示视图列表', message: '该功能正在建设中，敬请期待……', noBackdropDismiss: true});
  }

  public async toggleFullscreen(): Promise<void> {
    await AppFullscreen.toggle();
  }

  public toggleProfileMenu(): void {
    Dialog.create({title: '显示/隐藏用户菜单', message: '该功能正在建设中，敬请期待……', noBackdropDismiss: true});
  }

  public get fullscreen(): boolean {
    return AppFullscreen.isActive;
  }
}

export const application = reactive(new Application());
