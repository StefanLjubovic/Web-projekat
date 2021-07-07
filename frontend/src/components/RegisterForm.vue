<template>
	<div class="container mt-5 mb-5 d-flex justify-content-center">
		<div class="card">
			<div class="card-header">
				<h3>Register</h3>
				<div class="modal-close" @click="$emit('close')">
                        <i class="fas fa-times"></i>
                    </div>
			</div>
			<div class="card-body">
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label for="name">Name</label>
							<input class="form-control" type="text" placeholder="Enter your name..." v-model="name" />
						</div>
						<div class="alert alert-danger" v-if="nameError" role="alert">
							Field must not be empty!
						</div>
					</div>

					<div class="col-sm-6">
						<div class="form-group">
							<label for="name">Surname</label>
							<input class="form-control" type="text" placeholder="Enter you surname..." v-model="surname" />
						</div>
						<div class="alert alert-danger" v-if="surnameError" role="alert">
							Field must not be empty!
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label for="name">Username</label>
							<input class="form-control" type="text" placeholder="Enter you unique username..." v-model="username" />
						</div>
						<div class="alert alert-danger" v-if="userError" role="alert">
							Field must not be empty!
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label for="name">Password</label>
							<input class="form-control" type="password" placeholder="Enter your own password..." v-model="password" />
						</div>
						<div class="alert alert-danger" v-if="passwordError" role="alert">
							Field must not be empty!
						</div>
					</div>
				</div>

				<div class="form-group">
					<label>Date of birth:</label>
					<div>
						<!-- <label for="name">Name</label> -->
						<input class="form-control" type="text" placeholder="dd.mm.yyyy" v-model="dateOfBirth" />
						<div class="alert alert-danger" v-if="dateError" role="alert">
							Field must not be empty!
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label for="exampleFormControlSelect1">Gender:</label>
							<select class="form-control" id="exampleFormControlSelect1" v-model="gender">
								<option selected="selected">Male</option>
								<option>Female</option>
							</select>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label for="exampleFormControlSelect1">User type:</label>
							<select class="form-control" v-model="userType">
								<option selected="selected">Customer</option>
								<option>Manager</option>
								<option>Deliverer</option>
							</select>
						</div>
					</div>
				</div>

				<button class="btn btn-primary btn-block confirm-button confirmButton" @click="confirmForm">
					Confirm
				</button>
			</div>
			<div class="card-footer">
			<div class="d-flex justify-content-center links">
				Already have an account? <a
				href="#"
				class="link-danger"
				@click="loginPage"
				> Log in</a
				>
			</div>
			</div>
		</div>
	</div>
</template>

<script>
import Server from '@/server'
export default {
	watch: {
		$route(to, from) {},
	},
	data() {
		return {
			username: '',
			password: '',
			name: '',
			surname: '',
			gender: 'Male',
			dateOfBirth: '',
			userType: 'Customer',
			userError: false,
			passwordError: false,
			nameError: false,
			surnameError: false,
			dateError: false,
			confirmError: false,
		};
	},
	methods: {
		confirmForm() {
			this.userError = false;
			this.passwordError = false;
			this.nameError = false;
			this.surnameError = false;
			this.dateError = false;
			this.confirmError = false;
			if (this.username == '') this.userError = true;
			if (this.password == '') this.passwordError = true;
			if (this.name == '') this.nameError = true;
			if (this.surname == '') this.surnameError = true;
			var dateRegex = /^\d{2}[.]\d{2}[.]\d{4}[.]$/;
			if (!this.dateOfBirth.match(dateRegex)) this.dateError = true;
			if (this.userError || this.passwordError || this.nameError || this.surnameError || this.dateError) return;

			const newUser = {
				firstName: this.name,
				lastName: this.surname,
				username: this.username,
				password: this.password,
				gender: this.gender,
				dateOfBirth: this.dateOfBirth,
				role: this.userType
			};
				Server.register(newUser).then(resp=>{
				if(resp.success){
					console.log(resp.data);
				}
				console.log('register');
			})
		},
		loginPage() {
			this.$emit('changeState', 'login');
		},
	},
};
</script>

<style scoped>
body {
	background-color: #ffebee;
}
.logo {
	width: 150px;
	margin-left: -20px;
}
.card {
	width: 600px;
	background-color: #fff;
	border: none;
	border-radius: 20px;
	background-color: #5e639b !important;
}

.form-group > label {
	color: white;
	margin-bottom: unset;
}

.flex-row {
	margin-bottom: -20px;
}
label.radio {
	cursor: pointer;
	width: 100%;
}

label.radio input {
	position: absolute;
	top: 0;
	left: 0;
	visibility: hidden;
	pointer-events: none;
}

label.radio span {
	padding: 7px 14px;
	border: 2px solid #eee;
	display: inline-block;
	color: #039be5;
	border-radius: 10px;
	width: 100%;
	height: 48px;
	line-height: 27px;
}

label.radio input:checked + span {
	border-color: #039be5;
	background-color: #81d4fa;
	color: #fff;
	border-radius: 9px;
	height: 48px;
	line-height: 27px;
}

.form-control {
	margin-top: 2px;
	height: 48px;
	border: 2px solid #eee;
	border-radius: 10px;
}

.form-control:focus {
	box-shadow: none;
	border: 2px solid #fddf6d;
}

.agree-text {
	font-size: 12px;
}

.terms {
	font-size: 12px;
	text-decoration: none;
	color: #fddf6d;
}

.confirm-button {
	height: 50px;
	border-radius: 10px;
	background-color: #fddf6d;
	color: #42405F;
	font-weight: 500;
}

.display-false {
	display: none;
}
.display-true {
	display: inline;
}
.register {
	margin-right: 150px;
}
.card-header {
	display: flex;
	align-items: center;
}
.card-header > h3 {
	flex: 1;
	color: white;
}
.loginBack {
	color: white;
	font-weight: 600;
	cursor: pointer;
}
.card-footer{
	color: white;
}
.link-danger{
	color: white;
	font-weight: bold;
	margin-left: 5px;
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
