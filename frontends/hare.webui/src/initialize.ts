import { resettableGlobals } from "commons/global/globals";
import { Application } from "layouts/Application";
import { pagekey } from "commons/page/PageKey";
import { PageModel } from "commons/page/PageModel";
import { VueCulture } from "commons/i18n/VueCulture";
import { App } from "vue";

export function initializeGlobals(app: App): void {
  resettableGlobals.resetApplication(new Application(new VueCulture(app), new PageModel(pagekey('router.home'))));
}
