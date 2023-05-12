<template>
  <q-btn class="profile-menu-launcher-control" flat dense stretch square @click="model.toggleProfileMenu()">
    <q-icon name="account_circle" size="32px"/>
  </q-btn>
</template>

<script setup lang="ts">
import {UnwrapRef} from 'vue';
import {Application} from 'layouts/Application';

defineProps<{
  model: UnwrapRef<Application>
}>();
</script>
