import { resettableGlobals } from "commons/global/globals";
import { Application } from "layouts/Application";
import { pagekey } from "commons/page/PageKey";
import { PageModel } from "commons/page/PageModel";
import { Translator } from "commons/i18n/Translator";

export function initializeGlobals(): void {
  resettableGlobals.resetApplication(new Application(new PageModel(pagekey('router.home'))));
  Translator.initialize();
}
