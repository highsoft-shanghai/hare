import {createMemoryHistory, createWebHashHistory, createWebHistory, RouterHistory} from 'vue-router';

export class HistoryFactory {
  public create(base?: string): RouterHistory {
    if (process.env.SERVER) return createMemoryHistory(base);
    return process.env.VUE_ROUTER_MODE === 'history' ? createWebHistory(base) : createWebHashHistory(base);
  }
}
