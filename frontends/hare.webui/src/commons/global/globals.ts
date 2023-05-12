import { Application } from "layouts/Application";

export interface Globals {
  readonly application: Application;
}

export class ResettableGlobals implements Globals {
  private _application?: Application;

  public get application(): Application {
    return this._application;
  }

  public resetApplication(application: Application): void {
    this._application = application;
  }

  public clear(): void {
    this._application = undefined;
  }
}

export const resettableGlobals = new ResettableGlobals();
export const globals = resettableGlobals;
