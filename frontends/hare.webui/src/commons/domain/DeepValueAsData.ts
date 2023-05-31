import {Value} from 'commons/domain/Value';

function* map<T, U>(iterable: Iterable<T>, as: (item: T) => U): Iterable<U> {
  for (const item of iterable) {
    yield as(item);
  }
}

export function deepValueAsData(value: unknown): unknown {
  if (Array.isArray(value)) return value.map(x => deepValueAsData(x));
  if (value instanceof Set) return new Set(map(value.values(), item => deepValueAsData(item)));
  if (value instanceof Value) return value.asData();
  return value;
}
