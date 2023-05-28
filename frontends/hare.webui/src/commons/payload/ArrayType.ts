import {Payload} from './Payload';
import {Type} from './Type';

export class ArrayType extends Type<Payload[]> {
  public convert(path: string, value: unknown): Payload[] {
    if (!Array.isArray(value)) throw new Error(`type mismatch: require 'array' but got '${typeof value}', in path '${path}'`);
    return (value as unknown[]).map((x, index) => new Payload(`${path}[${index}]`, x));
  }
}

export function array(): ArrayType {
  return new ArrayType();
}
