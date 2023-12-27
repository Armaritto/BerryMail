import { createRouter, createWebHistory } from 'vue-router'
import FolderView from '../views/FolderView.vue'
import LoginView from '../views/LoginView.vue'
import SignupView from '../views/SignupView.vue'
import Main from "@/components/Main.vue";
import MailFolder from "@/components/MailFolder.vue";

const routes = [
  // {
  //   path: '/',
  //   name: 'home',
  //   component: HomeView
  // },
  {
    path: '/signup',
    name: 'signup',
    component:SignupView
  },
  {
    path: '/login',
    name: 'login',
    component:LoginView
  },
  {
    path: '/:clientEmail/folder/:folderName',
    name: 'folder',
    props: true,
    component: Main,

    children:[{
      path:'',
      name:'test',
      props: true,
      component: MailFolder
    }]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
