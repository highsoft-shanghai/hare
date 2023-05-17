import {describe, expect, it} from '@jest/globals';
import {StartMenu} from 'layouts/main/StartMenu';
import {globals, resettableGlobals} from 'commons/global/globals';
import {mock} from 'jest-mock-extended';
import {Navigator} from 'commons/router/Navigator';

describe('StartMenuModel', () => {
  it('should be invisible in initial', () => {
    const model = new StartMenu();
    expect(model.visible).toBeFalsy();
  });

  it('should be visible when toggle visible in invisible state', () => {
    const model = new StartMenu();
    model.toggleVisible();
    expect(model.visible).toBeTruthy();
  });

  it('should be invisible when toggle visible in visible state', () => {
    const model = new StartMenu();
    model.toggleVisible();
    model.toggleVisible();
    expect(model.visible).toBeFalsy();
  });

  it('should be invisible when close in visible state', () => {
    const model = new StartMenu();
    model.toggleVisible();
    model.close();
    expect(model.visible).toBeFalsy();
  });

  it('should be able to route to specified route', async () => {
    resettableGlobals.resetNavigator(mock<Navigator>());
    const model = new StartMenu();
    model.toggleVisible();
    await model.goto('page.home');
    expect(model.visible).toBeFalsy();
    expect(globals.navigator.goto).toBeCalledWith('page.home');
  });
});
