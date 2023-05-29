export interface Storage {
  readonly get: (key: string) => string | null;
  readonly set: (key: string, value: string | null) => void;
}
