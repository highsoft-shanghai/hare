<template>
  <q-layout view="lHh Lpr lFf">
    <q-header bordered>
      <application-header-bar :model="application"/>
      <application-tab-bar :model="application"/>
    </q-header>

    <q-page-container>
      <router-view/>
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import ApplicationHeaderBar from 'layouts/ApplicationHeaderBar.vue';
import {application} from 'layouts/ApplicationModel';
import ApplicationTabBar from 'layouts/ApplicationTabBar.vue';
</script>
