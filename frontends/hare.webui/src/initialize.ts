import {resettableGlobals} from 'commons/global/globals';
import {Application} from 'commons/application/Application';
import {pagekey} from 'commons/page/PageKey';
import {PageModel} from 'commons/page/PageModel';
import {VueCulture} from 'commons/i18n/VueCulture';
import {App} from 'vue';

export function initializeGlobals(app: App): void {
  resettableGlobals.context.clear();
  resettableGlobals.resetApplication(new Application(new VueCulture(app), new PageModel(pagekey('route.home'))));
}
