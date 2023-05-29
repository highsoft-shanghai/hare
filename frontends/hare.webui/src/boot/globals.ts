import {boot} from 'quasar/wrappers';
import {initializeGlobals} from 'src/initialize';

export default boot(({app}) => {
  initializeGlobals(app);
});
