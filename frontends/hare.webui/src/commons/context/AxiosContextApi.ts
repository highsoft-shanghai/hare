import {ContextApi} from 'commons/context/ContextApi';
import {Payload} from 'commons/payload/Payload';
import {api} from 'commons/api/api';

export class AxiosContextApi implements ContextApi {
  public async get(accessToken: string): Promise<Payload> {
    return Payload.of(await api.get(`/access-tokens/${accessToken}`));
  }
}
