import { createRouter, createWebHistory } from 'vue-router'
import FolderView from '../views/FolderView.vue'


const routes = [
  // {
  //   path: '/',
  //   name: 'home',
  //   component: HomeView
  // },
  {
    path: '/about/id',
    name: 'about',
  },
  {
    path: '/folder/:folderName',
    name: 'folder',
    props: true,
    component: FolderView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
