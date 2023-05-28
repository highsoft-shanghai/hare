<template>
  <div class="text-negative">{{ model?.reason }}</div>
</template>

<script setup lang="ts">
import {AuthenticationResult} from 'commons/context/AuthenticationResult';
import {UnwrapRef} from 'vue';

defineProps<{
  model: UnwrapRef<AuthenticationResult | undefined>
}>();
</script>
