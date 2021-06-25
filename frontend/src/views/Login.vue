<template>
    <div>
        <Header/>
        <div class="login-container">
        <LoginElement class="login"/>
        </div>
    </div>
</template>

<script>
import Header from '@/components/Header.vue'
import LoginElement from '@/components/LoginElement.vue'
export default {
    watch:{
    '$route'(to,from){
    }
  },
  name: 'Login',
  components: {
    Header,
    LoginElement
  }
  
}
</script>

<style scoped>

.login-container {
	/* padding-top: 10px; */
    background-color: #D7E2F8;
	/* width: 80%; */
	/* margin: auto; */
	/* overflow: auto; */
}
</style>
