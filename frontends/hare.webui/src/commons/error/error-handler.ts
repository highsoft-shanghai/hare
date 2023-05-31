import {QuasarErrorReporter} from 'commons/error/QuasarErrorReporter';

const errorReporter = new QuasarErrorReporter();

export const errorHandler = (err: unknown, instance?: unknown, info?: string) => {
  console.log('error: ', err);
  console.log('instance: ', instance);
  console.log('info: ', info);
  errorReporter.reportError(err);
};

export const warningHandler = (message: string, instance: unknown, trace: string) => {
  console.log('message: ', message);
  console.log('instance: ', instance);
  console.log('trace: ', trace);
  errorReporter.reportWarning(message);
};
