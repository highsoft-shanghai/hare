import { boot } from 'quasar/wrappers';
import { VueCulture } from 'commons/i18n/VueCulture';
import { initializeGlobals } from 'src/initialize';

export default boot(({ app }) => {
  initializeGlobals(app);
  app.use(VueCulture.instance);
});
