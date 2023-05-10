import {route} from 'quasar/wrappers';
import {createRouter,} from 'vue-router';

import routes from './routes';
import {HistoryFactory} from 'commons/router/HistoryFactory';

/*
 * If not building with SSR mode, you can
 * directly export the Router instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Router instance.
 */

const historyFactory = new HistoryFactory();

export function createApplicationRouter(/* { store, ssrContext } */) {
  return createRouter({
    scrollBehavior: () => ({left: 0, top: 0}),
    history: historyFactory.create(process.env.VUE_ROUTER_BASE),
    routes,
  });
}

export default route(createApplicationRouter);
