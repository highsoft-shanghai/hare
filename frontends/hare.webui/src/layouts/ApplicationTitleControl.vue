<template>
  <q-toolbar-title class="q-pl-xs application-title-control">{{ model.title }}</q-toolbar-title>
</template>

<script setup lang="ts">
import {UnwrapRef} from 'vue';
import {Application} from 'commons/application/Application';

defineProps<{
  model: UnwrapRef<Application>
}>();
</script>
