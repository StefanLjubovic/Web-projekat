<template>
	<div class="background">
		<div class="restaurant-cards">
			<div class="restaurant-info-card">
				<div class="left-side-container">
					<div class="restaurant-image-container" v-if="logo != ''">
						<img class="rounded-image restaurant-image" :src="getImage(logo)" v-bind:alt="'Restaurant Logo'" />
						<div class="remove-image" @click="removeImage">
							<i class="fas fa-times"></i>
						</div>
					</div>
					<div class="image-placeholder" @click="onPickFile" style="cursor: pointer" v-if="logo == ''" v-bind:class="{ errorLogo: error['logo'] }">
						<i class="far fa-images fa-3x"></i>
						<input type="file" @change="uploadPhoto" accept="image/*" style="display:none" ref="fileInput" />
					</div>
				</div>
				<div class="right-side-container">
					<div class="header">
						<button class="back" v-if="!!id" @click="$router.back"><i class="fas fa-chevron-left"></i></button> <h3 class="title">  Create a restaurant</h3>
					</div>
					<div class="restaurant-info-body">
						<div class="restaurant-info-body-part">
							<div class="form-group ">
								<label for="nameInput" v-bind:class="{ error: error['name'] }">Name:</label>
								<input type="text" class="form-control" id="nameInput" placeholder="Name*" v-model="name" />
								<div class="alert alert-danger" v-if="false" role="alert">
									Field must not be empty!
								</div>
							</div>
							<div class="form-group">
								<label for="type">Restaurant type:</label>
								<select name="cars" id="type" class="form-control" v-model="type">
									<option value="Italian">Italian 🍕</option>
									<option value="Chinese">Chinese🥡</option>
									<option value="Barbeque">Barbeque🍖</option>
									<option value="Similarly">Similarly🍗</option>
								</select>
							</div>
						</div>
						<div class="restaurant-info-body-part">
							<div class="form-group">
								<label for="manager" v-bind:class="{ error: error['manager'] }">Manager:</label>
								<div class="select-manager" v-if="!(id && selectedManager)">
									<div v-if="managers.length">
										<select name="manager" id="manager" class="form-control" @change="onChange($event)">
											<option value=""></option
											>t
											<option v-bind:key="manager.username" v-for="(manager, index) in managers"
												>{{ index + 1 }}. {{ manager.firstName }} {{ manager.lastName }}
											</option>
										</select>
									</div>
									<div class="form-row" v-else>
										<div class="col">
											<label class="text-light margin">None available</label>
										</div>
										<div class="col">
											<button type="button" class="btn btn-warning" @click="registerManager">Register one</button>
										</div>
									</div>
								</div>
								<div v-else class="selected-manager">
									<p for="" class="manager-name-label">
										Current manager: <span class="manager-name">{{ selectedManager.firstName }} {{ selectedManager.lastName }}</span>

										<button class="remove-manager" @click="removeManager">
											<i class="fas fa-times"></i>
										</button>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="restaurant-map">
				<MapContainer :coordinates="[longitude, latitude]" @changeCoordinates="changeCoordinates" />
				<div class="address-field">
					<input
						type="text"
						placeholder="Enter address here* (Street name, City, Zip code)"
						v-bind:class="{ errorAddress: error['address'] }"
						v-on:keydown="changeAddress"
						v-model="address"
					/>
				</div>
				<p for="" v-if="errorSpecial" class="address-field-error">Invalid format <i>(Street name, City, Zip code)</i></p>
				<div class="submit-register" @click="saveRestaurant">
					{{ !!id ? 'Save' : 'Create'}}
				</div>
			</div>
		</div>
		<LoginModal :form-type="formType" v-if="showLoginModal" @close="closeLogin" />
	</div>
</template>

