import { resettableGlobals } from "commons/global/globals";
import { Application } from "layouts/Application";

export function initializeGlobals(): void {
  resettableGlobals.resetApplication(new Application());
}
