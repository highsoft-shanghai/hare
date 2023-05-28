/*
 * TypeScript无法很好的处理循环编译依赖，对于Type uses WithDefault且WithDefault extends Type这样的循环编译依赖会导致编译失败，故需要通过某种间接机制来解除这种编译期循环依赖, 此模块即是这样的中间机制。
 */

import {Type} from './Type';

let withDefaultFactory: (type: Type<unknown>, defaultValue: unknown) => Type<unknown>;
let allowOptionalFactory: (type: Type<unknown>) => Type<unknown>;

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
