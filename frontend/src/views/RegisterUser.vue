<template>
    <div class="home-container">
    <RegisterForm/>
    </div>
</template>

<script>
import RegisterForm from '@/components/RegisterForm.vue'
export default {
    components:{
        RegisterForm
    },
     watch:{
    '$route'(to,from){
    }
  },
}
</script>

<style scoped>
.home-container {
	/* padding-top: 10px; */
	position: relative;
	flex: 1;
	display: flex;
	flex-direction: column;
    background-color: #D7E2F8;
	/* width: 80%; */
	/* margin: auto; */
	/* overflow: auto; */
}
</style>