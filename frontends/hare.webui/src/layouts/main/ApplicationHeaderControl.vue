<template>
  <q-toolbar class="q-pa-none application-header-control">
    <start-menu-launcher-control :model="model"/>
    <application-title-control :model="model"/>
    <q-space/>
    <profile-menu-launcher-control :model="model"/>
  </q-toolbar>
</template>

<script setup lang="ts">
import {Application} from 'commons/application/Application';
import {UnwrapRef} from 'vue';
import ProfileMenuLauncherControl from 'layouts/main/ProfileMenuLauncherControl.vue';
import ApplicationTitleControl from 'layouts/main/ApplicationTitleControl.vue';
import StartMenuLauncherControl from 'layouts/main/StartMenuLauncherControl.vue';

defineProps<{
  model: UnwrapRef<Application>
}>();
</script>
