import {QuasarErrorReporter} from 'commons/error/QuasarErrorReporter';

const errorReporter = new QuasarErrorReporter();

export const errorHandler = (err: unknown, instance?: unknown, info?: string) => {
  console.log(err, instance, info);
  errorReporter.report(err);
};
