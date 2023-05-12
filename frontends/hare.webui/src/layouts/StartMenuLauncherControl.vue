<template>
  <q-btn class="start-menu-launcher-control" flat dense square icon="menu" aria-label="Menu" @click="model.toggleVisible()">
    <start-menu-control :model="model" />
  </q-btn>
</template>

<script setup lang="ts">
import { StartMenu } from 'layouts/StartMenu';
import StartMenuControl from 'layouts/StartMenuControl.vue';
import { UnwrapRef } from 'vue';

defineProps<{
  model: UnwrapRef<StartMenu>;
}>();
</script>
