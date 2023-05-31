import {Value} from 'commons/domain/Value';

export function deepValueAsData(value: unknown): unknown {
  if (value instanceof Value) return value.asData();
  return value;
}
