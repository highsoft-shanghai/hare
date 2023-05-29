import {ErrorReporter} from 'commons/error/ErrorReporter';
import {Notify} from 'quasar';
import {ErrorFormatter} from 'commons/error/ErrorFormatter';

export class QuasarErrorReporter implements ErrorReporter {
  private errorFormatter = new ErrorFormatter();

  public reportError(error: unknown): void {
    Notify.create({type: 'negative', position: 'top', message: this.errorFormatter.format(error)});
  }

  public reportWarning(message: string): void {
    Notify.create({type: 'warning', position: 'top', message: message});
  }
}
