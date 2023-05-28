<template>
  <q-input :label="model.label" hint="" :hide-hint="false" :model-value="model.value" @update:model-value="e => model.handleInput(e)">
    <template v-if="$slots.prepend" #prepend>
      <slot name="prepend"/>
    </template>
  </q-input>
</template>
<script setup lang="ts">
import {UnwrapRef} from 'vue';
import {TextInputModel} from 'components/base/TextInputModel';

defineProps<{
  model: UnwrapRef<TextInputModel>
}>();
</script>
