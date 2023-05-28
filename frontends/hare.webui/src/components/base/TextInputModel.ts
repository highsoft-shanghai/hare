import {ControlModel} from 'components/base/ControlModel';
import {Message} from 'commons/i18n/Message';

export class TextInputModel implements ControlModel {
  private _label?: Message;
  private _value: string | undefined = undefined;

  public constructor(label?: Message, value?: string) {
    this._label = label;
    this._value = value;
  }

  public handleInput(value: string | undefined): void {
    this._value = value;
  }

  public get label(): string | undefined {
    return this._label?.toString();
  }

  public get value(): string | undefined {
    return this._value;
  }
}
