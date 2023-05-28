<template>
  <text-input-control :model="model">
    <template #prepend>
      <q-icon name="perm_identity"/>
    </template>
  </text-input-control>
</template>

<script setup lang="ts">
import {UnwrapRef} from 'vue';
import {TextInputModel} from 'components/base/TextInputModel';
import TextInputControl from 'components/base/TextInputControl.vue';

defineProps<{
  model: UnwrapRef<TextInputModel>
}>();
</script>
