<template>
	<div class="container rounded bg-white mt-5 mb-5 color">
		<div class="row">
			<div class="col-md-5 border-right">
				<div class="p-3 py-5">
					<div class="d-flex justify-content-between align-items-center mb-3">
						<h4 class="text-right text-light">Profile Settings</h4>
					</div>
					<div class="row mt-2">
						<div class="col-md-6">
							<label class="labels">Name</label><input type="text" class="form-control" placeholder="first name" v-model="user.firstName" />
						</div>
						<div class="col-md-6">
							<label class="labels">Surname</label><input type="text" class="form-control" v-model="user.lastName" placeholder="surname" />
						</div>
						<div class="alert alert-danger form-control name-alert" v-if="nameError" role="alert">
							Field must not be empty!
						</div>
						<div class="alert alert-danger form-control name-alert" v-if="surnameError" role="alert">
							Field must not be empty!
						</div>
					</div>
					<div class="row mt-3">
						<div class="col-md-12">
							<label class="labels">Username</label
							><input type="text" class="form-control" placeholder="username" v-model="user.username" readonly />
						</div>

						<div class="alert alert-danger form-control alert-form" v-if="usernameError" role="alert">
							Field must not be empty!
						</div>
						<div class="col-md-12">
							<label class="labels">Date of birth</label><input type="text" class="form-control" placeholder="dd.mm.yyyy" v-model="dateDisplay" />
						</div>
						<div class="alert alert-danger form-control alert-form" v-if="dateError" role="alert">
							Wrong date format!
						</div>
						<div class="col-md-12">
							<label class="labels">Gender</label>
							<select class="form-control" v-model="user.gender"
								><option value="Male">Male</option
								><option value="Female">Female</option>
							</select>
						</div>
					</div>
					<div class="mt-5 text-center">
						<button class="btn btn-primary profile-button" type="button" @click="changeUserInfo">Save Profile</button>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="p-3 py-5">
					<div class="d-flex justify-content-between align-items-center experience">
						<span class="text-right text-light">Change password</span
						><span class="border px-3 p-1 add-experience btn" @click="changePassword">&nbsp;Change password</span>
					</div>
					<br />
					<div class="alert alert-danger" v-if="passwordError" role="alert">
						Password to weak!
					</div>
					<div class="col-md-12">
						<label class="labels">Current password</label><input type="password" class="form-control" v-model="user.password" />
					</div>
					<br />
					<div class="col-md-12"><label class="labels">New password</label><input type="password" class="form-control" v-model="password" /></div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { create } from 'ol/transform';
import moment from 'moment';
import Server from '@/server';
export default {
	data() {
		return {
			user: {},
			password: '',
			passwordError: false,
			dateError: false,
			nameError: false,
			surnameError: false,
			usernameError: false,
			dateDisplay: '',
		};
	},
	methods: {
		changePassword() {
			this.passwordError = false;
			console.log(this.password);
			if (this.password.length < 8 || this.password != this.user.password) this.passwordError = true;
			if (this.passwordError) return;
			const loginUser = {
				username: this.user.username,
				password: this.password,
			};
			Server.updatePassword(loginUser).then((resp) => {
				if (resp.success) {
					console.log('this.restaurants');
				}
			});
		},
		changeUserInfo() {
			this.emailError = false;
			this.usernameError = false;
			this.nameError = false;
			this.surnameError = false;
			this.usernameError = false;
			this.dateError = false;
			var dateRegex = /^\d{2}[.]\d{2}[.]\d{4}[.]$/;
			if (this.user.firstName == '') this.nameError = true;
			if (this.user.lastName == '') this.surnameError = true;
			if (this.user.username == '') this.usernameError = true;
			if (!this.dateDisplay.match(dateRegex)) this.dateError = true;
			if (this.dateError || this.surnameError || this.nameError || this.usernameError) return;
			var splittedDate = this.dateDisplay.split('.');
			var date = [splittedDate[1], splittedDate[0], splittedDate[2]].join('/');
			var dateObject = new Date(date);
			this.user.dateOfBirth = dateObject.getTime();
			Server.updateUser(this.user).then((resp) => {
				if (resp.success) {
					console.log('this.restaurants');
				}
			});
		},
	},
	async created() {
		this.user = this.$store.getters.getUser;
		this.user.dateOfBirth = new Date(this.user.dateOfBirth);
		this.dateDisplay = moment(String(this.user.dateOfBirth)).format('DD.MM.YYYY.');
	},
};
</script>

<style scoped>
.form-control:focus {
	box-shadow: none;
	border-color: #ba68c8;
}
.color{
    height: unset !important;
}
.name-alert {
	width: 43%;
	margin-left: 15px;
	margin-top: 15px;
	margin-right: 15px;
	font-size: 13px;
}
.profile-button {
	background-color: #ffc312;
	color: black;
	box-shadow: none;
	border: none;
}
.profile-button:hover {
	background-color: #ffc312;
	color: black;
}
.btn {
	background-color: #ffc312;
	color: black;
}
.profile-button:focus {
	background-color: #ffc312;
	color: black;
}
.alert-form {
	margin-top: 10px;
	margin-left: 15px;
	width: 93%;
}
.profile-button:active {
	background-color: #ffc312;
	color: black;
}

.back:hover {
	color: #682773;
	cursor: pointer;
}

.labels {
	font-size: 11px;
	color: white;
}

.add-experience:hover {
	background-color: #ffc312;
	color: black;
	cursor: pointer;
}
.background {
	background-color: #d7e2f8;
	width: 100%;
	height: 100%;
}
.row {
	background-color: #5e639b;
	border-radius: 20px;
}
.color {
	background-color: #d7e2f8;
	width: 100%;
	height: 100%;
}
</style>
