import {boot} from 'quasar/wrappers';
import {initializeGlobals} from 'src/initialize';

export default boot(async ({app}) => {
  initializeGlobals(app);
});
