<template>
  <q-dialog :model-value="model.startMenu.visible" maximized seamless transition-show="fade" transition-hide="fade" @keyup.esc="model.startMenu.close()">
    <q-layout view="hHh LpR fFf">
      <q-header bordered>
        <start-menu-header-control :model="model"/>
      </q-header>
      <q-page-container class="bg-primary q-layout__section--marginal">
        <q-page class="row items-stretch" :style-fn="pageStyle">
          <div class="col-12 row no-wrap items-stretch q-px-lg q-pb-lg q-pt-md" style="height: 100%!important;">
            <start-menu-group-list-control :model="model.startMenu"/>
            <q-separator vertical dark style="margin-left: -1px"/>
            <start-menu-item-list-control :model="model.startMenu"/>
          </div>
        </q-page>
      </q-page-container>
    </q-layout>
  </q-dialog>
</template>

<script setup lang="ts">
import {UnwrapRef} from 'vue';
import StartMenuHeaderControl from 'layouts/StartMenuHeaderControl.vue';
import {Application} from 'layouts/Application';
import StartMenuGroupListControl from 'layouts/StartMenuGroupListControl.vue';
import StartMenuItemListControl from 'layouts/StartMenuItemListControl.vue';

defineProps<{
  model: UnwrapRef<Application>
}>();

const pageStyle = (offset: number, height: number) => {
  return ({
    height: `${height - offset}px`
  });
};
</script>
