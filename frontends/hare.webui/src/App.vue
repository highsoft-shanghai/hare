<template>
  <router-view/>
</template>

<script setup lang="ts">
import {useMeta} from 'quasar';
import {globals} from 'commons/global/globals';
import {onMounted} from 'vue';

useMeta(() => {
  return {title: globals.application.title.toString()};
});

onMounted(() => {
  globals.loadingIndicator.hide();
});
</script>
