<template>
    <header class="page-header">
        <div class="logo">
           <a href="#" @click="retrunToHomePage"><img src="../assets/logo_transparent.png"></a>
        </div>
        <div class="logIn"><LoginButton @login-user="$emit('login-user')"/></div>
    </header>
</template>
<script>
    import LoginButton from '@/components/LoginButton.vue';
    export default {
    watch:{
    '$route'(to,from){
    }
    },
    components: {
        LoginButton
    },
    emits:['login-user'],
    methods:{
        retrunToHomePage(){
            this.$router.push({ path: '/' });
        }
    }
    }
</script>
<style scoped>
    img{
        width: 200px;
    }
    div.logo{
        margin-left:70px;
        margin-top:-30px;
        display:inline-block;margin-right:10px;
    }
    div.logIn{
       display:inline-block; 
      position:absolute;
      right:0px;
      width:150px;
      margin-top:30px;
    }
</style>