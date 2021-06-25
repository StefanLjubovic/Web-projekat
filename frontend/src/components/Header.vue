<template>
  <header class="page-header">
    <div class="header-conainer">
      <div class="logo">
        <a href="#"><img src="../assets/logo_transparent.png" /></a>
      </div>
      <div class="logIn"><LoginButton /></div>
    </div>
  </header>
</template>
<script>
import LoginButton from "@/components/LoginButton.vue";
export default {
  components: {
    LoginButton,
  },
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
div.logIn {
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