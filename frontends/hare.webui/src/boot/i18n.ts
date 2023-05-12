import { boot } from 'quasar/wrappers';
import { Translator } from 'src/commons/i18n/Translator';
import { initializeGlobals } from "src/initialize";

export default boot(({ app }) => {
  initializeGlobals();
  Translator.initialize();
  app.use(Translator.instance);
});
