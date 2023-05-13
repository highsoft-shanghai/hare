export class StartMenuItemGroup {
  public readonly key: string;
  public readonly label: string;
  public readonly icon: string;

  public constructor(key: string, label: string, icon: string) {
    this.key = key;
    this.label = label;
    this.icon = icon;
  }
}
