import {LoadingIndicator} from './LoadingIndicator';
import {Loading, QSpinnerGears} from 'quasar';
import {resettableGlobals} from 'commons/global/globals';

export class QuasarLoadingIndicator implements LoadingIndicator {
  public show(): void {
    Loading.show({spinner: QSpinnerGears, spinnerSize: 128, backgroundColor: 'blue-10'});
  }

  public hide(): void {
    Loading.hide();
  }
}

export function installGlobalLoadingIndicator(): void {
  resettableGlobals.resetLoadingIndicator(new QuasarLoadingIndicator());
}
