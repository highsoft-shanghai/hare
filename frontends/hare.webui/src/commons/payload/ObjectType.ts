import {Type} from './Type';

export class ObjectType extends Type<Record<string, unknown>> {
  public convert(path: string, value: unknown): Record<string, unknown> {
    if (typeof value !== 'object') throw new Error(`type mismatch: require 'object' but got '${typeof value}', in path '${path}'`);
    return value as Record<string, unknown>;
  }
}

export function object(): ObjectType {
  return new ObjectType();
}
