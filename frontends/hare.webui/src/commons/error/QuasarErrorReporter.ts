import {ErrorReporter} from 'commons/error/ErrorReporter';
import {Notify} from 'quasar';

export class QuasarErrorReporter implements ErrorReporter {
  public reportError(error: unknown): void {
    Notify.create({type: 'negative', position: 'top', message: 'error message'});
  }

  public reportWarning(message: string): void {
    Notify.create({type: 'warning', position: 'top', message: message});
  }
}
