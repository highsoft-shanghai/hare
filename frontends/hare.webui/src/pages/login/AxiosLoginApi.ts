import {LoginApi} from 'pages/login/LoginApi';

export class AxiosLoginApi implements LoginApi {
  public async login(payload: Readonly<Record<string, unknown>>): Promise<Readonly<Record<string, unknown>>> {
    return {};
  }
}
