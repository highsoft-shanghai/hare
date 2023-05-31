import {Payload} from 'commons/payload/Payload';

export interface ContextApi {
  readonly get: () => Promise<Payload>;
}
