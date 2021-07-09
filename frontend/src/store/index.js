import { createStore } from 'vuex'
// Vue.use(Vuex)

export default createStore({
  state: {
    user: {},
    cart:{}
  },
  mutations: {
    //syncrous
    setUser(state, payload) {
      console.log('Setting user');
      state.user = payload;
    },
    addItem(state,item){
      state.cart.items = !!state.cart?.items ? state.cart.items : [];
      state.cart.items.push(item);
    },
    addItems(state, items){
      state.cart.items = !!state.cart?.items ? state.cart.items : [];
      for(const item of items)
        state.cart.items.push(item);
      console.log(`Added ${items.length} items...`);
      console.log(`Total size is ${state.cart.items.length} items...`);
    },
    removeItem(state,index){
      state.cart.items.splice(index, 1);
    },
    clearCart(state){
      state.cart = {}
    }
  },
  actions: {
    //asyncronous
    
  },
  modules: {},
  getters: {
    getUser: (state) => state.user,
    getCart: (state) => state.cart
  }
});
