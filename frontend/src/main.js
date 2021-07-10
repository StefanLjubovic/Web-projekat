import { createApp } from 'vue'
import App from './App.vue'
import VModal from 'vue-js-modal'
import Vuex from 'vuex'
// import Notifications from 'vue-notification/dist/ssr.js'
// Vue.use(Vuex)
import store from './store'
import router from './router'
import { Map, TileLayer, OsmSource, Geoloc } from 'vuelayers'
import 'vuelayers/lib/style.css' // needs css-loader
import VTooltip from 'v-tooltip'

import ToggleButton from 'vue-js-toggle-button'
 
Vue.use(ToggleButton)
Vue.use(VTooltip)

import Swal from 'sweetalert2'

Vue.use(VModal)
Vue.use(Map)
// Vue.use(Notifications)
Vue.use(TileLayer)
Vue.use(OsmSource)
Vue.use(Geoloc)

Vue.config.productionTip = false

createApp(App).use(store).use(router).mount('#app')

