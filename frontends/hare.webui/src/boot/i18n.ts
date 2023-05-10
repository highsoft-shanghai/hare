import {boot} from 'quasar/wrappers';
import {Translator} from 'src/commons/i18n/Translator';

export default boot(({app}) => {
  Translator.initialize();
  app.use(Translator.instance);
});
