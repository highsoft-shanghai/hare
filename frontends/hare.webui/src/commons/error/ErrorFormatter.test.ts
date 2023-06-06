import {describe, expect, it} from '@jest/globals';
import {ErrorFormatter} from 'commons/error/ErrorFormatter';
import {initializeGlobals} from 'src/initialize';
import {App} from 'vue';
import {mock} from 'jest-mock-extended';
import {AxiosError, AxiosHeaders} from 'axios';
import {installGlobalCulture, VueCulture} from 'commons/i18n/VueCulture';
import {resettableGlobals} from 'commons/global/globals';

describe('ErrorFormatter', () => {
  installGlobalCulture();
  initializeGlobals(mock<App>());
  const formatter = new ErrorFormatter();
  const others = {statusText: 'Status Text', headers: {}, config: {headers: new AxiosHeaders()}};

  it('should be able to format errors', () => {
    expect(formatter.format('string')).toBe('string');
    expect(formatter.format(undefined)).toBe('未知错误');
    expect(formatter.format(new Error('error'))).toBe('error');
  });

  it('should be able to format network errors', () => {
    expect(formatter.format(new AxiosError('network error', 'ERROR', undefined, undefined, undefined))).toBe('网络错误，请稍后再试～');
    expect(formatter.format(new AxiosError('network error', 'ERROR', undefined, undefined, {data: {}, status: 500, ...others}))).toBe('服务器故障，请联系管理员～');
    expect(formatter.format(new AxiosError('network error', 'ERROR', undefined, undefined, {data: {message: 'message from server'}, status: 400, ...others}))).toBe('message from server');
    expect(formatter.format(new AxiosError('network error', 'ERROR', undefined, undefined, {data: {message: 'message from server'}, status: 404, ...others}))).toBe('message from server');
    expect(formatter.format(new AxiosError('network error', 'ERROR', undefined, undefined, {data: {message: ''}, status: 400, ...others}))).toBe('数据格式错误');
    expect(formatter.format(new AxiosError('network error', 'ERROR', undefined, undefined, {data: {message: ''}, status: 404, ...others}))).toBe('资源不存在');
  });

  it('should format as empty string when i18n returned undefined', () => {
    const mockCulture = mock<VueCulture>();
    resettableGlobals.resetCulture(mockCulture);
    expect(formatter.format(undefined)).toBe('');
  });
});
