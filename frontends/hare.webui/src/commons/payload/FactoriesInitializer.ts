import {setAllowOptionalFactory, setWithDefaultFactory} from './Factories';
import {Type} from './Type';
import {WithDefault} from './WithDefault';
import {AllowOptional} from './AllowOptional';

export function initializePayloadFactories(): void {
  setWithDefaultFactory((type: Type<unknown>, defaultValue: unknown) => new WithDefault(type, defaultValue));
  setAllowOptionalFactory((type: Type<unknown>) => new AllowOptional(type));
}
