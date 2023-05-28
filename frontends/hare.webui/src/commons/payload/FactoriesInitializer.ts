import {setAllowOptionalFactory, setWithDefaultFactory} from './Factories';
import {WithDefault} from './WithDefault';
import {AllowOptional} from './AllowOptional';

/**
 * 初始化Payload相关编译期循环依赖处理中间机制，请在应用的入口和测试的入口调用此函数。
 * @see setWithDefaultFactory
 * @see setAllowOptionalFactory
 */
export function initializePayloadFactories(): void {
  setWithDefaultFactory((type, defaultValue) => new WithDefault(type, defaultValue));
  setAllowOptionalFactory((type) => new AllowOptional(type));
}
