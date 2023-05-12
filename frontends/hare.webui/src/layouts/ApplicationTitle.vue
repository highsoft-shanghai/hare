<template>
  <q-toolbar-title class="q-pl-xs application-title">{{ model.title }}</q-toolbar-title>
</template>

<script setup lang="ts">
import {UnwrapRef} from 'vue';
import {Application} from 'layouts/Application';

defineProps<{
  model: UnwrapRef<Application>
}>();
</script>
