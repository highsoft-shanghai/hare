<template>
  <div>
    <q-btn class="full-width q-mt-md" :disable="!submittable" color="primary" type="submit">登录</q-btn>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  submittable: boolean
}>();
</script>
