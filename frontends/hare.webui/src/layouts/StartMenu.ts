import {StartMenuItemGroup} from 'layouts/StartMenuItemGroup';

export class StartMenu {
  private _visible = false;
  private _activeGroup: string | undefined;
  private _groups: StartMenuItemGroup[] = [ // TODO: construct from remote data
    new StartMenuItemGroup('users', '人员管理', 'manage_accounts'),
    new StartMenuItemGroup('customers', '客户管理', 'groups'),
    new StartMenuItemGroup('products', '商品管理', 'widgets'),
    new StartMenuItemGroup('inventories', '库存管理', 'inventory_2'),
    new StartMenuItemGroup('suppliers', '供应商管理', 'local_shipping'),
    new StartMenuItemGroup('plans', '学习计划管理', 'next_plan'),
  ];

  public constructor() {
    this._activeGroup = this._groups[0]?.key;
  }

  public toggleVisible(): void {
    this._visible = !this._visible;
  }

  public close(): void {
    this._visible = false;
  }

  public get visible(): boolean {
    return this._visible;
  }

  public get groups(): StartMenuItemGroup[] {
    return this._groups;
  }

  public get activeGroup(): string | undefined {
    return this._activeGroup;
  }

  public activateGroup(key: string | undefined): void {
    this._activeGroup = key;
  }
}
