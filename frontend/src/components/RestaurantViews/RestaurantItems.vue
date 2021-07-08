<template>
	<div style="width: 888px; margin: auto" class="header">
		<h3>Items</h3>
		<span class="header-new-item" @click="openEditModal(null)"> <i class="fas fa-plus"></i> New Item </span>
	</div>
	<div class="items-list">
		<div :key="index" v-for="(item, index) in availableItems(restaurant?.items)" class="item-wrapper">
			<div class="item" @click="openItem(item)" >
				<div class="item-image-container">
					<img class="item-image" :src="getImage(item)" alt="" />
				</div>
				<div class="item-body">
					<div class="item-name">
						{{ item.name }}
					</div>
					<div class="item-details">
						<label class="item-description">{{ item.description }}</label>
					</div>
					<div class="item-pricing">
						<label class="item-price" for="username"
							>Price: <b>{{ item.price }} RSD </b></label
						>
					</div>
				</div>
				
			</div>
			<div class="item-options">
					<div class="space"></div>
					<div class="item-options-panel">
						<div class="single-option" @click="openEditModal(item)">
							<i class="fas fa-edit fa-1x"></i>
						</div>
						<div class="single-option" style="color: #FC4C59" @click="deleteItem(item)">
							<i class="far fa-trash-alt" style="color: #FC4C59"></i>
						</div>
					</div>
			</div>
		</div>
		<div class="empty-list" v-if="availableItems(restaurant.items).length == 0">
			<p>This restaurant don't have any item yet</p>
		</div>
		<div class="new-item-placeholder" @click="openEditModal(null)">
			<p class="new-item-placeholder-text">
				Add new item
			</p>
		</div>
	</div>
	<RestaurantItemModal v-if="showModal" v-bind:item="selectedItem" @closeModal="closeModal" />
	<EditItemModal v-if="showEditModal" v-bind:selectedItem="selectedItem" :restaurant="restaurant" @closeModal="closeEditModal" @closeSuccess="closeSuccess" />
</template>
<script>
import moment from 'moment';
import RestaurantItemModal from './RestaurantItemModal.vue';
import EditItemModal from './NewItemModal.vue';
import server from '../../server';
export default {
	data() {
		return {
			items: [
				// {
				// 	name: 'Chiken nuggets',
				// 	description: '10 komada. 300 grama pilećeg filea, pomfrit 100 grama, sos po izboru, kajzerica',
				// 	price: 400.00,
				// 	image: 'https://cdn.donesi.rs/cdn-cgi/image/w=800,h=450,fit=cover,q=100,f=auto/restaurants/1200/popular_item/0000000144119?c=674e1869af5e28d0874ba7c07dbcad6a',
				// },{
				// 	name: 'Punjena pljeskavica',
				// 	description: '200 grama. Sir, šampinjoni',
				// 	price: 280.00,
				// 	image: 'https://cdn.donesi.rs/cdn-cgi/image/w=800,h=450,fit=cover,q=100,f=auto/restaurants/1200/popular_item/0000000096598?c=6ca20e7751415c84fc6c003e1528fada',
				// },
			],
			showModal: false,
			showEditModal: false,
			selectedItem: {},
		};
	},
	props: {
		restaurant: {
			type: Object,
			default: {
				items: [],
			},
		},
	},
	components: { RestaurantItemModal, EditItemModal },
	methods: {
		getImage(review) {
			return server.getImage(review.image);
		},
		format_date(value) {
			if (value) {
				return moment(value).format('DD.MM.YYYY.');
			}
		},
		openEditModal(item = null) {
			this.selectedItem = item;
			this.showEditModal = true;
		},
		openItem(item) {
			this.selectedItem = item;
			this.showModal = true;
		},
		closeModal() {
			this.selectedItem = {};
			this.showModal = false;
		},
		closeEditModal() {
			this.selectedItem = {};
			this.showEditModal = false;
		},
		closeSuccess() {
			this.showEditModal = false;
			this.selectedItem = {};
			this.$emit('refreshRestaurant');
		},
		async deleteItem(item){
			const index = this.restaurant.items.findIndex(e => e.id == item.id);
			if(confirm("Do you want to delete this item?")){
				this.restaurant.items[index].deleted = true;
				const resp = await server.updateRestaurant(this.restaurant);
				if(resp.success){
					Swal.fire({ 
						title: 'Item has been deleted!',
						icon: 'success',
						confirmButtonText: 'Okay',
						timer: 3000,
						timerProgressBar: true
					})
					this.$emit('refreshRestaurant');
				}
			}
		},
		availableItems(list){
			return list.filter(e => !e.deleted)
		}
	},
};
</script>

<style scoped>
.items-list {
	align-items: center;
	display: flex;
	flex-direction: column;
	/* flex-wrap: wrap; */
	padding: 10px 40px;
	position: relative;
	width: 100%;
	/* justify-content: center; */
	min-height: calc(100% - 320px);
}
.item-wrapper{
	width: 800px;
	position: relative;
}
.item {
	width: 800px;
	padding: 17px;
	background-color: white;
	border-radius: 20px;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
	display: flex;
	flex-direction: row;
	height: calc(100% - 10px);
	margin: 5px;
	cursor: pointer;
	position: relative;
	z-index: 2;
}

.item-wrapper:hover .item-options{
	right: -50px
}
.item-options {
	background-color: #FAE480;
    position: absolute;
    top: 15px;
    right: 0px;
    display: flex;
    flex-direction: row;
    align-items: center;
    height: 80%;
	width: 200px;
	border-radius: 20px;
	transition: right 1s ease;
	z-index: 0;
}
.item:hover {
	box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.2);
}

.space{
	flex: 1;
}
.item-options-panel{
	padding: 10px;
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	height: 100%;
	/* background-color: red */
}
.single-option{
	font-size: 19px;
	color: #5E639B;
	cursor: pointer;
}
.item-image-container {
	overflow: hidden;
	/* background-color: #D7E2F8; */
	/* box-shadow: iset 0px 0px 20px rgba(0, 0, 0, 0.1); */
}
.item-image {
	width: 200px;
	height: 100px;
	object-fit: contain;
}
.item-details {
	flex: 1;
}

.item-name {
	flex: 1;
	color: #42405f;
	font-weight: bold;
}
.item-description {
	color: #8f8fa1;
	/* font-weight: 500; */
	font-size: 14px;
}
.item-body {
	flex: 1;
	padding-left: 10px;
	display: flex;
	flex-direction: column;
}
.item-price {
	color: #42405f;
	font-weight: 500;
}
.item-pricing {
	text-align: right;
}
.new-item-placeholder {
	width: 800px;
	padding: 0 17px;
	/* background-color: white; */
	border: 2px dotted #8f8fa1;
	border-radius: 20px;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-items: center;
	margin: 5px;
	cursor: pointer;
}
.new-item-placeholder:hover {
	font-weight: 600;
	box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.2);
}
.new-item-placeholder-text {
	margin: 0 auto;
	color: #8f8fa1;
	line-height: 40px;
}
.header {
	width: 888px;
	display: flex;
	align-items: center;
}
.header > *:first-child {
	flex: 1;
}
.header-new-item {
	color: #8f8fa1;
	font-weight: 500;
	cursor: pointer;
}

.empty-list {
	color: #8f8fa1;
	/* margin: 70px 0; */
	flex: 1;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-items: center;
	justify-content: center;
}

</style>
