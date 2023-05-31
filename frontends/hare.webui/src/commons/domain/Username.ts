import {NullableValue} from 'commons/domain/NullableValue';
import {payload} from 'commons/payload/Payload';
import {string} from 'commons/payload/StringType';

export class Username extends NullableValue<string> {
  public fromData(data: unknown): void {
    super.changeValue(payload(data).as(string().allowOptional()));
  };
}
