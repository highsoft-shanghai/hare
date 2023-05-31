import {TextValue} from 'commons/value/TextValue';
import sha512 from 'crypto-js/sha512';

export class Password extends TextValue {
  public toJSON(): unknown {
    return this.value ? sha512(this.value).toString() : null;
  }
}
