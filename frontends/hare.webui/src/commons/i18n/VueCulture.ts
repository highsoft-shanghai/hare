import messages from 'src/i18n';
import { createI18n, I18n, I18nOptions } from 'vue-i18n';
import { App } from 'vue';
import { Culture } from 'commons/i18n/Culture';

export class VueCulture extends Culture {
  private readonly instance: I18n<unknown, unknown, unknown, unknown, false>;

  public constructor(app: App) {
    super();
    this.instance = createI18n(this.getOptions());
    app.use(this.instance);
  }

  public localize(code: string, ...args: unknown[]): string {
    return (this.instance.global as { t: (code: string, ...args: unknown[]) => string }).t(code, args);
  }

  private getOptions(): I18nOptions {
    return {
      legacy: false,
      globalInjection: true,
      locale: 'zh-CN',
      messages,
    };
  }
}
