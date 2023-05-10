<template>
  <q-btn class="start-button" flat dense square icon="menu" aria-label="Menu" @click="model.toggleVisible()">
    <start-menu :model="model"/>
  </q-btn>
</template>

<script setup lang="ts">
import {StartMenuModel} from 'layouts/StartMenuModel';
import {UnwrapRef} from 'vue';
import StartMenu from 'layouts/StartMenu.vue';

defineProps<{
  model: UnwrapRef<StartMenuModel>;
}>();
</script>
