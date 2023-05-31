import {ControlModel} from 'components/base/ControlModel';
import {Message} from 'commons/i18n/Message';
import {TextValue} from 'commons/value/TextValue';

export class TextInputModel implements ControlModel {
  private _label?: Message;
  private readonly _value: TextValue;

  public constructor(label: Message, value: TextValue) {
    this._label = label;
    this._value = value;
  }

  public handleInput(value: string | undefined): void {
    this._value.assign(value);
  }

  public get label(): string | undefined {
    return this._label?.toString();
  }

  public get value(): TextValue {
    return this._value;
  }

  public get valuePresent(): boolean {
    return this._value.present;
  }
}
