import { describe, expect, it } from '@jest/globals';
import { resettableGlobals } from "commons/global/globals";
import { anyArray, mockDeep } from "jest-mock-extended";
import { Application } from "commons/application/Application";
import { culture } from "commons/i18n/CultureAccessor";

describe('CultureAccessor', () => {
  it('should delegate actions to global culture', () => {
    let mockApplication = mockDeep<Application>();
    resettableGlobals.resetApplication(mockApplication);
    mockApplication.culture.localize.calledWith('code', anyArray()).mockReturnValue('message from mock culture');
    expect(culture.localize('code', 'args')).toBe('message from mock culture');
    expect(mockApplication.culture.localize).toBeCalledWith('code', ['args']);
  });
});
