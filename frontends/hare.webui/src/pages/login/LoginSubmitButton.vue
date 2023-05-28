<template>
  <div>
    <q-btn :loading="loading" class="full-width q-mt-md" :disable="!submittable" color="primary" type="submit">登录</q-btn>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  submittable: boolean,
  loading?: boolean
}>();
</script>
