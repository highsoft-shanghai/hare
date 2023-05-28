import axios from 'axios';

const api = axios.create({baseURL: '/api'});

api.interceptors.response.use(response => {
  return response.data;
});

export {api};
