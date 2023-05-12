import { Application } from "layouts/Application";
import { reactive } from "vue";

export interface Globals {
  readonly application: Application;
}

export class ResettableGlobals implements Globals {
  private _application?: Application;

  public get application(): Application {
    if (!this._application) throw new Error('Globals not initialized');
    return this._application;
  }

  public resetApplication(application: Application): void {
    this._application = application;
  }

  public clear(): void {
    this._application = undefined;
  }
}

export const resettableGlobals = reactive(new ResettableGlobals());
export const globals = resettableGlobals;
