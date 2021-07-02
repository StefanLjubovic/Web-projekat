import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Register from '../views/RegisterUser.vue'
import RestaurantReview from '../views/RestaurantReview.vue'
import CreateRestaurant from '../views/CreateRestaurant.vue'
import EditProfile from '../views/EditProfile.vue'
import ListOfAllUsers from '../views/ListOfAllUsers.vue'
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
    path: '/restaurant/:id',
    name: 'Restaurant',
    component: RestaurantReview
  },
  {
    path: '/create-restaurant',
    name: 'CreateRestaurant',
    component: CreateRestaurant
  },
  {
    path: '/profile',
    name: 'EditProfile',
    component: EditProfile
  },
  {
    path: '/user-list',
    name: 'ListOfAllUsers',
    component: ListOfAllUsers
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
