<template>
  <q-btn-group class="page-host-actions-control" flat square stretch>
    <q-btn icon="list" dense @click="model.toggleViewList()"/>
    <q-separator vertical inset dark/>
    <q-btn icon="arrow_upward" dense @click="model.toggleMiniMode()"/>
    <q-separator vertical inset dark/>
    <q-btn :icon="model.fullscreen ? 'fullscreen_exit' : 'fullscreen'" dense @click="model.toggleFullscreen()"/>
  </q-btn-group>
</template>

<script setup lang="ts">
import {UnwrapRef} from 'vue';
import {Application} from 'commons/application/Application';

defineProps<{
  model: UnwrapRef<Application>
}>();
</script>
