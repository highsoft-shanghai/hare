import {Application} from 'commons/application/Application';
import {reactive} from 'vue';
import {Navigator} from 'commons/router/Navigator';
import {Context} from 'commons/context/Context';
import { LoadingIndicator } from './LoadingIndicator';
import {Storage} from 'commons/global/Storage';
import {Culture} from 'commons/i18n/Culture';
import {VueCulture} from 'commons/i18n/VueCulture';

export interface Globals {
  readonly culture: Culture;
  readonly application: Application;
  readonly navigator: Navigator;
  readonly context: Context;
  readonly storage: Storage;
  readonly loadingIndicator: LoadingIndicator;
}

export class ResettableGlobals implements Globals {
  private _culture?: VueCulture;
  private _application?: Application;
  private _navigator?: Navigator;
  private _context?: Context;
  private _storage?: Storage;
  private _loadingIndicator?: LoadingIndicator;

  public get culture(): VueCulture {
    if (!this._culture) throw new Error('Global culture not initialized');
    return this._culture;
  }

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

  public get storage(): Storage {
    if (!this._storage) throw new Error('Global storage not initialized');
    return this._storage;
  }

  public get loadingIndicator(): LoadingIndicator {
    if (!this._loadingIndicator) throw new Error('Global loading-indicator not initialized');
    return this._loadingIndicator;
  }

  public resetCulture(culture: VueCulture): void {
    this._culture = culture;
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

  public resetStorage(storage: Storage): void {
    this._storage = storage;
  }

  public resetLoadingIndicator(indicator: LoadingIndicator): void {
    this._loadingIndicator = indicator;
  }

  public clear(): void {
    this._culture = undefined;
    this._application = undefined;
    this._navigator = undefined;
    this._context = undefined;
    this._storage = undefined;
    this._loadingIndicator = undefined;
  }
}

export const resettableGlobals = reactive(new ResettableGlobals()) as ResettableGlobals;
export const globals: Globals = resettableGlobals;
