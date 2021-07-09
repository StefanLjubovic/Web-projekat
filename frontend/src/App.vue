<template>
    <div class="app" @click="hideOptions" id="appContainer">
        <Header @login-user="loginUser" @show-options="showOptions"/>
        <UserOptions class="options" @hideDialog="hideDialog"  @edit-profile="editProfile" @openRegistration="registerUser" v-bind:class="{ clicked: !show }"/>
        <LoginModal :form-type="formType" v-if="showLoginModal" @close="closeLogin"/>
        <router-view/>
    </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header.vue";
import UserOptions from "@/components/UserOptions.vue";
import { mapMutations, mapGetters } from "vuex";
import LoginModal from '@/components/Login/LoginModal.vue';
import Server from './server'
export default {
	name: "App",
    data(){
        return{
            show:false,
            buttonClick:false,
            showLoginModal: false,
            formType: 'register',
        }
    },
	components: {
		Header,
        UserOptions,
        LoginModal
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
        },
        hideOptions(){
            if(!this.buttonClick)
                this.show=false;
            this.buttonClick=false;
        },
        hideDialog(){
            this.show=false;
        },
        editProfile(){
            this.show=false;
            this.$router.push({ path: '/profile' });
        },
        registerUser(){
            this.showLoginModal = true;
			document.getElementById('appContainer').style.overflow = 'hidden';
			document.getElementById('appContainer').style.height = '100vh';
		},
		closeLogin(){
			this.showLoginModal = false;
			document.getElementById('appContainer').style.overflow = 'unset';
			document.getElementById('appContainer').style.height = 'unset';
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
    .clicked{
        display: none;
    }
</style>