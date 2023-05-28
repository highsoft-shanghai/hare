import {ControlModel} from 'components/base/ControlModel';
import {i18n} from 'commons/i18n/i18n';

export class LoginNameModel implements ControlModel {
  private _value: string | undefined = undefined;

  public handleInput(value: string | undefined): void {
    this._value = value;
  }

  public readonly label = i18n('label.login-name');

  public get value(): string | undefined {
    return this._value;
  }
}
