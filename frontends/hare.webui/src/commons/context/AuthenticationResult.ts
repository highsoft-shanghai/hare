import {Payload} from 'commons/payload/Payload';
import {boolean} from 'commons/payload/BooleanType';
import {string} from 'commons/payload/StringType';

export class AuthenticationResult {
  public readonly success: boolean;
  public readonly accessToken?: string;

  public constructor(payload: Payload) {
    this.success = payload.get('success').as(boolean());
    this.accessToken = payload.get('accessToken').as(string().allowOptional());
  }
}

export function authenticationResult(payload: Payload): AuthenticationResult {
  return new AuthenticationResult(payload);
}
