<template>
  <q-toolbar class="q-pa-none application-header-control">
    <start-menu-launcher-control :model="model.startMenu" />
    <application-title-control :model="model" />
    <q-space />
    <profile-menu-launcher-control :model="model" />
  </q-toolbar>
</template>

<script setup lang="ts">
import { Application } from 'layouts/Application';
import { UnwrapRef } from 'vue';
import ProfileMenuLauncherControl from 'layouts/ProfileMenuLauncherControl.vue';
import ApplicationTitleControl from "layouts/ApplicationTitleControl.vue";
import StartMenuLauncherControl from "layouts/StartMenuLauncherControl.vue";

defineProps<{
  model: UnwrapRef<Application>
}>();
</script>
