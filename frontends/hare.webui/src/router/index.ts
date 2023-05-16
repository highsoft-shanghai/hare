import {route} from 'quasar/wrappers';
import {createRouter} from 'vue-router';
import routes from './routes';
import {HistoryFactory} from 'commons/router/HistoryFactory';
import {globals} from 'commons/global/globals';

/*
 * If not building with SSR mode, you can
 * directly export the Router instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Router instance.
 */

const historyFactory = new HistoryFactory();
const scrollBehavior = () => ({left: 0, top: 0});

export function createApplicationRouter(/* { store, ssrContext } */) {
  const router = createRouter({scrollBehavior: scrollBehavior, history: historyFactory.create(process.env.VUE_ROUTER_BASE), routes});
  globals.resetRouter(router);
  return router;
}

export default route(createApplicationRouter);
