export abstract class Culture {
  public abstract localize(code: string, ...args: unknown[]): string;
}
