<template>
  <q-input :label="model.label" :type="type" hint="" :hide-hint="false" :model-value="model.value.value" @update:model-value="e => model.handleInput(e)">
    <template v-if="$slots.prepend" #prepend>
      <slot name="prepend"/>
    </template>
  </q-input>
</template>
<script setup lang="ts">
import {UnwrapRef} from 'vue';
import {TextInputModel} from 'components/base/TextInputModel';
import {TextInputNativeType} from 'components/base/TextInputNativeType';

defineProps<{
  model: UnwrapRef<TextInputModel>,
  type?: TextInputNativeType
}>();
</script>
