import {StartMenuItem} from 'layouts/main/StartMenuItem';

export class StartMenuItemSubGroup {
  public readonly key: string;
  public readonly label: string;
  public readonly icon: string;
  public readonly items: StartMenuItem[];

  public constructor(key: string, label: string, icon: string, items: StartMenuItem[]) {
    this.key = key;
    this.label = label;
    this.icon = icon;
    this.items = items;
  }
}
