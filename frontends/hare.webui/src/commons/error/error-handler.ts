import {QuasarErrorReporter} from 'commons/error/QuasarErrorReporter';

const errorReporter = new QuasarErrorReporter();

export const errorHandler = (err: unknown, instance?: unknown, info?: string) => {
  console.log(err, instance, info);
  errorReporter.reportError(err);
};

export const warningHandler = (message: string, instance: unknown, trace: string) => {
  console.log(message, instance, trace);
  errorReporter.reportWarning(message);
};
