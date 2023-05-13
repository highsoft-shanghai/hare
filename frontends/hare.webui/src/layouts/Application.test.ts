import { beforeEach, describe, expect, it, jest } from '@jest/globals';
import { setupComponentTest } from 'app/test/utils/component';
import { Application } from 'layouts/Application';
import { AppFullscreen, Dialog, DialogChainObject } from 'quasar';
import { PageModel } from "commons/page/PageModel";
import { pagekey } from "commons/page/PageKey";
import { mock } from "jest-mock-extended";
import { Culture } from "commons/i18n/Culture";

setupComponentTest();

const mockAppFullscreenToggle = async () => {
  AppFullscreen.isActive = true;
};

describe('Application', () => {
  let application: Application;
  let culture: Culture;

  beforeEach(() => {
    culture = mock<Culture>();
    application = new Application(culture, new PageModel(pagekey('router.home')));
  });

  it('should provide correct application title', () => {
    expect(application.title.toString()).toBe('瀚诚企业应用框架');
  });

  it('should be able to toggle application fullscreen', async () => {
    AppFullscreen.toggle = jest.fn(mockAppFullscreenToggle);
    await application.toggleFullscreen();
    expect(application.fullscreen).toBeTruthy();
  });

  it('should show under-construction message when toggle mini mode', () => {
    Dialog.create = jest.fn(() => ({} as DialogChainObject));
    application.toggleMiniMode();
    expect(Dialog.create).toBeCalledWith({ 'message': '该功能正在建设中，敬请期待……', 'noBackdropDismiss': true, 'title': '收起标题栏' });
  });

  it('should show under-construction message when toggle view list', () => {
    Dialog.create = jest.fn(() => ({} as DialogChainObject));
    application.toggleViewList();
    expect(Dialog.create).toBeCalledWith({ 'message': '该功能正在建设中，敬请期待……', 'noBackdropDismiss': true, 'title': '显示视图列表' });
  });

  it('should show under-construction message when toggle profile menu', () => {
    Dialog.create = jest.fn(() => ({} as DialogChainObject));
    application.toggleProfileMenu();
    expect(Dialog.create).toBeCalledWith({ 'message': '该功能正在建设中，敬请期待……', 'noBackdropDismiss': true, 'title': '显示/隐藏用户菜单' });
  });

  it('should be able to hold culture', () => {
    expect(application.culture).toBe(culture);
  });
});
