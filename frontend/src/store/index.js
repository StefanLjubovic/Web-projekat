import { createStore } from 'vuex'
// Vue.use(Vuex)

export default createStore({
  state: {
    user: {},
  },
  mutations: {
    //syncrous
    setUser(state, payload) {
      console.log('Setting user');
      state.user = payload;
    },
  },
  actions: {
    //asyncronous
    
  },
  modules: {},
  getters: {
    getUser: (state) => state.user
  }
});
