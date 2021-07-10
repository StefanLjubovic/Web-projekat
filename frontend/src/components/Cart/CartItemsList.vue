<template>
	<div class="cart-header-container">
		<h3>This is your cart</h3>
		<label for="" class="sutitle">Feel free to change amout of items or simply remove some of them</label>
		<div class="items-list" v-if="!!cart?.items?.length">
			<div class="item" v-for="(item, index) in group(cart.items)" :key="item">
				<div class="no">#{{ index + 1 }}</div>
				<div class="item-image-container">
					<img class="item-image" :src="getImage(item.image)" alt="" />
				</div>
				<div class="item-body">
					<p class="item-name">{{ item.name }}</p>
					<p class="item-description">{{ item.description }}</p>
					<p class="item-size">Portion: {{ item.size }}g</p>
				</div>
				<div class="item-information">
					<div class="price-section">
						<p class="price-title">Price</p>
						<p class="price-info">
							Item price: <span class="price-label"> {{ item.price.toFixed(2) }} RSD</span>
						</p>
						<p class="price-info">
							Amount:
							<span class="price-label"
								><span class="changeAmount" @click="decrease(item)"><i class="fas fa-minus"></i></span>
								<span class="count">{{ item.count }}</span>
								<span class="changeAmount" @click="increase(item)"><i class="fas fa-plus"></i></span>
							</span>
						</p>
					</div>
					<p class="total-item-price">
						Total price: <span class="price-label">{{ (item.price * item.count).toFixed(2) }} RSD</span>
					</p>
				</div>
				<div class="delete-section" @click="deleteItem(item)">
					<div>
						<i class="fas fa-times"></i>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
import Swal from 'sweetalert2';
import server from '../../server';
export default {
	watch: {
		$route(to, from) {},
	},
	computed: {
		cart() {
			return this.$store.getters.getCart;
		},
	},
	methods: {
		async deleteItem(item) {
			const confirm = await Swal.fire({
				title: 'Do you want to remove this item from cart?',
				text: "You won't be able to revert this!",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#FC4C59',
				cancelButtonColor: '#5E639B',
				confirmButtonText: 'Yes, remove it!'
				});
			if(confirm.isConfirmed){



				this.$store.commit('removeItems', item);
			this.$nextTick(() => {
				if (this.cart.items.length == 0) {
					this.$router.push('/');
					Swal.fire({
						title: 'Cart is empty',
						icon: 'info',
						confirmButtonText: 'Okay',
						timer: 2000,
						timerProgressBar: true,
					});
				}
			});
						}
		},
		isEmpty() {
			return !this.$store.getters.getCart.items?.length;
		},
		group() {
			let ordersDict = {};
			for (const item of this.cart.items) {
				if (!ordersDict[item.name]) ordersDict[item.name] = { count: 0, ...item };
				ordersDict[item.name].count++;
			}
			return Object.values(ordersDict);
		},
		getImage(image) {
			return server.getImage(image);
		},
		checkout() {
			console.log('Checkout');
			this.$router.push({ name: 'Checkout' });
		},
		totalPrice() {
			return this.cart.items.reduce((a, b) => a + b.price, 0);
		},
		decrease(item) {
			this.$store.commit('removeItem', item);
		},
		increase(item) {
			this.$store.commit('addItem', item);
		},
	},
	created() {
		// this.user = User;
	},
	emits: [],
};
</script>
<style scoped>
.sutitle {
	color: #8f8fa1;
	font-weight: 600;
}
.item {
	display: flex;
	flex-direction: row;
	/* align-items: center; */
	margin: 10px 0;
	position: relative;
	border-bottom: 2px solid #8f8fa1;
	padding-bottom: 10px;
}
.title {
	color: #5e639b;
	font-weight: 600;
}
.item-image-container {
	overflow: hidden;
	border-radius: 10px;
}
.item-image {
	width: 230px;
	/* max-height: 100px; */
	object-fit: contain;
}
.item-name {
	font-weight: 600;
	margin-bottom: 10px;
	color: #5e639b;
	font-size: 20px;
}
.item-description {
	font-weight: 400;
	margin-bottom: 10px;
	color: #42405f;
	/* font-size: 20px; */
}
.item-body {
	height: 100%;
	margin-left: 10px;
	flex: 1;
}
.price-info {
	color: #8f8fa1;
	font-size: 14px;
	font-weight: 500;
	margin-bottom: unset;
	margin-right: 10px;
}
.total-item-price {
	color: #1f1f43;
	font-size: 17px;
	font-weight: 500;
	margin-bottom: unset;
	margin-right: 10px;
}
.price-title {
	color: #8f8fa1;
	font-size: 17px;
	font-weight: 600;
	margin-bottom: unset;
	margin-right: 10px;
}
.price-label {
	font-weight: 600;
	align-self: flex-end;
	float: right;
	margin-left: 10px;
}
.item-size {
	color: #8f8fa1;
	font-weight: 500;
}
.no {
	color: #8f8fa1;
	/* font-size: 14px; */
	font-weight: bold;
	margin-right: 10px;
	padding-top: 20px;
}
.delete-section {
	/* background-color: #fddf6d; */
	margin-left: 20px;
	border-radius: 100px;
	transition: 0.7s ease all;
	position: absolute;
	right: 0;
	top: -10px;
	padding: 8px 10px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	color: #fc4c59;
	cursor: pointer;
	opacity: 0;
	border: 1px solid #fc4c59;
}
.price-section {
	margin-right: 0px;
	transition: 0.7s ease all;
	flex: 1;
}

.item:hover .delete-section {
	opacity: 1;
}

.count {
	margin: 0 10px;
	font-weight: bold;
}
.changeAmount {
	color: #5e639b;
	/* width: 50px; */
	padding: 1px 4px;
	/* height: 27px; */
	border-radius: 10px;
	cursor: pointer;
}
.item-information {
	display: flex;
	flex-direction: column;
}
</style>
