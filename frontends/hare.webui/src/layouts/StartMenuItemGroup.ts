import {StartMenuItemSubGroup} from 'layouts/StartMenuItemSubGroup';

export class StartMenuItemGroup {
  public readonly key: string;
  public readonly label: string;
  public readonly icon: string;
  public readonly subgroups: StartMenuItemSubGroup[];

  public constructor(key: string, label: string, icon: string, subgroups: StartMenuItemSubGroup[]) {
    this.key = key;
    this.label = label;
    this.icon = icon;
    this.subgroups = subgroups;
  }
}
