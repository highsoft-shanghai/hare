import {Payload} from 'commons/payload/Payload';

export interface LoginApi {
  readonly login: (payload: Payload) => Promise<Payload>;
}
