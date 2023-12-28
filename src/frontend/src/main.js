import { createApp } from 'vue';
import router from './router'
import VStickyElement from 'vue-sticky-element';
// import VueAwesomePaginate from "vue-awesome-paginate";
// import "vue-awesome-paginate/dist/style.css";


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
app.use(VStickyElement);
app.use(router);
// app.use(VueAwesomePaginate);
app.mount("#app");
