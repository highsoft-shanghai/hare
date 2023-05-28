<template>
  <div class="fixed-full bg-primary">
    <q-card class="absolute-center" square style="width: 960px; height: 560px;">
      <q-card-section class="full-height items-start" horizontal>
        <q-img class="full-height" fit="cover" width="560px" :src="login" no-spinner position="20% 50%"/>
        <div class="col-grow q-pa-xl" style="width: 32px">
          <h5 class="text-center q-mb-xl">{{ globals.application.title }}</h5>
          <q-form class="row q-gutter-md" style="width: 100%" autofocus @submit="() => 1">
            <login-name-control class="col-all"/>
            <password-control class="col-all"/>
            <div class="col-all">
              <q-btn class="full-width q-mt-md" color="primary" type="submit">登录</q-btn>
            </div>
          </q-form>
        </div>
      </q-card-section>
    </q-card>
  </div>
</template>

<script setup lang="ts">
import {globals} from 'commons/global/globals';
import login from 'assets/login.png';
import LoginNameControl from 'components/login/LoginNameControl.vue';
import PasswordControl from 'components/login/PasswordControl.vue';
</script>