<script>
import LoginModal from '@/components/Login/LoginModal.vue';
import MapContainer from '../components/MapContainer.vue';
import Swal from 'sweetalert2';
import server from '../server';
import { fromLonLat, toLonLat } from 'ol/proj';
const coordinates = fromLonLat([19.845013, 45.255068])
export default {
	data() {
		return {
			logo: '',
			nameError: false,
			name: '',
			selectedManager: null,
			type: 'Italian',
			managers: [],
			showLoginModal: false,
			formType: 'register',
			longitude: coordinates[0],
			latitude: coordinates[1],
			address: '',
			error: {},
			imageFile: null,
			id: null,
			newImage: false,
			restaurant: {},
			errorSpecial: false
		};
	},
	methods: {
		removeManager() {
			if(this.managers.filter(e => e.id == this.selectedManager.id).length == 0)
				this.managers.push(this.selectedManager);
			this.selectedManager = null;
		},
		removeImage() {
			this.logo = '';
			this.newImage = true;
		},
		getImgUrl(pic) {
			return require('../assets/' + pic);
		},
		uploadPhoto(event) {
			const files = event.target.files;
			const fileReader = new FileReader();
			fileReader.addEventListener('load', () => {
				this.logo = fileReader.result;
			});
			console.log(files[0]);
			fileReader.readAsDataURL(files[0]);
			this.imageFile = files[0];
		},
		onPickFile() {
			this.$refs.fileInput.click();
		},
		async uploadImage() {
			const respImage = await server.uploadImage(this.imageFile);
			if (respImage.success) {
				console.log(respImage);
				const image = respImage.data.filename;
				return image;
			}
			return 0;
		},
		async saveRestaurant(event) {
			event.preventDefault();
			this.error['name'] = this.name == '';
			// this.error['manager'] = this.manager == null
			this.error['logo'] = this.logo == '';
			this.error['address'] = this.address == '';
			if (this.containesError()) {
				Swal.fire({
					title: 'Error!',
					text: "You didn't feel all fields...",
					icon: 'error',
					confirmButtonText: 'Okay',
				});
			} else {
				let image = this.logo;
				if (!this.id || this.newImage) {
					image = await this.uploadImage();
					if (!image) {
						this.notiftError();
						return;
					}
				}
				console.log([this.longitude,this.latitude]);
				console.log([this.restaurant.location.longitude,this.restaurant.location.latitude]);
				const data = {
					...this.restaurant,
					name: this.name,
					location: {
						address: this.address,
						longitude: this.longitude,
						latitude: this.latitude,
					},
					type: this.type,
					logo: image,
					managerId: this.selectedManager.id,
				};
				console.log([data.location.longitude, data.location.latitude]);
				if (!this.id) this.createRestaurant(data);
				else this.updateRestaurant(data);
			}
		},
		async createRestaurant(data) {
			const resp = await server.createRestaurant(data);
			if (resp.success) {
				this.$router.push({ name: 'Home' });
				Swal.fire({
					title: 'Restaurant is created!',
					icon: 'success',
					confirmButtonText: 'Okay',
					timer: 2000,
					timerProgressBar: true,
				});
			}
		},
		async updateRestaurant(data) {
			console.log("Update data: ",data);
			const resp = await server.updateRestaurant(data);
			if (resp.success) {
				this.$router.push({ name: 'Restaurant', params: { id: this.id } });
				Swal.fire({
					title: 'Restaurant is saved!',
					icon: 'success',
					confirmButtonText: 'Okay',
					timer: 2000,
					timerProgressBar: true,
				});
			}
		},
		containesError() {
			for (const key in this.error) {
				console.log(key);
				if (this.error[key]) return true;
			}
			return this.errorSpecial;
		},
		registerManager() {
			this.showLoginModal = true;
			document.getElementById('appContainer').style.overflow = 'hidden';
			document.getElementById('appContainer').style.height = '100vh';
		},
		closeLogin() {
			this.showLoginModal = false;
			document.getElementById('appContainer').style.overflow = 'unset';
			document.getElementById('appContainer').style.height = 'unset';
		},
		changeCoordinates(coordinates) {
			console.log(this.selectedManager.username);
			this.longitude = coordinates[0];
			this.latitude = coordinates[1];
		},
		changeAddress() {
			this.error['address'] = this.address == '';
			const regex = /(\w+\ *)+\,\ *(\w+\ *)+\,\ *\w+/g;
			this.errorSpecial = !this.address.match(regex);
		},
		onChange(event) {
			const myArr = event.target.value.split('.');
			var index = myArr[0] - 1;
			this.selectedManager = this.managers[index];
		},
		loadRestaurant() {
			server.getRestaurantById(this.id).then((resp) => {
				if (resp.success) {
					const restaurant = resp.data;
					console.log(restaurant);
					this.name = restaurant.name;
					this.logo = restaurant.logo;
					this.type = restaurant.type;
					this.address = restaurant.location.address;
					this.logintude = restaurant.location.longitude;
					this.latitude = restaurant.location.latitude;
					this.restaurant = restaurant;
					this.loadManager(restaurant.managerId);
				}
			});
		},
		getImage(image) {
			return !!this.id ? server.getImage(image) : image;
		},
		async loadManager(id) {
			const resp = await server.getManager(id);
			if (resp.success) {
				this.selectedManager = resp.data;
				console.log(this.selectedManager);
			}
		},
	},
	async created() {
		this.id = this.$route.params.id;
		if (!!this.id) this.loadRestaurant();
		server.getAllManagers().then((resp) => {
			console.log('Resp', resp.data);
			if (resp.success) {
				this.managers = resp.data;
			}
		});
	},
	components: {
		LoginModal,
		MapContainer,
	},
};
</script>

