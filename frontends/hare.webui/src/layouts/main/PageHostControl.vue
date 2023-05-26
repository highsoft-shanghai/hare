<template>
  <div class="row no-wrap">
    <q-tabs class="page-host-control col-shrink col-grow" dense align="left">
      <q-route-tab v-for="(page, index) in model.pageHost.pages" :key="index" :label="page.title.toString()" :to="{name: page.key.code}" exact replace />
    </q-tabs>
    <q-space />
    <page-host-actions-control :model="model" />
  </div>
</template>

<script setup lang="ts">
import { UnwrapRef } from 'vue';
import { Application } from 'commons/application/Application';
import PageHostActionsControl from 'layouts/main/PageHostActionsControl.vue';

defineProps<{
  model: UnwrapRef<Application>
}>();
</script>
