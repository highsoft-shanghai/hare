import {StartMenuItemGroup} from 'layouts/StartMenuItemGroup';
import {StartMenuItemSubGroup} from 'layouts/StartMenuItemSubGroup';
import {StartMenuItem} from 'layouts/StartMenuItem';

export class StartMenu {
  private _visible = false;
  private _activeGroup: StartMenuItemGroup | undefined;
  private _groups: StartMenuItemGroup[] = [ // TODO: construct from remote data
    new StartMenuItemGroup('users', '人员管理', 'manage_accounts', [
      new StartMenuItemSubGroup('users-management', '人员信息维护', '', [new StartMenuItem('add-user', '新增人员', 'person_add', 'router.home'), new StartMenuItem('query-users', '检索人员', 'search', 'router.home')]),
      new StartMenuItemSubGroup('users-analysis', '人员信息分析', '', [new StartMenuItem('user-status', '人员状态', 'people_alt', 'router.home')])
    ]),
    new StartMenuItemGroup('customers', '客户管理', 'groups', []),
    new StartMenuItemGroup('products', '商品管理', 'widgets', []),
    new StartMenuItemGroup('inventories', '库存管理', 'inventory_2', []),
    new StartMenuItemGroup('suppliers', '供应商管理', 'local_shipping', []),
    new StartMenuItemGroup('plans', '学习计划管理', 'next_plan', []),
  ];

  public constructor() {
    this._activeGroup = this._groups[0];
  }

  public toggleVisible(): void {
    this._visible = !this._visible;
  }

  public close(): void {
    this._visible = false;
  }

  public activateGroup(key: string | undefined): void {
    this._activeGroup = this._groups.find(x => x.key === key);
  }

  public get visible(): boolean {
    return this._visible;
  }

  public get groups(): StartMenuItemGroup[] {
    return this._groups;
  }

  public get activeGroupKey(): string | undefined {
    return this._activeGroup?.key;
  }

  public get activeGroup(): StartMenuItemGroup | undefined {
    return this._activeGroup;
  }
}
