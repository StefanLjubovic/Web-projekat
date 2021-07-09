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
      <div class="logIn"><LoginButton @show-options="$emit('show-options')" @login-user="openLogin" @show-cart="$emit('show-cart')"/></div>
    </div>
  </header>
  <LoginModal :form-type="formType" v-if="showLoginModal" @close="closeLogin"/>
</template>
<script>
import LoginButton from "@/components/LoginButton.vue";
import UserOptions from "@/components/UserOptions.vue";
import LoginModal from "@/components/Login/LoginModal.vue"
export default {
  data(){
    return{
      showLoginModal: false,
      formType:'login'
    }
  },
   watch:{
    '$route'(to,from){
    }
    },
  components: {
    LoginButton,
    UserOptions,
    LoginModal
  },
  emits:['login-user','show-options','show-cart', 'click'],
    methods:{
        retrunToHomePage(){
            this.$router.push('/')
        },
        openLogin(){
          this.showLoginModal = true;
          document.getElementById('appContainer').style.overflow = 'hidden';
          document.getElementById('appContainer').style.height = '100vh';
        },
        closeLogin(){
          this.showLoginModal = false;
          document.getElementById('appContainer').style.overflow = 'unset';
          document.getElementById('appContainer').style.height = 'unset';
        }
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