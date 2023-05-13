import {describe, expect, test} from '@jest/globals';
import {StartMenu} from 'layouts/StartMenu';

describe('StartMenuModel', () => {
  test('should be invisible in initial', () => {
    const model = new StartMenu();
    expect(model.visible).toBeFalsy();
  });

  test('should be visible when toggle visible in invisible state', () => {
    const model = new StartMenu();
    model.toggleVisible();
    expect(model.visible).toBeTruthy();
  });

  test('should be invisible when toggle visible in visible state', () => {
    const model = new StartMenu();
    model.toggleVisible();
    model.toggleVisible();
    expect(model.visible).toBeFalsy();
  });

  test('should be invisible when close in visible state', () => {
    const model = new StartMenu();
    model.toggleVisible();
    model.close();
    expect(model.visible).toBeFalsy();
  });
});
