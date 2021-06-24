<template>
    <header class="page-header">
        <div class="logo">
           <a href="#"><img src="../assets/logo_transparent.png"></a>
        </div>
        <div class="logIn"><LoginButton/></div>
    </header>
</template>
<script>
    import LoginButton from '@/components/LoginButton.vue';
    export default {
    components: {
        LoginButton
    },
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