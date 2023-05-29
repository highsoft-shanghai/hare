import {resettableGlobals} from 'commons/global/globals';
import {Application} from 'commons/application/Application';
import {pagekey} from 'commons/page/PageKey';
import {PageModel} from 'commons/page/PageModel';
import {VueCulture} from 'commons/i18n/VueCulture';
import {App} from 'vue';
import {initializePayloadFactories} from 'commons/payload/FactoriesInitializer';
import {errorHandler, warningHandler} from 'commons/error/error-handler';

export function initializeGlobals(app: App): void {
  app.config.errorHandler = errorHandler;
  app.config.warnHandler = warningHandler;
  initializePayloadFactories();
  resettableGlobals.resetApplication(new Application(new VueCulture(app), new PageModel(pagekey('route.home'))));
}
