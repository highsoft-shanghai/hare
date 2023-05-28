import {TextInputModel} from 'components/base/TextInputModel';
import {i18n} from 'commons/i18n/i18n';
import {globals, resettableGlobals} from 'commons/global/globals';
import {grantedAuthorities} from 'commons/context/GrantedAuthorities';
import {Navigator} from 'commons/router/Navigator';
import {Context} from 'commons/context/Context';
import {LoginApi} from 'pages/login/LoginApi';

export class LoginModel {
  private readonly api: LoginApi;
  private readonly navigator: Navigator;
  private readonly context: Context;
  private readonly _loginName = new TextInputModel(i18n('label.login-name'));
  private readonly _password = new TextInputModel(i18n('label.password'));

  public constructor(api: LoginApi, navigator: Navigator, context: Context) {
    this.api = api;
    this.navigator = navigator;
    this.context = context;
  }

  public async submit(): Promise<void> {
    // TODO: implement it
    resettableGlobals.resetContext(grantedAuthorities());
    await globals.navigator.goto('route.home');
  }

  public get loginName(): TextInputModel {
    return this._loginName;
  }

  public get password(): TextInputModel {
    return this._password;
  }

  public get submittable(): boolean {
    return !!this.loginName.value && !!this.password.value;
  }
}
