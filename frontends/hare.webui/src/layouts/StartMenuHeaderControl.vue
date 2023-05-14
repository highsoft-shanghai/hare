<template>
  <div class="row items-center no-wrap q-pa-xs q-pa-sm-md text-no-wrap non-selectable">
    <div class="row no-wrap items-center">
      <q-btn class="start-menu-back-action-control" flat dense stretch icon="arrow_back" @click="model.toggleStartMenu()"/>
      <h6 class="q-ma-none">{{ i18n('label.start') }}</h6>
    </div>
    <q-separator class="q-mx-md" vertical inset dark/>
    <q-toolbar-title class="q-pl-none xs-hide">{{ model.title }}</q-toolbar-title>
    <q-input class="start-menu-search-box-control q-mr-xs col-grow col-sm-4" dense rounded standout dark placeholder="建设中……">
      <template v-slot:prepend>
        <q-icon name="search"/>
      </template>
    </q-input>
  </div>
</template>

<script setup lang="ts">
import {UnwrapRef} from 'vue';
import {Application} from 'layouts/Application';
import {i18n} from 'commons/i18n/i18n';

defineProps<{
  model: UnwrapRef<Application>
}>();
</script>
