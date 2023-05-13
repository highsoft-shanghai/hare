<template>
  <q-tabs class="start-menu-group-list-control" vertical inline-label>
    <q-tab v-for="group in model.groups" :key="group.key" :name="group.key" :label="group.label" :icon="group.icon"/>
  </q-tabs>
</template>

<script setup lang="ts">
import {UnwrapRef} from 'vue';
import {StartMenu} from 'layouts/StartMenu';

defineProps<{
  model: UnwrapRef<StartMenu>
}>();
</script>
