<template>
  <q-toolbar class="q-pa-none application-header-control">
    <start-button :model="model.startMenu" />
    <application-title-control :model="model" />
    <q-space />
    <profile-menu-button :model="model" />
  </q-toolbar>
</template>

<script setup lang="ts">
import { Application } from 'layouts/Application';
import { UnwrapRef } from 'vue';
import StartButton from 'layouts/StartButton.vue';
import ProfileMenuButton from 'layouts/ProfileMenuButton.vue';
import ApplicationTitleControl from "layouts/ApplicationTitleControl.vue";

defineProps<{
  model: UnwrapRef<Application>
}>();
</script>
