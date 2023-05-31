import {NullableValue} from 'commons/value/NullableValue';
import {payload} from 'commons/payload/Payload';
import {string} from 'commons/payload/StringType';

export class TextValue extends NullableValue<string> {
  public assign(data: unknown): void {
    this.changeValue(payload(data).as(string().allowOptional()));
  }
}
