<template>
  <div class="fixed-full bg-primary">
    <q-card class="absolute-center" square style="width: 960px; height: 560px;">
      <q-card-section class="full-height items-start" horizontal>
        <q-img class="full-height" fit="cover" width="560px" :src="login" no-spinner position="20% 50%"/>
        <div class="col-grow q-pa-xl" style="width: 32px">
          <h5 class="text-center q-mb-xl">{{ globals.application.title }}</h5>
          <q-form class="row q-gutter-md" style="width: 100%" autofocus @submit="() => model.submit()">
            <username-control :model="model.username" class="col-all"/>
            <password-control :model="model.password" class="col-all"/>
            <login-submit-button :submittable="model.submittable" :loading="model.loading" class="col-all"/>
            <login-result-control class="q-mt-sm" :model="model.lastAuthenticationResult"/>
          </q-form>
        </div>
      </q-card-section>
    </q-card>
  </div>
</template>

<script setup lang="ts">
import {globals} from 'commons/global/globals';
import login from 'assets/login.png';
import PasswordControl from 'components/login/PasswordControl.vue';
import {reactive} from 'vue';
import {LoginModel} from 'pages/login/LoginModel';
import LoginSubmitButton from 'pages/login/LoginSubmitButton.vue';
import {AxiosLoginApi} from 'pages/login/AxiosLoginApi';
import LoginResultControl from 'pages/login/LoginResultControl.vue';
import UsernameControl from 'components/login/UsernameControl.vue';

const model = reactive(new LoginModel(new AxiosLoginApi(), globals.navigator, globals.context));
</script>
