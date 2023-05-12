import {describe, expect, it, jest, beforeEach} from '@jest/globals';
import {HistoryFactory} from 'commons/router/HistoryFactory';
import {createMemoryHistory, createWebHashHistory, createWebHistory, RouterHistory} from 'vue-router';
import {mock} from 'jest-mock-extended';

jest.mock('vue-router');

describe('HistoryFactory', () => {
  const mockCreateMemoryHistory = createMemoryHistory as jest.MockedFunction<typeof createMemoryHistory>
  const mockCreateWebHashHistory = createWebHashHistory as jest.MockedFunction<typeof createWebHashHistory>
  const mockCreateWebHistory = createWebHistory as jest.MockedFunction<typeof createWebHistory>
  const memoryHistory: RouterHistory = mock<RouterHistory>();
  const hashHistory: RouterHistory = mock<RouterHistory>();
  const history: RouterHistory = mock<RouterHistory>();

  beforeEach(() => {
    mockCreateMemoryHistory.mockReturnValue(memoryHistory);
    mockCreateWebHashHistory.mockReturnValue(hashHistory);
    mockCreateWebHistory.mockReturnValue(history);
  });

  it('should create memory history when in server environment', () => {
    const factory = new HistoryFactory();
    process.env.SERVER = 'true';
    expect(factory.create(process.env.VUE_ROUTER_BASE)).toBe(memoryHistory);
    expect(createMemoryHistory).toBeCalledWith(process.env.VUE_ROUTER_BASE);
  });

  it('should create hash history when NOT in server environment and router mode is NOT history', () => {
    const factory = new HistoryFactory();
    process.env.SERVER = '';
    process.env.VUE_ROUTER_MODE = 'hash';
    expect(factory.create(process.env.VUE_ROUTER_BASE)).toBe(hashHistory);
    expect(mockCreateWebHashHistory).toBeCalledWith(process.env.VUE_ROUTER_BASE);
  });

  it('should create history when NOT in server environment and router mode is history', () => {
    const factory = new HistoryFactory();
    process.env.SERVER = '';
    process.env.VUE_ROUTER_MODE = 'history';
    expect(factory.create(process.env.VUE_ROUTER_BASE)).toBe(history);
    expect(mockCreateWebHistory).toBeCalledWith(process.env.VUE_ROUTER_BASE);
  });
});
