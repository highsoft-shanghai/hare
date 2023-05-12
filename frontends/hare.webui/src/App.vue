<template>
  <router-view />
</template>

<script setup lang="ts">
import { useMeta } from 'quasar';
import { globals } from "commons/global/globals";

useMeta(() => {
  return { title: globals.application.title.toString() }
});
</script>
