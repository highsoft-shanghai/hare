import {Type} from './Type';

let withDefaultFactory: (type: Type<unknown>, defaultValue: unknown) => Type<unknown>
let allowOptionalFactory: (type: Type<unknown>) => Type<unknown>

export function withDefault<T>(type: Type<T>, defaultValue: T): Type<T> {
  return withDefaultFactory(type, defaultValue) as Type<T>;
}

export function allowOptional<T>(type: Type<T>): Type<T | undefined> {
  return allowOptionalFactory(type) as Type<T | undefined>;
}

export function setWithDefaultFactory<T>(factory: (type: Type<T>, defaultValue: T) => Type<T>): void {
  withDefaultFactory = factory as (type: Type<unknown>, defaultValue: unknown) => Type<unknown>;
}

export function setAllowOptionalFactory<T>(factory: (type: Type<T>) => Type<T | undefined>): void {
  allowOptionalFactory = factory as (type: Type<unknown>) => Type<unknown>;
}
