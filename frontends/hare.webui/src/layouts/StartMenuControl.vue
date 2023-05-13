<template>
  <q-dialog :model-value="model.startMenu.visible" maximized seamless transition-show="fade" transition-hide="fade" @keyup.esc="model.startMenu.close()">
    <q-layout view="lHh Lpr lFf">
      <q-header>
        <start-menu-header-control :model="model"/>
      </q-header>
      <q-page-container class="bg-primary">
        <q-page class="q-px-lg q-pt-md">
          <div>此功能正在建设中，敬请期待……</div>
        </q-page>
      </q-page-container>
    </q-layout>
  </q-dialog>
</template>

<script setup lang="ts">
import {UnwrapRef} from 'vue';
import StartMenuHeaderControl from 'layouts/StartMenuHeaderControl.vue';
import {Application} from 'layouts/Application';

defineProps<{
  model: UnwrapRef<Application>
}>();
</script>
