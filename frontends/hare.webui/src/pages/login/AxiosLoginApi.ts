import {LoginApi} from 'pages/login/LoginApi';
import {Payload} from 'commons/payload/Payload';
import {api} from 'commons/api/api';

export class AxiosLoginApi implements LoginApi {
  public async login(payload: Payload): Promise<Payload> {
    return Payload.of(await api.post('/logins', payload.value));
  }
}
