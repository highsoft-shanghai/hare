<template>
  <div class="row no-wrap">
    <q-tabs class="application-tab-bar col-shrink col-grow" dense align="left">
      <q-route-tab label="首页" to="/" exact replace/>
      <q-route-tab label="任务" to="/todos" exact replace/>
    </q-tabs>
    <q-space/>
    <application-tab-bar-actions :model="model"/>
  </div>
</template>

<script setup lang="ts">
import ApplicationTabBarActions from 'layouts/ApplicationTabBarActions.vue';
import {UnwrapRef} from 'vue';
import {Application} from 'layouts/Application';

defineProps<{
  model: UnwrapRef<Application>
}>();
</script>
