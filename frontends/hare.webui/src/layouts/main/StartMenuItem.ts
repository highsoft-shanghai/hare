export class StartMenuItem {
  public readonly key: string;
  public readonly label: string;
  public readonly icon: string;
  public readonly to: string;

  public constructor(key: string, label: string, icon: string, to: string) {
    this.key = key;
    this.label = label;
    this.icon = icon;
    this.to = to;
  }
}
