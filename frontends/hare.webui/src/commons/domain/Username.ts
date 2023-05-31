import {payload} from 'commons/payload/Payload';
import {string} from 'commons/payload/StringType';
import {SimpleValue} from 'commons/domain/SimpleValue';

export class Username extends SimpleValue<string | undefined> {
  public constructor(value?: string) {
    super(value);
  }

  public setValueFromData(data: unknown): void {
    super.value = payload(data).as(string());
  };

  public asData(): unknown {
    return super.value;
  }
}
