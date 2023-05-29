import {Storage} from 'commons/global/Storage';
import {SessionStorage} from 'quasar';
import {resettableGlobals} from 'commons/global/globals';

export class QuasarStorage implements Storage {
  public get(key: string): string | null {
    return SessionStorage.getItem(key);
  }

  public set(key: string, value: string | null): void {
    SessionStorage.set(key, value);
  }

  public clear(): void {
    SessionStorage.clear();
  }
}

export function installGlobalStorage(): void {
  resettableGlobals.resetStorage(new QuasarStorage());
}
