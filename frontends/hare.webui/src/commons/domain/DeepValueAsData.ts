import {Value} from 'commons/domain/Value';

export function deepValueAsData(value: unknown): unknown {
  if (Array.isArray(value)) return value.map(x => deepValueAsData(x));
  if (value instanceof Value) return value.asData();
  return value;
}
