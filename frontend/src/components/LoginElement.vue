<template>
  <div class="login">
    <div class="d-flex justify-content-center h-100">
      <div class="card">
        <div class="card-header">
          <h3>Sign In</h3>
		  <div class="modal-close" @click="$emit('close')">
                        <i class="fas fa-times"></i>
                    </div>
        </div>
        <div class="card-body">
          <form @submit="onSubmit">
            <div class="input-group form-group">
              <div class="input-group-prepend">
                <span class="input-group-text"
                  ><i class="fas fa-user"></i
                ></span>
              </div>
              <input
                name="username"
                v-model="username"
                type="text"
                class="form-control"
                placeholder="username"
              />
            </div>
            <div class="input-group form-group">
              <div class="input-group-prepend">
                <span class="input-group-text"><i class="fas fa-key"></i></span>
              </div>
              <input
                name="password"
                v-model="password"
                type="password"
                class="form-control"
                placeholder="password"
              />
            </div>
            <div class="row align-items-center remember">
              <input type="checkbox" />Remember Me
            </div>
            <div class="form-group">
              <input
                type="submit"
                value="Login"
                class="btn float-right login_btn"
              />
            </div>
          </form>
        </div>
        <div class="card-footer">
          <div class="d-flex justify-content-center links">
            Don't have an account?<a
              href="#"
              class="link-danger"
              @click="signUpUser"
              >Sign Up</a
            >
          </div>
          <div class="d-flex justify-content-center">
            <a href="#" class="link-danger">Forgot your password?</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Server from "@/server/server";
import { mapMutations, mapGetters } from "vuex";
import store from '../store/index'
export default {
  data() {
    return {
      username: "pera",
      password: "111",
    };
  },
  methods: {
    onSubmit(e) {
      e.preventDefault();
      if (!this.username) {
        alert("Plese insert username");
        return;
      }
      const loginUser = {
        username: this.username,
        password: this.password,
      };
      Server.login(loginUser).then(resp => {
        if(resp.success){
          const data = resp.data;
          const user = JSON.parse(data['user'])
          const token = localStorage.setItem("token", token);
          store.commit("setUser", user);
          this.$emit('close')
        }
      })
      // this.$emit("login-user", newUser);
    },
    signUpUser() {
      this.$emit("changeState", "register");
    },
    ...mapMutations({ setUser: "setUser" })
  },
  mounted(){
    // const loginUser = {
    //     username: this.username,
    //     password: this.password,
    //   };
    //   Server.login(loginUser).then(resp => {
    //     console.log(resp);
    //   })
  }
};
</script>


<style scoped>
@import url("https://fonts.googleapis.com/css?family=Numans");

html,
body {
  background-image: url("http://getwallpapers.com/wallpaper/full/a/5/d/544750.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  height: 100%;
  font-family: "Numans", sans-serif;
}

.container {
  height: 100%;
  align-content: center;
}

.card {
  height: 370px;
  margin-top: 100px;
  margin-bottom: auto;
  width: 400px;
  border-radius: 20px;
  background-color: #5e639b !important;
}

.social_icon span {
  font-size: 60px;
  margin-left: 10px;
  color: #ffc312;
}

.social_icon span:hover {
  color: white;
  cursor: pointer;
}

.card-header h3 {
  color: white;
}

.social_icon {
  position: absolute;
  right: 20px;
  top: -45px;
}

.input-group-prepend span {
  width: 50px;
  background-color: #FDDF6D;
  color: black;
  border: 0 !important;
}

input:focus {
  outline: 0 0 0 0 !important;
  box-shadow: 0 0 0 0 !important;
}

.remember {
  color: white;
}

.remember input {
  width: 20px;
  height: 20px;
  margin-left: 15px;
  margin-right: 5px;
}

.login_btn {
  color: black;
  background-color: #FDDF6D;
  width: 100px;
}

.login_btn:hover {
  color: black;
  background-color: white;
}

.links {
  color: white;
}

.links a {
  margin-left: 4px;
}
.link-danger{
	color: white;
	font-weight: bold;
}
.modal-close{
    position: absolute;
    top: 20px;
    right: 20px;
    border-radius: 100px;
    background-color: white;
    width: 30px;
    height: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
}
</style>