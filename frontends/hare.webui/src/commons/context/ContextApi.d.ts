import {Payload} from 'commons/payload/Payload';

export interface ContextApi {
  readonly get: (accessToken: string) => Promise<Payload>;
}
