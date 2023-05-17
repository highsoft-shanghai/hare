import {Application} from 'layouts/Application';
import {reactive} from 'vue';
import {Navigator} from 'commons/router/Navigator';

export interface Globals {
  readonly application: Application;
  readonly navigator: Navigator;
}

export class ResettableGlobals implements Globals {
  private _application?: Application;
  private _navigator?: Navigator;

  public get application(): Application {
    if (!this._application) throw new Error('Global application not initialized');
    return this._application;
  }

  public get navigator(): Navigator {
    if (!this._navigator) throw new Error('Global navigator not initialized');
    return this._navigator;
  }

  public resetApplication(application: Application): void {
    this._application = application;
  }

  public resetNavigator(navigator: Navigator) {
    this._navigator = navigator;
  }

  public clear(): void {
    this._application = undefined;
    this._navigator = undefined;
  }
}

export const resettableGlobals = reactive(new ResettableGlobals()) as ResettableGlobals;
export const globals: Globals = resettableGlobals;
