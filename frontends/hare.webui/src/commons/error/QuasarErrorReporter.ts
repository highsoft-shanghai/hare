import {ErrorReporter} from 'commons/error/ErrorReporter';
import {Notify} from 'quasar';

export class QuasarErrorReporter implements ErrorReporter {
  public report(error: unknown): void {
    Notify.create({type: 'negative', position: 'top', message: 'error message'});
  }
}
