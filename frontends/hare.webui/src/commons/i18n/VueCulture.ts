import messages from 'src/i18n';
import {createI18n, I18n, I18nOptions} from 'vue-i18n';
import {Culture} from 'commons/i18n/Culture';
import {resettableGlobals} from 'commons/global/globals';

export class VueCulture implements Culture {
  public readonly instance: I18n<unknown, unknown, unknown, unknown, false>;

  public constructor() {
    this.instance = createI18n(this.getOptions());
  }

  public localize(code: string, ...args: unknown[]): string {
    return (this.instance.global as { t: (code: string, ...args: unknown[]) => string }).t(code, args);
  }

  private getOptions(): I18nOptions {
    return {
      legacy: false,
      globalInjection: true,
      locale: 'zh-CN',
      warnHtmlMessage: false,
      messages,
    };
  }
}

export function installGlobalCulture(): void {
  resettableGlobals.resetCulture(new VueCulture());
}
