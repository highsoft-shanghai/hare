<template>
  <q-layout view="lHh Lpr lFf">
    <q-header bordered>
      <application-header-control :model="application" />
      <page-host-control :model="application" />
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import ApplicationHeaderControl from 'layouts/ApplicationHeaderControl.vue';
import PageHostControl from "layouts/PageHostControl.vue";
import { globals } from "commons/global/globals";

const application = globals.application;
</script>
