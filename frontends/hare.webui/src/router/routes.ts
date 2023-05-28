import {RouteRecordRaw} from 'vue-router';
import {Authorities} from 'commons/context/Authorities';

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    name: 'layout.main',
    component: () => import('layouts/main/MainLayoutControl.vue'),
    children: [
      {path: '/', name: 'route.home', meta: {requiredAuthorities: [Authorities.AUTHENTICATED]}, component: () => import('pages/home/HomePage.vue')},
    ],
  },

  {
    path: '/',
    name: 'layout.blank',
    component: () => import('layouts/blank/BlankLayoutControl.vue'),
    children: [
      {path: '/login', name: 'route.login', meta: {requiredAuthorities: [Authorities.ANONYMOUS]}, component: () => import('pages/login/LoginPage.vue')}
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    meta: {requiredAuthorities: [Authorities.ANONYMOUS]},
    component: () => import('pages/ErrorNotFound.vue'),
  },
];

export default routes;
