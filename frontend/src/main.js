import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import VModal from 'vue-js-modal'

Vue.use(VModal)
createApp(App).use(router).mount('#app')

const bus=new Vue();