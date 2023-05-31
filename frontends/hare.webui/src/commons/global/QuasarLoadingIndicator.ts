import {LoadingIndicator} from './LoadingIndicator';
import {Loading, QSpinnerBars} from 'quasar';
import {resettableGlobals} from 'commons/global/globals';
import {i18n} from 'commons/i18n/i18n';

export class QuasarLoadingIndicator implements LoadingIndicator {
  public show(): void {
    Loading.show({spinner: QSpinnerBars, backgroundColor: 'primary', message: i18n('message.application-loading').toString(), html: true});
  }

  public hide(): void {
    Loading.hide();
  }
}

export function installGlobalLoadingIndicator(): void {
  resettableGlobals.resetLoadingIndicator(new QuasarLoadingIndicator());
}
