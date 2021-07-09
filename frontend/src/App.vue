<template>
    <div class="app" @click="hideOptions"  id="appContainer">
        <Header @login-user="loginUser" @show-options="showOptions" @show-cart="showCartDialog" />
        <UserOptions  class="options" @hideDialog="hideDialog"  @edit-profile="editProfile" v-bind:class="{ clicked: !show }"/>
        <CartDialog  class="cart" v-bind:class="{ clicked: !showCart }"/>
        <router-view />
    </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header.vue";
import UserOptions from "@/components/UserOptions.vue";
import CartDialog from "@/components/Cart/CartDialog.vue"
import { mapMutations, mapGetters } from "vuex";
import Server from './server'
export default {
	name: "App",
    data(){
        return{
            show:false,
            buttonClick:false,
            showCart:false,
            cartClick:false,
        }
    },
	components: {
		Header,
        UserOptions,
        CartDialog
	},
    methods:{
        loginUser(){
      		this.$router.push({ path: '/login' });
    	},userContainerClick(){
            this.buttonClick=true;
        },
        showOptions(){
            this.buttonClick=true;
            this.show=!this.show;
            this.cartClick = false;
            this.showCart  = false;
        },
        showCartDialog(){
            console.log("Click cart");
            this.cartClick = !this.cartClick;
            this.showCart  = !this.showCart;
            this.buttonClick = false;
            this.show = false;
        },
        hideOptions(){
            if(!this.buttonClick)
                this.show    = false;
            if(!this.cartClick)
                this.showCart = false;
            this.buttonClick = false;
            this.cartClick   = false;
        },
        hideDialog(){
            this.show=false;
        },
        editProfile(){
            this.show=false;
            this.$router.push({ path: '/profile' });
        },
        ...mapMutations({ setUser: "setUser" })
    },
    async mounted(){
        const token = localStorage.getItem("token");
        if(!!token){
            const resp = await Server.getUserByToken(token);
            if(resp.success){
                const data = resp.data;
                const user = JSON.parse(data['user'])
                const token = data['loginToken']
                localStorage.setItem("token", token);
                this.$store.commit("setUser", user)
            }
        }
        const cartString = localStorage.getItem("cart");
        if(!!cartString){
            const cart = JSON.parse(cartString);
            this.$store.commit("setCart", cart)
        }
    }
};
</script>
<style scoped>
    .app{
        font-family: 'Quicksand', sans-serif;   
         /* position: relative; */
    }
    .app input:focus{
        outline: none !important;
    }
    ::placeholder { /* Chrome, Firefox, Opera, Safari 10.1+ */
        color: white;
        opacity: 0.5; /* Firefox */
    }

    :-ms-input-placeholder { /* Internet Explorer 10-11 */
        color: white;
        opacity: 0.5; /* Firefox */
    }

    ::-ms-input-placeholder { /* Microsoft Edge */
        color: white;
        opacity: 0.5; /* Firefox */
    }
    .options{
        position: absolute;
        right: 20px;
        z-index: 12;
        top: 60px;
    }
    .cart{
        position: absolute;
        right: 145px;
        z-index: 12;
        top: 50px;
    }
    .clicked{
        display: none;
    }
</style>