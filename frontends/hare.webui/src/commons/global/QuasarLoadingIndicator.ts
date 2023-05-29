import {LoadingIndicator} from './LoadingIndicator';
import {Loading} from 'quasar';
import {resettableGlobals} from 'commons/global/globals';

export class QuasarLoadingIndicator implements LoadingIndicator {
  public show(): void {
    Loading.show();
  }

  public hide(): void {
    Loading.hide();
  }
}

export function installGlobalLoadingIndicator(): void {
  resettableGlobals.resetLoadingIndicator(new QuasarLoadingIndicator());
}
