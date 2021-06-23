<template>
  <div class="home">
    <Login @login-user="loginUser"/>
  </div>
</template>

<script>
// @ is an alias to /src
import Login from '@/components/Login.vue'

export default {
  watch:{
    '$route'(to,from){
    }
  },
  
  name: 'Home',
  components: {
    Login
  },
  methods:{
    loginUser(user){
      console.log('aaa')
      this.$router.push('/about')
    }
  }
}
</script>
