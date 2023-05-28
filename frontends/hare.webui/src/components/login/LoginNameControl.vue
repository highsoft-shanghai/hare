<template>
  <q-input :label="model.label.toString()" hint="" :hide-hint="false" :model-value="model.value" @update:model-value="e => model.handleInput(e)">
    <template #prepend>
      <q-icon name="perm_identity"/>
    </template>
  </q-input>
</template>
<script setup lang="ts">
import {UnwrapRef} from 'vue';
import {LoginNameModel} from 'components/login/LoginNameModel';

defineProps<{
  model: UnwrapRef<LoginNameModel>
}>();
</script>
