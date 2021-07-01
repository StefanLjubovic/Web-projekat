<template>
    <div class="container" @click="$emit('user-container-click')">
        <div class="profile-btn margin">
            <button class="btn btn-light" @click="$emit('edit-profile')">
            <div  v-if="user.logo == ''">
                <i class="far fa-user user-icon fa-2x"></i>
                
            </div>
            <div  v-if="user.logo != ''">
                <img class="rounded-image" :src="getImgUrl(user.logo)" v-bind:alt="user.logo">                
            </div>
            <div  class="user-info">
            <h3>{{user.name}} {{user.surname}}</h3>
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
           <button type="button" class="btn btn-light btn" @click="createArticle"><span class="btn-components">Add article<i class="fas fa-plus icon"></i></span></button>
           </div>
           <div class="btn-div margin">
           <button type="button" class="btn btn-light btn" @click="viewCustomers"><span class="btn-components">View customers<i class="fas fa-user-friends icon"></i></span></button>
           </div>
       <div class="btn-div margin">
           <button type="button" class="btn btn-light btn"><span class="btn-components">Sign out<i class="fas fa-sign-out-alt icon"></i></span></button>
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
    data(){
        return{
            user: {},
        }
    },
    methods:{
        getImgUrl(pic) {
            return require('../assets/'+pic)
        },
        createRestaurant(){
            this.$emit('create-restaurant');
            this.$router.push({ path: '/create-restaurant' });
        },
        allUsers(){
            this.$router.push({ path: '/user-list' });
            this.$emit('all-users');
        },
        managersRestaurant(){
             this.$emit('create-restaurant');
            this.$router.push({ path: '/restaurant' });
        },
        createArticle(){
             this.$emit('create-restaurant');
             this.$router.push({ path: '/create-article' });
        },
        viewCustomers(){
            this.$emit('create-restaurant');
             this.$router.push({ path: '/view-customers' });
        }
    },
    created() {
		this.user = User;
	},
    emits:['create-restaurant','edit-profile','all-users','user-container-click']
}
</script>

<style scoped>
.container {
  background: #ffffff;
  color: #999999;
  border-radius: 8px;
  padding: 10px 20px;
  width: 400px;
  height: 400px;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
  background-color: #5E639B;
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
    background-color: #FDDF6D;
    color: #5E639B;
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
    color: #5E639B;
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