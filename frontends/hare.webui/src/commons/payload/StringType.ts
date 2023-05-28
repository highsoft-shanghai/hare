import {Type} from './Type';

export class StringType extends Type<string> {
  public convert(path: string, value: unknown): string {
    if (typeof value !== 'string') throw new Error(`type mismatch: require 'string' but got '${typeof value}', in path '${path}'`);
    return value as string;
  }
}

export function string(): StringType {
  return new StringType();
}
