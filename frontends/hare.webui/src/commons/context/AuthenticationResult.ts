import {Payload} from 'commons/payload/Payload';
import {boolean} from 'commons/payload/BooleanType';
import {string} from 'commons/payload/StringType';

export class AuthenticationResult {
  public readonly success: boolean;
  public readonly accessToken?: string;
  public readonly message?: string;

  public constructor(payload: Payload) {
    this.success = payload.get('success').as(boolean());
    this.accessToken = this.success ? payload.get('accessToken').as(string().allowOptional()) : undefined;
    this.message = payload.get('message').as(string().allowOptional());
    this.verify();
  }

  private verify() {
    if (this.success && !this.accessToken || !this.success && !this.message) {
      throw new Error('Malformed authentication result payload from server');
    }
  }
}

export function authenticationResult(payload: Payload): AuthenticationResult {
  return new AuthenticationResult(payload);
}