<style scoped>
.register {
	padding: 3%;
}
.form-group > label {
	color: white;
	margin-bottom: unset;
	font-size: 20px;
}
.register-left {
	text-align: center;
	color: #fff;
	margin-top: 4%;
}
.logo-div {
	position: absolute;
	right: 100px;
	top: 20px;
	width: 100px;
	height: 100px;
}
.register-left input {
	border: none;
	border-radius: 1.5rem;
	padding: 2%;
	width: 60%;
	background: #f8f9fa;
	font-weight: bold;
	color: #383d41;
	margin-top: 30%;
	margin-bottom: 3%;
	cursor: pointer;
}
.register-right {
	background-color: #5e639b;
	position: relative;
	padding: 17px;
	border-radius: 20px;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
	display: flex;
}
.register-left img {
	margin-top: 15%;
	margin-bottom: 5%;
	width: 25%;
	-webkit-animation: mover 2s infinite alternate;
	animation: mover 1s infinite alternate;
}
@-webkit-keyframes mover {
	0% {
		transform: translateY(0);
	}
	100% {
		transform: translateY(-20px);
	}
}
@keyframes mover {
	0% {
		transform: translateY(0);
	}
	100% {
		transform: translateY(-20px);
	}
}
.register-left p {
	font-weight: lighter;
	padding: 12%;
	margin-top: -9%;
}
.margin {
	margin-top: 5px;
	font-size: 20px;
}
.img-btn {
	border-radius: 50%;
	position: absolute;
	border: #fddf6d;
	background: #fddf6d;
	width: 100px;
	height: 100px;
	right: 0;
}
.icon {
	position: absolute;
	right: 15px;
	bottom: 16px;
	z-index: 2;
	pointer-events: none;
	cursor: pointer;
}
.small-img-btn {
	position: absolute;
	border-radius: 50%;
	width: 30px;
	height: 30px;
	right: 10px;
	bottom: 10px;
	z-index: 2;
	cursor: pointer;
}

.register .register-form {
	padding: 10%;
	margin-top: 10%;
	padding-bottom: 5%;
}
.btnRegister {
	float: right;
	margin-top: 10%;
	border: none;
	border-radius: 1.5rem;
	padding: 10px;
	background-color: #fddf6d;
	color: black;
	font-weight: 600;
	width: 50%;
	cursor: pointer;
}
.register .nav-tabs {
	margin-top: 3%;
	border: none;
	border-radius: 1.5rem;
	width: 28%;
	float: right;
}
.register .nav-tabs .nav-link {
	padding: 2%;
	height: 34px;
	font-weight: 600;
	color: #fff;
	border-top-right-radius: 1.5rem;
	border-bottom-right-radius: 1.5rem;
}
.register .nav-tabs .nav-link:hover {
	border: none;
}
.register .nav-tabs .nav-link.active {
	width: 100px;
	border-top-left-radius: 1.5rem;
	border-bottom-left-radius: 1.5rem;
}
.register-heading {
	text-align: left;
	margin-top: 8%;
	margin-bottom: -15%;
	color: white;
}
.background {
	background-color: #d7e2f8;
	/* width: 100%;
	height: 100%; */
	min-height: calc(100% - 70px);
	background-color: #d7e2f8;
	padding-top: 40px;
}
.register-button {
	background-color: #fddf6d;
}

