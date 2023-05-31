export abstract class Value {
  public abstract setValueFromData(data: unknown): void;

  public abstract asData(): unknown;
}
