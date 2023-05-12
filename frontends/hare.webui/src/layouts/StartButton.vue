<template>
  <q-btn class="start-button" flat dense square icon="menu" aria-label="Menu" @click="model.toggleVisible()">
    <start-menu-control :model="model" />
  </q-btn>
</template>

<script setup lang="ts">
import { StartMenuModel } from 'layouts/StartMenuModel';
import StartMenuControl from "layouts/StartMenuControl.vue";
import { UnwrapRef } from 'vue';

defineProps<{
  model: UnwrapRef<StartMenuModel>;
}>();
</script>
