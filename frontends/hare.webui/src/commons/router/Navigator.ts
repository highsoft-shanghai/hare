export abstract class Navigator {
  public abstract goto(page: string): Promise<void>;
}
