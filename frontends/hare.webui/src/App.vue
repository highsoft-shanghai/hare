<template>
  <router-view/>
</template>

<script setup lang="ts">
import {useMeta} from 'quasar';
import {application} from 'layouts/Application';

useMeta(() => {
  return {title: application.title.toString()}
});
</script>
