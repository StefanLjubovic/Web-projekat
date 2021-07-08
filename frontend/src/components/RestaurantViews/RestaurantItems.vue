<template>
	<div style="width: 888px; margin: auto" class="header">
		<h3>Items</h3>
		<span class="header-new-item">
			<i class="fas fa-plus"></i> New Item
		</span>
	</div>
	<div class="items-list">
		<div :key="index" v-for="(item, index) in items">
			<div class="item" @click="openItem(item)">
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
						<label class="item-price" for="username">Price: <b>{{ item.price }} RSD </b></label>
					</div>
				</div>
			</div>
		</div>
		<div class="empty-list" v-if="items.length == 0">
			<p>This restaurant don't have any item yet</p>
		</div>
		<div class="new-item-placeholder">
			<p class="new-item-placeholder-text">
				Add new item
			</p>
		</div>
	</div>
    <RestaurantItemModal v-if="showModal" v-bind:item="selectedItem" @closeModal="closeModal" />
</template>
<script>
import moment from 'moment';
import RestaurantItemModal from './RestaurantItemModal.vue';
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
            selectedItem: {}
		};
	},
	props:["restaurant"],
    components:{RestaurantItemModal},
	methods: {
		getImage(review) {
			console.log(review.image);
			return review.image;
		},
		format_date(value) {
			if (value) {
				return moment(value).format('DD.MM.YYYY.');
			}
		},
        openItem(item){
            this.selectedItem = item;
            this.showModal = true;
        },closeModal(){
            this.selectedItem = {};
            this.showModal = false;
        }
	}
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
	justify-content: center;
	min-height: calc(100% - 320px);
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
}
.item:hover{
    box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.2);
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
.item-details{
    flex: 1;
}

.item-name {
	flex: 1;
	color: #42405F;
	font-weight: bold;
}
.item-description {
	color: #8f8fa1;
	/* font-weight: 500; */
    font-size: 14px;
}
.item-body{
    flex: 1;
    padding-left: 10px;
    display: flex;
    flex-direction: column;
}
.item-price{
    color: #42405F;
    font-weight: 500;
}
.item-pricing{
    text-align: right;

}
.new-item-placeholder{
	width: 800px;
	padding: 0 17px;
	/* background-color: white; */
	border: 2px dotted #8F8FA1;
	border-radius: 20px;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-items: center;
	margin: 5px;
	cursor: pointer;
}
.new-item-placeholder:hover{
	font-weight: 600;
	box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.2);
}
.new-item-placeholder-text{
	margin: 0 auto;
	color: #8F8FA1;
	line-height: 40px;
}
.header{
	width: 888px;
	display: flex;
	align-items: center;
}
.header > *:first-child{
	flex: 1;
}
.header-new-item{
	color: #8F8FA1;
	font-weight: 500;
	cursor: pointer;
}

.empty-list{
	color: #8F8FA1;
	/* margin: 70px 0; */
	flex: 1;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-items: center;
	justify-content: center;
}
</style>
