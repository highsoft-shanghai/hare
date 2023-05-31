import {TextInputModel} from 'components/base/TextInputModel';
import {i18n} from 'commons/i18n/i18n';
import {Navigator} from 'commons/router/Navigator';
import {Context} from 'commons/context/Context';
import {LoginApi} from 'pages/login/LoginApi';
import {AuthenticationResult, authenticationResult} from 'commons/context/AuthenticationResult';
import {payload, Payload} from 'commons/payload/Payload';
import {Username} from 'components/login/Username';
import {Password} from 'components/login/Password';

export class LoginModel {
  private readonly api: LoginApi;
  private readonly navigator: Navigator;
  private readonly context: Context;
  private readonly _username = new TextInputModel(i18n('label.username'), new Username());
  private readonly _password = new TextInputModel(i18n('label.password'), new Password());
  private _authenticationResult?: AuthenticationResult;
  private _loading = false;

  public constructor(api: LoginApi, navigator: Navigator, context: Context) {
    this.api = api;
    this.navigator = navigator;
    this.context = context;
  }

  public async submit(): Promise<void> {
    try {
      this._loading = true;
      this._authenticationResult = undefined;
      this._authenticationResult = authenticationResult(await this.api.login(this.payload));
      if (!this._authenticationResult.accessToken) return;
      await this.context.reset(this._authenticationResult.accessToken);
      await this.navigator.goto('route.home');
    } finally {
      this._loading = false;
    }
  }

  public get username(): TextInputModel {
    return this._username;
  }

  public get password(): TextInputModel {
    return this._password;
  }

  public get submittable(): boolean {
    return this.username.valuePresent && this.password.valuePresent;
  }

  public get loading(): boolean {
    return this._loading;
  }

  public get lastAuthenticationResult(): AuthenticationResult | undefined {
    return this._authenticationResult;
  }

  private get payload(): Payload {
    return payload({group: 'web', type: 'username-and-password', username: this.username.value, password: this.password.value});
  }
}
