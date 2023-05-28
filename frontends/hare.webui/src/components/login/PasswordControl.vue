<template>
  <text-input-control :model="model" type="password">
    <template #prepend>
      <q-icon name="lock_open"/>
    </template>
  </text-input-control>
</template>
<script setup lang="ts">
import TextInputControl from 'components/base/TextInputControl.vue';
import {UnwrapRef} from 'vue';
import {TextInputModel} from 'components/base/TextInputModel';

defineProps<{
  model: UnwrapRef<TextInputModel>
}>();
</script>
