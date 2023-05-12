<template>
  <q-layout view="lHh Lpr lFf">
    <q-header bordered>
      <application-header-control :model="application" />
      <application-tab-bar :model="application" />
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import ApplicationHeaderControl from 'layouts/ApplicationHeaderControl.vue';
import ApplicationTabBar from 'layouts/ApplicationTabBar.vue';
import { application } from 'layouts/Application';
</script>
