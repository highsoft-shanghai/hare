export interface ErrorReporter {
  readonly report: (error: unknown) => void;
}
