export abstract class Value {
  public abstract assign(data: unknown): void;

  public abstract toJSON(): unknown;
}
