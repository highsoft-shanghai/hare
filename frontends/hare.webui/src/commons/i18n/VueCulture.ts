import messages from 'src/i18n';
import { createI18n, I18n } from 'vue-i18n';
import { App } from "vue";

export class VueCulture {
  private static _instance: I18n<unknown, unknown, unknown, false>;

  public static initialize(app: App): void {
    VueCulture._instance = createI18n({
      legacy: false,
      globalInjection: true,
      locale: 'zh-CN',
      messages,
    });
    app.use(VueCulture.instance);
  }

  public static translate(code: string, ...args: unknown[]): string {
    return (VueCulture.instance.global as { t: (code: string, ...args: unknown[]) => string }).t(code, args);
  }

  public static get instance(): I18n<unknown, unknown, unknown, false> {
    return this._instance;
  }
}
