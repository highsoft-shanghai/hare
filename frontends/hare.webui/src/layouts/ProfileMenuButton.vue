<template>
  <q-btn class="profile-menu-button" flat dense stretch square @click="model.toggleProfileMenu()">
    <q-icon name="account_circle" size="32px"/>
  </q-btn>
</template>

<script setup lang="ts">
import {UnwrapRef} from 'vue';
import {ApplicationModel} from 'layouts/ApplicationModel';

defineProps<{
  model: UnwrapRef<ApplicationModel>
}>();
</script>
