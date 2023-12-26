import { createRouter, createWebHistory } from 'vue-router'
<<<<<<< Updated upstream
import HomeView from '../views/HomeView.vue'

const routes = [
=======
import FolderView from '../views/FolderView.vue'
import LoginView from '../views/LoginView.vue'
// import SignUpView from '../views/SignUpView.vue'
import Main from "@/components/Main.vue";
import MailFolder from "@/components/MailFolder.vue";

const myRoutes = [
  // {
  //   path: '/',
  //   name: 'home',
  //   component: HomeView
  // },
>>>>>>> Stashed changes
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  // {
  //   path: '/signup',
  //   name: 'signup',
  //   component: SignUpView
  // },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes: myRoutes
})

export default router
