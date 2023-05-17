import {RouteRecordRaw} from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    name: 'layout.main',
    component: () => import('layouts/main/MainLayoutControl.vue'),
    children: [
      {path: '/', name: 'router.home', component: () => import('pages/home/HomePage.vue')},
    ],
  },

  {
    path: '/',
    name: 'layout.blank',
    component: () => import('layouts/blank/BlankLayoutControl.vue'),
    children: [
      {path: '/login', name: 'page.login', component: () => import('pages/login/LoginPage.vue')}
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
];

export default routes;
