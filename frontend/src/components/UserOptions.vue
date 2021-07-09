<template>
	<div class="container">
		<div class="profile-btn margin">
			<button class="btn btn-light profile-container" @click="$emit('edit-profile')">
				<div class="profile-conatiner-image">
					<img class="rounded-image" :src="getImgUrl(user?.logo)" v-bind:alt="user.logo" v-if="!!user.logo" />
					<i class="far fa-user user-icon fa-2x" v-if="!user.logo"></i>
				</div>
				<div class="user-info">
					<h3>{{ user.firstName }} {{ user.lastName }}</h3>
					<p class="info">Profile info</p>
				</div>
			</button>
		</div>
		<div class="btn-div margin" v-if="user.role=='Admin'">
			<button type="button" class="btn btn-light btn" @click="createRestaurant">
				<span class="btn-components">Register restaurant<i class="fas fa-plus icon"></i></span>
			</button>
		</div>
		<div class="btn-div margin" v-if="user.role=='Manager' && user.restaurantId !='undefined' ">
			<button type="button" class="btn btn-light btn" @click="managersRestaurant">
				<span class="btn-components">My restaurant<i class="fas fa-utensils icon"></i></span>
			</button>
		</div>
		<div class="btn-div margin" v-if="user.role=='Admin'">
			<button type="button" class="btn btn-light btn" @click="allUsers">
				<span class="btn-components">All users<i class="fas fa-users icon"></i></span>
			</button>
		</div>
		<div class="btn-div margin">
			<button type="button" class="btn btn-light btn" @click="viewCustomers">
				<span class="btn-components">View orders<i class="fas fa-truck icon"></i></span>
			</button>
		</div>
		 <div class="btn-div margin" v-if="user.role=='Admin'">
           <button type="button" class="btn btn-light btn" @click="registerUser"><span class="btn-components">Register user<i class="fas fa-user-friends icon"></i></span></button>
           </div>
		<div class="btn-div margin">
			<button type="button" class="btn btn-light btn" @click="signout">
				<span class="btn-components">Sign out<i class="fas fa-sign-out-alt icon"></i></span>
			</button>
		</div>
	</div>
</template>
<script>
const User = {
	name: 'Pera',
	surname: 'Peric',
	logo: '',
};
export default {
	watch: {
		$route(to, from) {},
	},
	computed: {
		user() {
			return this.$store.getters.getUser;
		},
	},
	data(){
		return{
			showLoginModal: false,
			formType: 'register',
		}
	},
	methods: {
		getImgUrl(pic) {
			return !!pic ? require('../assets/' + pic) : '';
		},
		createRestaurant() {
			this.$emit('hideDialog');
			this.$router.push({ path: '/create-restaurant' });
		},
		allUsers() {
			this.$router.push({ path: '/user-list' });
			this.$emit('hideDialog');
		},
		managersRestaurant() {
			this.$emit('hideDialog');
			this.$router.push({ name: 'Restaurant',params: { id: this.user.restaurantId } });
		},
		createArticle() {
			this.$emit('create-restaurant');
			this.$router.push({ path: '/create-article' });
		},
		viewCustomers() {
			this.$emit('create-restaurant');
			this.$router.push({ path: '/view-customers' });
		},
		signout() {
			localStorage.clear();
			this.$store.commit('setUser', {});
			this.$emit('hideDialog');
			this.$router.push("/")
		},
		registerUser(){
			console.log()
			this.$emit('hideDialog');
			this.$emit('openRegistration');
		}
	},
	emits: ['create-restaurant', 'edit-profile', 'all-users', 'hideDialog','openRegistration'],
};
</script>
<style scoped>
.container {
	background: #f0f1f3;
	color: #999999;
	border-radius: 8px;
	padding: 10px 20px;
	width: 400px;
	/* height: 400px; */
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
}
.btn-div {
	width: 100vw;
	position: relative;
	height: 30px;
	left: 50%;
}
.icon {
	margin-left: 5px;
}
.btn-div {
	left: 0px;
}
.btn {
	width: 360px;
}
.rounded-image {
	width: 100%;
	height: auto;
	border-radius: 10px;
}
.user-icon {
	width: 25px;
	height: 20px;
	color: black;
	margin-bottom: 20px;
	margin-top: 10px;
	float: left;
}
.user-info {
	margin-right: 100px;
}
.info {
	margin-right: 50px;
}
.margin {
	margin-bottom: 10px;
}
.profile-container {
	display: flex;
	align-items: center;
	padding-left: 0;
}
.profile-conatiner-image {
	width: 80px;
	display: flex;
	justify-content: center;
}
.btn-light {
	background-color: #fddf6d;
}
</style>
