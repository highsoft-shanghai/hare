<template>
  <div class="row no-wrap">
    <q-tabs class="page-host-control col-shrink col-grow" dense align="left">
      <q-route-tab label="首页" to="/" exact replace />
      <q-route-tab label="任务" to="/todos" exact replace />
    </q-tabs>
    <q-space />
    <page-host-actions-control :model="model" />
  </div>
</template>

<script setup lang="ts">
import { UnwrapRef } from 'vue';
import { Application } from 'layouts/Application';
import PageHostActionsControl from "layouts/PageHostActionsControl.vue";

defineProps<{
  model: UnwrapRef<Application>
}>();
</script>
