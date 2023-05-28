import {TextInputModel} from 'components/base/TextInputModel';
import {i18n} from 'commons/i18n/i18n';

export class LoginModel {
  public readonly loginName = new TextInputModel(i18n('label.login-name'));
  public readonly password = new TextInputModel(i18n('label.password'));

  public get submittable(): boolean {
    return !!this.loginName.value && !!this.password.value;
  }
}
