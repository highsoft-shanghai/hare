export interface Culture {
  readonly localize: (code: string, ...args: unknown[]) => string;
}
