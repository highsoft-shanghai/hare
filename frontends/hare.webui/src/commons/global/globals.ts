import {Application} from 'commons/application/Application';
import {reactive} from 'vue';
import {Navigator} from 'commons/router/Navigator';
import {Context} from 'commons/context/Context';
import { LoadingIndicator } from './LoadingIndicator';

export interface Globals {
  readonly application: Application;
  readonly navigator: Navigator;
  readonly context: Context;
  readonly loadingIndicator: LoadingIndicator;
}

export class ResettableGlobals implements Globals {
  private _application?: Application;
  private _navigator?: Navigator;
  private _context?: Context;
  private _loadingIndicator?: LoadingIndicator;

  public get application(): Application {
    if (!this._application) throw new Error('Global application not initialized');
    return this._application;
  }

  public get navigator(): Navigator {
    if (!this._navigator) throw new Error('Global navigator not initialized');
    return this._navigator;
  }

  public get context(): Context {
    if (!this._context) throw new Error('Global context not initialized');
    return this._context;
  }

  public get loadingIndicator(): LoadingIndicator {
    if (!this._loadingIndicator) throw new Error('Global loading-indicator not initialized');
    return this._loadingIndicator;
  }

  public resetApplication(application: Application): void {
    this._application = application;
  }

  public resetNavigator(navigator: Navigator): void {
    this._navigator = navigator;
  }

  public resetContext(context: Context): void {
    this._context = context;
  }

  public resetLoadingIndicator(indicator: LoadingIndicator): void {
    this._loadingIndicator = indicator;
  }

  public clear(): void {
    this._application = undefined;
    this._navigator = undefined;
    this._context = undefined;
  }
}

export const resettableGlobals = reactive(new ResettableGlobals()) as ResettableGlobals;
export const globals: Globals = resettableGlobals;
