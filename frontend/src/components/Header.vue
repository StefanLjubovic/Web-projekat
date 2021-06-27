<template>
  <header class="page-header">
    <div class="header-conainer">
      <div class="logo-container">
        <a href="#" @click="retrunToHomePage"><img src="../assets/logo.svg" class="logo" /></a>
        <div class="header-title">
          <h3>FoodDoor</h3>
          <p for="">Start a day with us</p>
        </div>
      </div>
      <div class="logIn"><LoginButton @login-user="$emit('login-user')"/></div>
    </div>
  </header>
</template>
<script>
import LoginButton from "@/components/LoginButton.vue";
export default {
   watch:{
    '$route'(to,from){
    }
    },
  components: {
    LoginButton,
  },
  emits:['login-user'],
    methods:{
        retrunToHomePage(){
            this.$router.push({ path: '/' });
        },
        openLogin(){}
    }
};
</script>
<style scoped>
img {
  width: 200px;
}
div.logo-container {
  flex: 1;
  display: flex;
  flex-direction: row;
  align-items: center;
}
.header-title{
  padding-left: 10px;
}
.header-title > h3{
  margin: 0;
  color: #FDDF6D;
}
.header-title > p{
  margin: unset;
  color: #A1B8E6;
  /* opacity: 0.8; */
}
.logo{
  height: 70px;
  width: 70px;
  color: #FDDF6D;
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