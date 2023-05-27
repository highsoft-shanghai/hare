import {route} from 'quasar/wrappers';
import {createRouter, Router, RouteRecordRaw} from 'vue-router';
import routes from './routes';
import {HistoryFactory} from 'commons/router/HistoryFactory';
import {resettableGlobals} from 'commons/global/globals';
import {VueRouterNavigator} from 'commons/router/VueRouterNavigator';

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

export function createVueRouter(routes: ReadonlyArray<RouteRecordRaw>): Router {
  return createRouter({scrollBehavior: scrollBehavior, history: historyFactory.create(process.env.VUE_ROUTER_BASE), routes: routes});
}

export function installVueRouter(/* { store, ssrContext } */): Router {
  const router = createVueRouter(routes);
  resettableGlobals.resetNavigator(new VueRouterNavigator(router, resettableGlobals.context));
  return router;
}

export default route(installVueRouter);
