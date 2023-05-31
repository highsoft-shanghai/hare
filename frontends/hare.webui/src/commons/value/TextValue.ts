import {NullableValue} from 'commons/value/NullableValue';
import {payload} from 'commons/payload/Payload';
import {string} from 'commons/payload/StringType';

export class TextValue extends NullableValue<string> {
  public fromData(data: unknown): void {
    this.changeValue(payload(data).as(string().allowOptional()));
  }
}
