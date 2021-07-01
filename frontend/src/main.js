import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import VModal from 'vue-js-modal'
import Vuex from 'vuex'

Vue.use(Vuex)
Vue.use(VModal)
import { Map, TileLayer, OsmSource, Geoloc } from 'vuelayers'
import 'vuelayers/lib/style.css' // needs css-loader
Vue.use(Map)
Vue.use(TileLayer)
Vue.use(OsmSource)
Vue.use(Geoloc)
createApp(App).use(router).mount('#app')