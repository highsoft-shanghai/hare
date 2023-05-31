export abstract class Value {
  public abstract fromData(data: unknown): void;

  public abstract toJSON(): unknown;
}
