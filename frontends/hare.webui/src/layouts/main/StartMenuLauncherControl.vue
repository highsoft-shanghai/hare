<template>
  <q-btn class="start-menu-launcher-control" flat dense square icon="menu" aria-label="Menu" @click="model.toggleStartMenu()">
    <start-menu-control :model="model"/>
  </q-btn>
</template>

<script setup lang="ts">
import StartMenuControl from 'layouts/main/StartMenuControl.vue';
import {UnwrapRef} from 'vue';
import {Application} from 'commons/application/Application';

defineProps<{
  model: UnwrapRef<Application>;
}>();
</script>
