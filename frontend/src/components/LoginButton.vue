<template>
	<div class="login-button-container">
		<button type="button" class="btn btn-primary" @click="$emit('login-user')" v-if="!userExists">
			Log in
		</button>
		<button class="my-cart-container" @click="$emit('show-cart')">
      <div class="my-cart">
			  <i class="fas fa-shopping-cart"></i>
      </div>
			<div class="badge">
				{{ cartLength }}
			</div>
		</button>
		<span class="profile-dropdown" v-if="userExists">
			<i class="fas fa-user" style="color: #fddf6d"></i>
			<span class="user-profile-name"> {{ user.firstName }} {{ user.lastName }} </span>
			<button class="btn btn-warning " @click="$emit('show-options')">
				<i class="fas fa-angle-down"></i>
			</button>
		</span>
	</div>
</template>

<script>
export default {
	emits: ['login-user', 'show-options', 'show-cart'],
	computed: {
		user() {
			console.log('Getting user');
			return this.$store.getters.getUser;
		},
		cart() {
			return this.$store.getters.getCart;
		},
		userExists() {
			return !!this.user.firstName;
		},
		cartLength() {
			return this.cart?.items?.length || '0';
		},
	},
	methods: {},
};
</script>

<style scoped>
button.btn-primary {
	width: 100px;
	background-color: #fddf6d;
	color: #42405f;
	font-weight: 500;
	border: unset;
	margin-right: 15px;
}
button.btn-warning {
	background-color: #fddf6d;
	color: #42405f;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
	margin-right: 15px;
	height: 35px;
	width: 35px;
	border-radius: 100px;
}
.btn-circle {
	width: 45px;
	height: 45px;
	line-height: 45px;
	text-align: center;
	padding: 0;
	border-radius: 50%;
}

.btn-circle i {
	position: relative;
	top: -1px;
}

.btn-circle-sm {
	width: 35px;
	height: 35px;
	/* line-height: 35px; */
	/* font-size: 0.9rem; */
}

.btn-circle-lg {
	width: 55px;
	height: 55px;
	line-height: 55px;
	font-size: 1.1rem;
}

.btn-circle-xl {
	width: 70px;
	height: 70px;
	line-height: 70px;
	font-size: 1.3rem;
}
.profile-dropdown {
	display: flex;
	flex-direction: row;
	align-items: center;
}
.user-profile-name {
	color: #fddf6d;
	font-weight: 500;
	margin-left: 5px;
	margin-right: 10px;
}

.login-button-container {
	display: flex;
	align-items: center;
}
.my-cart-container {
	/* padding: 0 10px; */
	color: #fddf6d;
	margin: 0 15px;
	cursor: pointer;
	position: relative;
  background-color: unset;
  border: unset;
  outline: unset !important;
}
.my-cart {
	/* padding: 0 10px; */
	color: #FFFFFF;
  opacity: 0.9;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 35px;
	width: 35px;
	border-radius: 100px;
	cursor: pointer;
	position: relative;
}

.my-cart:hover {
	background-color: rgba(255, 255, 255, 0.15);
}
.badge {
	/* background-color: #D7E2F8; */
	position: absolute;
  top:   0px;
  right: 0px;
  font-weight: bold;
  color: #5E639B;
  font-size: 14px;
  background-color: #FDDF6D;
  width: 16px;
  height: 16px;
  border-radius: 100px;
  line-height: 16px;
  padding: unset;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
}
</style>
