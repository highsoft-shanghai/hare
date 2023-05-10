<template>
  <q-toolbar class="q-pa-none application-header-bar">
    <start-button :model="model.startMenu"/>
    <application-title :model="model"/>
    <q-space/>
    <profile-menu-button :model="model"/>
  </q-toolbar>
</template>

<script setup lang="ts">
import {ApplicationModel} from 'layouts/ApplicationModel';
import {UnwrapRef} from 'vue';
import StartButton from 'layouts/StartButton.vue';
import ProfileMenuButton from 'layouts/ProfileMenuButton.vue';
import ApplicationTitle from 'layouts/ApplicationTitle.vue';

defineProps<{
  model: UnwrapRef<ApplicationModel>
}>();
</script>
