<template>
  <header class="page-header">
    <div class="header-conainer">
      <div class="logo">
        <a href="#" @click="retrunToHomePage"><img src="../assets/logo_transparent.png" /></a>
      </div>
      <div class="logIn"><LoginButton @show-options="$emit('show-options')" @login-user="$emit('login-user')"/></div>
    </div>
  </header>
</template>
<script>
import LoginButton from "@/components/LoginButton.vue";
import UserOptions from "@/components/UserOptions.vue";
export default {
   watch:{
    '$route'(to,from){
    }
    },
  components: {
    LoginButton,
    UserOptions
  },
  emits:['login-user','show-options'],
    methods:{
        retrunToHomePage(){
            this.$router.push({ path: '/' });
        },
        openLogin(){},
        test(){
          console.log('aaa');
        }
    }
};
</script>
<style scoped>
img {
  width: 200px;
}
div.logo {
  margin-top: -30px;
  margin-bottom: -30px;
  flex: 1;
}
.page-header {
  
  background-color: #5E639B;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
}
.header-conainer{
	display: flex;
	flex-direction: row;
	align-items: center;
	width: 80%;
	margin: auto;
}
</style>