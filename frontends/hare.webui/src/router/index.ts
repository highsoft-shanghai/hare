import {route} from 'quasar/wrappers';
import {createRouter, Router, RouteRecordRaw} from 'vue-router';
import routes from './routes';
import {HistoryFactory} from 'commons/router/HistoryFactory';
import {globals, resettableGlobals} from 'commons/global/globals';
import {VueRouterNavigator} from 'commons/router/VueRouterNavigator';
import {Context} from 'commons/context/Context';
import {AxiosContextApi} from 'commons/context/AxiosContextApi';
import {installGlobalLoadingIndicator} from 'commons/global/QuasarLoadingIndicator';
import {installGlobalStorage} from 'commons/global/QuasarStorage';

/*
 * If not building with SSR mode, you can
 * directly export the Router instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Router instance.
 */

installGlobalLoadingIndicator();
installGlobalStorage();

globals.loadingIndicator.show();

const historyFactory = new HistoryFactory();
const scrollBehavior = () => ({left: 0, top: 0});

export function createVueRouter(routes: ReadonlyArray<RouteRecordRaw>): Router {
  return createRouter({scrollBehavior: scrollBehavior, history: historyFactory.create(process.env.VUE_ROUTER_BASE), routes: routes});
}

export async function installVueRouter(/* { store, ssrContext } */): Promise<Router> {
  const router = createVueRouter(routes);
  resettableGlobals.resetContext(new Context(new AxiosContextApi()));
  resettableGlobals.resetNavigator(new VueRouterNavigator(router, globals.context));
  await globals.context.restore();
  return router;
}

export default route(installVueRouter);
