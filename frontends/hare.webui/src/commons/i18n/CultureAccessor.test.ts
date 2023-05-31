import {describe, expect, it} from '@jest/globals';
import {resettableGlobals} from 'commons/global/globals';
import {anyArray, mock} from 'jest-mock-extended';
import {culture} from 'commons/i18n/CultureAccessor';
import {VueCulture} from 'commons/i18n/VueCulture';

describe('CultureAccessor', () => {
  it('should delegate actions to global culture', () => {
    let mockCulture = mock<VueCulture>();
    resettableGlobals.resetCulture(mockCulture);
    mockCulture.localize.calledWith('code', anyArray()).mockReturnValue('message from mock culture');
    expect(culture.localize('code', 'args')).toBe('message from mock culture');
    expect(mockCulture.localize).toBeCalledWith('code', ['args']);
  });
});
