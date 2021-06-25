<template>
    <div>
        <Header/>
        <LoginElement class="login"/>
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