<template>

<div class="app" @click="hideOptions" id="appContainer">
    <Header @login-user="loginUser" @show-options="showOptions"/>
    <UserOptions class="options" @create-restaurant="hideDialog" @user-container-click="userContainerClick" v-bind:class="{ clicked: !show }"/>
    <router-view/>
</div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header.vue";
import UserOptions from "@/components/UserOptions.vue";

export default {
	name: "App",
    data(){
        return{
            show:false,
            buttonClick:false,
        }
    },
	components: {
		Header,
        UserOptions
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
        }
    }
};
</script>
<style>
    .app{
        font-family: 'Quicksand', sans-serif;   
         position: relative;
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
        z-index: 2;
        top: 100px;
    }
    .clicked{
        display: none;
    }
</style>