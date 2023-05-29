export interface ErrorReporter {
  readonly reportError: (error: unknown) => void;
  readonly reportWarning: (message: string) => void;
}
