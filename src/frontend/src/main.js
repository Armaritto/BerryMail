import { createApp } from 'vue';
import router from './router'
// import { createRouter, createWebHashHistory } from 'vue-router';

import App from './App.vue'

// const router = createRouter({
//     mode: 'history',
//     history: createWebHashHistory(),
//     // base: __dirname,
//     routes: [
//         { path: '/', component: main }
//     ]
// });

const app = createApp(App);

app.use(router);
app.mount("#app");
