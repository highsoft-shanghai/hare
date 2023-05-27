import {requiredAuthorities, RequiredAuthorities} from 'commons/context/RequiredAuthorities';
import {RouteLocationNormalized} from 'vue-router';

export function requiredAuthoritiesFromRoute(route: RouteLocationNormalized): RequiredAuthorities {
  return requiredAuthorities(...(route.meta?.['requiredAuthorities'] as string[])); // TODO: use payload instead
}
