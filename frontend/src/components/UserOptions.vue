<template>
    <div class="container" @click="$emit('hideDialog')">
        <div class="profile-btn margin">
            <button class="btn btn-light" @click="$emit('edit-profile')">
            <div  v-if="user.logo == ''">
                <i class="far fa-user user-icon fa-2x"></i>
                
            </div>
            <div  v-if="user.logo != ''">
                <img class="rounded-image" :src="getImgUrl(user?.logo)" v-bind:alt="user.logo">                
            </div>
            <div  class="user-info">
            <h3>{{user.firstName}} {{user.lastName}}</h3>
            <p class="info">Profile info</p>
            </div>
            </button>
        </div>
            <div class="btn-div margin">
           <button type="button" class="btn btn-light btn"  @click="createRestaurant"><span class="btn-components">Register restaurant<i class="fas fa-plus icon"></i></span></button>
           </div>
            <div class="btn-div margin">
           <button type="button" class="btn btn-light btn" @click="managersRestaurant"><span class="btn-components">My restaurant<i class="fas fa-utensils icon"></i></span></button>
           </div>
           <div class="btn-div margin">
           <button type="button" class="btn btn-light btn" @click="allUsers"><span class="btn-components">All users<i class="fas fa-users icon"></i></span></button>
           </div>
       <div class="btn-div margin">
           <button type="button" class="btn btn-light btn" @click="signout"><span class="btn-components">Sign out<i class="fas fa-sign-out-alt icon"></i></span></button>
           </div>
    </div>
</template>

<script>
    const User={
                name:  'Pera',
                surname: 'Peric',
                logo: '',
               }
export default {
    watch: {
		$route(to, from) {},
	},
    computed:{
        user(){
            return this.$store.getters.getUser;
        }
    },
    methods:{
        getImgUrl(pic) {
            return !!pic ? require('../assets/'+pic) : ""
        },
        createRestaurant(){
            this.$emit('hideDialog');
            this.$router.push({ path: '/create-restaurant' });
        },
        allUsers(){
            this.$router.push({ path: '/user-list' });
            this.$emit('hideDialog');
        },
        managersRestaurant(){
             this.$emit('hideDialog');
            this.$router.push({ path: '/restaurant' });
        },
        signout(){
            this.$store.commit("setUser", {});
            this.$emit('hideDialog')
        }
    },
    created() {
		// this.user = User;
	},
    emits:['create-restaurant','edit-profile','all-users','hideDialog']
}
</script>

<style scoped>
.container {
  background: #FDDF6D;
  color: #999999;
  border-radius: 8px;
  padding: 10px 20px;
  width: 400px;
  /* height: 400px; */
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
}

.btn-div{
 width: 100vw;
 position: relative;
 height: 30px;
 left: 50%;
}
.icon{
    margin-left:5px ;
}
.btn-div{
    left: 0px;
}
.btn{
    width:360px;
}
.rounded-image{
        width: 100%;
        height: auto;
        border-radius: 10px;
    }
.user-icon{
    width: 25px;
    height: 20px;
    color: black;
    margin-bottom:20px ;
    margin-top:10px ;
    float: left;
}
.user-info{
    margin-right: 100px;
}
.info{
    margin-right:50px ;
}
.margin{
    margin-bottom: 10px;
}
</style>