.restaurant-cards {
	/* padding: 17px; */
	background-color: white;
	width: 80%;
	border-radius: 20px;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
	margin: auto;
	overflow: hidden;
	position: relative;
}
.restaurant-map {
	/* background-color: red; */
	margin-top: -20px;
	/* padding-top: 20px; */
	/* padding-bottom: 10px; */
	z-index: 0;
	position: relative;
	min-height: 300px;
    height: calc(100vh - 400px);
}
.restaurant-info-card {
	padding: 17px;
	z-index: 2;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
	border-radius: 20px;
	background-color: #5e639b;
	position: relative;
	display: flex;
	flex-direction: row;
	align-items: center;
}
.left-side-container {
	width: 200px;
	text-align: center;
	margin-right: 30px;
}
.restaurant-image-container {
	max-width: 200px;
	border-radius: 12px;
	overflow: hidden;
	position: relative;
}
.rounded-image {
	object-fit: contain;
	width: 100%;
	max-height: 200px;
	/* height: 100%; */
}
.restaurant-info-body-part {
	flex: 1;
	padding: 0 10px;
}
.restaurant-info-body {
	display: flex;
	/* background-color: red; */
}
.right-side-container {
	flex: 1;
}
.title {
	color: white;
}
.btn-warning {
	background-color: #fddf6d;
}
.remove-image {
	background-color: rgba(255, 255, 255, 0.15);
	position: absolute;
	top: 10px;
	right: 10px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	height: 23px;
	width: 23px;
	border-radius: 50%;
	color: white;
	cursor: pointer;
}
.address-field {
	padding: 10px;
	position: absolute;
	top: 40px;
	right: 10px;
	background-color: white;
	width: 400px;
	border-radius: 27px;
	display: flex;
	/* flex-direction: row; */
	border-radius: 100px;
	padding: 0px 11px;
	height: 39px;
	align-items: center;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.3);
}
.address-field-error{
	position: absolute;
	top: 75px;
	right: 10px;
	/* width: 400px; */
	border-radius: 27px;
	display: flex;
	/* flex-direction: row; */
	border-radius: 100px;
	padding: 0px 11px;
	align-items: center;
	color: #fc4c59;
	font-weight: 600;
	background-color: #FFFFFF;
	margin: 0;
}
.submit-register {
	padding: 10px;
	position: absolute;
	bottom: 20px;
	right: 10px;
	background-color: #fddf6d;
	border-radius: 27px;
	border-radius: 100px;
	padding: 0px 11px;
	line-height: 42px;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);
	cursor: pointer;
	font-weight: 600;
	color: #42405f;
	padding: 0 40px;
	opacity: 0.8;
}
.submit-register:hover {
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.3);
	opacity: 1;
}
.address-field > input {
	background-color: transparent;
	border: unset;
	flex: 1;
	color: #42405f;
}
.address-field > input::placeholder {
	color: #42405f;
	opacity: 0.5;
}
.error {
	color: #fc4c59 !important;
	font-weight: 600;
}
.errorLogo {
	border: 2px solid #fc4c59;
	border-radius: 14px;
	color: #fc4c59;
}

.errorAddress::placeholder {
	color: #fc4c59 !important;
	font-weight: 600;
}
.image-placeholder {
	height: 200px;
	display: flex;
	flex-direction: row;
	justify-items: center;
	align-items: center;
	text-align: center;
	vertical-align: middle;
}

.image-placeholder > i {
	width: 100%;
}
.selected-manager {
	padding: 8px 15px;
	border-radius: 0.25rem;
	background-color: #ffffff;
}
.manager-name {
	font-size: 16px;
	font-weight: 600;
}
.manager-name-label {
	margin-bottom: unset;
	font-weight: 500;
	color: #1f1f43;
	font-size: 15px;
}
.remove-manager {
	float: right;
	padding: 5px 7px;
	margin-right: -5px;
	cursor: pointer;
	border: unset;
	outline: unset;
	border-radius: 100px;
}
.header{
	display: flex;
	flex-direction: row;
	align-items: center;
}
.back{
	background-color: transparent;
	border: unset;
	font-size: 15px;
	width: 30px;
	height: 30px;
	line-height: 30px;
	background-color: #FFFFFF66;
	margin-top: -5px;
	margin-right: 10px;
	border-radius: 100px;
	cursor: pointer;
}
</style>
