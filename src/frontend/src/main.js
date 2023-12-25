import { createApp } from 'vue';
import bootstrap from 'bootstrap/dist/css/bootstrap.min.css';
import { createRouter, createWebHashHistory } from 'vue-router';
import main from "@/components/main.vue";

const router = createRouter({
    mode: 'history',
    history: createWebHashHistory(),
    // base: __dirname,
    routes: [
        { path: '/', component: main }
    ]
});

const app = createApp({
    template: `
    <div>
      
      <router-view class="view"></router-view>
    </div>
  `
});

app.use(router);
app.mount("#app");
