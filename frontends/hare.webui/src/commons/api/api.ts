import axios from 'axios';
import {globals} from 'commons/global/globals';

const api = axios.create({baseURL: '/api'});

api.interceptors.request.use(config => {
  if (globals.context.accessToken) {
    config.headers.Authorization = `Bearer ${globals.context.accessToken}`;
  }
  return config;
});

api.interceptors.response.use(response => {
  return response.data;
});

export {api};
