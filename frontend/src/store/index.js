import { createStore } from 'vuex'
// Vue.use(Vuex)

export default createStore({
  state: {
    user: {},
    cart:{}
  },
  methods:{
    getLastIndex(list, item){
      let index = -1;
      for(let i=0;i<list.length;i++){
          if(list[i].name == item.name){
            index = i;
          }
      }
      return index;
    },
  },
  mutations: {
    //syncrous
    setUser(state, payload) {
      console.log('Setting user');
      state.user = payload;
    },
    setCart(state, cart){
      state.cart = cart;
    },
    addItem(state,item){
      state.cart.items = !!state.cart?.items ? state.cart.items : [];
      state.cart.items.push(item);
      localStorage.setItem('cart', JSON.stringify(state.cart))
    },
    addItems(state, items){
      state.cart.items = !!state.cart?.items ? state.cart.items : [];
      for(const item of items)
        state.cart.items.push(item);
      console.log(`Added ${items.length} items...`);
      console.log(`Total size is ${state.cart.items.length} items...`);
      localStorage.setItem('cart', JSON.stringify(state.cart))
    },
    removeItem(state,item){
      let index = -1;
      const list = state.cart.items;
      for(let i=0;i<list.length;i++){
          if(list[i].name == item.name){
            index = i;
          }
      }
      const amount = state.cart.items.filter(e => e.name == item.name).length
      if(amount > 1){
        // state.cart.items.splice(index,1);
        state.cart.items.splice(index, 1)
        localStorage.setItem('cart', JSON.stringify(state.cart))
        // console.log(length - index);
        // state.cart.items = newArr;
      }
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
