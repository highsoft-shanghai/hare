export interface Navigator {
  readonly goto: (page: string) => Promise<void>;
}
