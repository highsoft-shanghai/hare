import {payload} from 'commons/payload/Payload';
import {string} from 'commons/payload/StringType';
import {NullableValue} from 'commons/domain/NullableValue';

export class Username extends NullableValue<string> {
  public setValueFromData(data: unknown): void {
    super.value = payload(data).as(string());
  };

  public asData(): unknown {
    return super.value;
  }
}
