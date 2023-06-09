import {resettableGlobals} from 'commons/global/globals';
import {Application} from 'commons/application/Application';
import {pagekey} from 'commons/page/PageKey';
import {PageModel} from 'commons/page/PageModel';
import {App} from 'vue';
import {initializePayloadFactories} from 'commons/payload/FactoriesInitializer';
import {errorHandler, warningHandler} from 'commons/error/error-handler';

export function initializeGlobals(app: App): void {
  app.config.errorHandler = errorHandler;
  app.config.warnHandler = warningHandler;
  app.use(resettableGlobals.culture.instance);
  initializePayloadFactories();
  resettableGlobals.resetApplication(new Application(new PageModel(pagekey('route.home'))));
}
