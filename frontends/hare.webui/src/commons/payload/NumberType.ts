import {Type} from './Type';

export class NumberType extends Type<number> {
  public convert(path: string, value: unknown): number {
    if (typeof value !== 'number') throw new Error(`type mismatch: require 'number' but got '${typeof value}', in path '${path}'`);
    return value as number;
  }
}

export function number(): NumberType {
  return new NumberType();
}
