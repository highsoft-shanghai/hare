<template>
  <q-input :label="i18ns('label.login-name')" hint="" :hide-hint="false" model-value="san.zhang@highsoft.ltd">
    <template #prepend>
      <q-icon name="perm_identity"/>
    </template>
  </q-input>
</template>
<script setup lang="ts">
import {i18ns} from 'commons/i18n/i18n';
</script>
