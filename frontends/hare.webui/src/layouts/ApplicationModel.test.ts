import {describe, expect, it, jest} from '@jest/globals';
import {setupComponentTest} from 'app/test/utils/component';
import {ApplicationModel} from 'layouts/ApplicationModel';
import {AppFullscreen, Dialog, DialogChainObject} from 'quasar';

setupComponentTest();

const mockAppFullscreenToggle = async () => {
  AppFullscreen.isActive = true;
};

describe('ApplicationModel', () => {
  it('should provide correct application title', () => {
    const model = new ApplicationModel();
    expect(model.title.toString()).toBe('瀚诚企业应用框架');
  });

  it('should be able to toggle application fullscreen', async () => {
    AppFullscreen.toggle = jest.fn(mockAppFullscreenToggle);
    const model = new ApplicationModel();
    await model.toggleFullscreen();
    expect(model.fullscreen).toBeTruthy();
  });

  it('should show under-construction message when toggle mini mode', () => {
    const model = new ApplicationModel();
    Dialog.create = jest.fn(() => ({} as DialogChainObject));
    model.toggleMiniMode();
    expect(Dialog.create).toBeCalledWith({'message': '该功能正在建设中，敬请期待……', 'noBackdropDismiss': true, 'title': '收起标题栏'});
  });

  it('should show under-construction message when toggle view list', () => {
    const model = new ApplicationModel();
    Dialog.create = jest.fn(() => ({} as DialogChainObject));
    model.toggleViewList();
    expect(Dialog.create).toBeCalledWith({'message': '该功能正在建设中，敬请期待……', 'noBackdropDismiss': true, 'title': '显示视图列表'});
  });

  it('should show under-construction message when toggle profile menu', () => {
    const model = new ApplicationModel();
    Dialog.create = jest.fn(() => ({} as DialogChainObject));
    model.toggleProfileMenu();
    expect(Dialog.create).toBeCalledWith({'message': '该功能正在建设中，敬请期待……', 'noBackdropDismiss': true, 'title': '显示/隐藏用户菜单'});
  });
});
