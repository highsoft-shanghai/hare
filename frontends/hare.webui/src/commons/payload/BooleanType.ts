import {Type} from './Type';

export class BooleanType extends Type<boolean> {
  public convert(path: string, value: unknown): boolean {
    if (typeof value !== 'boolean') throw new Error(`type mismatch: require 'boolean' but got '${typeof value}', in path '${path}'`);
    return value as boolean;
  }
}

export function boolean(): BooleanType {
  return new BooleanType();
}
