import { globals } from 'commons/global/globals';

export class CultureAccessor {
  public localize(code: string, ...args: unknown[]): string {
    return globals.culture.localize(code, args);
  }
}

export const culture = new CultureAccessor();
