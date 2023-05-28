import {TextInputModel} from 'components/base/TextInputModel';
import {i18n} from 'commons/i18n/i18n';
import {Navigator} from 'commons/router/Navigator';
import {Context} from 'commons/context/Context';
import {LoginApi} from 'pages/login/LoginApi';
import {AuthenticationResult, authenticationResult} from 'commons/context/AuthenticationResult';
import {payload, Payload} from 'commons/payload/Payload';

export class LoginModel {
  private readonly api: LoginApi;
  private readonly navigator: Navigator;
  private readonly context: Context;
  private readonly _loginName = new TextInputModel(i18n('label.login-name'));
  private readonly _password = new TextInputModel(i18n('label.password'));
  private _authenticationResult?: AuthenticationResult;

  public constructor(api: LoginApi, navigator: Navigator, context: Context) {
    this.api = api;
    this.navigator = navigator;
    this.context = context;
  }

  public async submit(): Promise<void> {
    this._authenticationResult = authenticationResult(await this.api.login(this.payload));
    if (!this._authenticationResult.accessToken) return;
    await this.context.reset(this._authenticationResult.accessToken);
    await this.navigator.goto('route.home');
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

  public get lastAuthenticationResult(): AuthenticationResult | undefined {
    return this._authenticationResult;
  }

  private get payload(): Payload {
    return payload({loginName: this.loginName.value, secret: this.password.value});
  }
}
