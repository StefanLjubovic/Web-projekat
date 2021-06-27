import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Register from '../views/RegisterUser.vue'
import RestaurantReview from '../views/RestaurantReview.vue'
import CreateRestaurant from '../views/CreateRestaurant.vue'
const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/restaurant',
    name: 'Restaurant',
    component: RestaurantReview
  },
  {
    path: '/create-estaurant',
    name: 'CreateRestaurant',
    component: CreateRestaurant
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
