<template>
  <q-dialog :model-value="model.visible" maximized seamless transition-show="fade" transition-hide="fade" @keyup.esc="model.close()">
    <q-layout view="lHh Lpr lFf">
      <q-header class="row no-wrap">
        <q-btn flat dense stretch icon="arrow_back" @click="model.toggleVisible()"/>
        <h6 class="q-ma-none">开始</h6>
      </q-header>
      <q-page-container class="bg-primary">
        <q-page>
          <div>此功能正在建设中，敬请期待……</div>
        </q-page>
      </q-page-container>
    </q-layout>
  </q-dialog>
</template>

<script setup lang="ts">
import {StartMenuModel} from 'layouts/StartMenuModel';
import {UnwrapRef} from 'vue';

defineProps<{
  model: UnwrapRef<StartMenuModel>
}>();
</script>
