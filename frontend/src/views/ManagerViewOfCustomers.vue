<template>
	<div class="home">
		<!-- <Header @login-user="loginUser"/> -->
		<div class="home-container">
			<SearchBar class="search" :advanced-orders="advancedOrders" @orders-search="openModal" @search="search" />
			<SortOrder @sortName="sortByName" @sortPrice="sortByPrice" @sortDate="sortByDate" class="sorters" />
			<OrdersSearch v-if="showModal" @close-modal="closeModal" class="advanced" @applyFilters="applyFilters" />
			<CustomerOrders :orders="orders" :filters="filters" class="orders" @openDialog="openDialog" />
			<CommentModal @close-modal="closeComment" v-if="showReview" @saveReview="saveReview" />
			<ConfirmModal v-if="confirmModal" :title="title" :description="description" @saveConfirm="saveConfirm" @cancelConfirm="closeConfirmModal" />
		</div>
	</div>
</template>

<script>
import CustomerOrders from '@/components/CustomerOrders.vue';
import OrdersSearch from '@/components/OrdersSearch.vue';
import SearchBar from '@/components/SearchBar.vue';
import SortOrder from '@/components/SortOrders.vue';
import CommentModal from '@/components/CommentModal.vue';
import Comment from '@/components/Comment.vue';
import ConfirmModal from '@/components/ConfirmModal.vue';
import Server from '@/server';
export default {
	data() {
		return {
			orders: [],
			allOrders: [],
			advancedOrders: true,
			filters: {},
			showModal: false,
			showReview: false,
			title: 'Change order status',
			description: 'Are you sure you want to change status from in preparation to waiting for delivery?',
			confirmModal: false,
			modifiedOrder: {},
		};
	},
	computed: {
		user() {
			return this.$store.getters.getUser;
		},
	},
	components: {
		CustomerOrders,
		SearchBar,
		OrdersSearch,
		SortOrder,
		CommentModal,
		Comment,
		ConfirmModal,
	},
	mounted() {
		// this.user = this.$store.getters.getUser;
		const id = this.$route.params.id;
		Server.getAllOrders(id).then((resp) => {
			if (resp.success) {
				this.orders = resp.data;
                this.allOrders = [...this.orders];
			}
		});
		let sum = 0;
	},
	methods: {
		sortByName(i) {
			const correct = i == 1 ? 1 : -1;
			const incorrect = i == 1 ? -1 : 1;
			this.orders.sort((a, b) => (a.user?.firstName > b.user?.firstName ? correct : incorrect));
		},
		sortByPrice(i) {
			const correct = i == 1 ? 1 : -1;
			const incorrect = i == 1 ? -1 : 1;
			this.orders.sort((a, b) => (a.order.price > b.order.price ? correct : incorrect));
		},
		sortByDate(i) {
			const correct =   i == 1 ?  1 : -1;
			const incorrect = i == 1 ? -1 :  1;
			this.orders.sort((a, b) => (new Date(a.order.date) > new Date(b.order.date) ? correct : incorrect));
		},
		applyFilters(filters, search) {
			this.filters = filters;
            console.log("search", filters);
            const maxPrice = !!search.priceTo ? parseInt(search.priceTo) : Number.MAX_SAFE_INTEGER;
            const minPrice = !!search.priceFrom ? parseInt(search.priceFrom) : Number.MIN_SAFE_INTEGER;

            const minDate = !!filters.startDate ? (new Date(filters.startDate)).setHours(0,0,0,0) : new Date(0);
            const maxDate = !!filters.endDate ? (new Date(filters.endDate)).setHours(23,59,59,0) : new Date();
			this.orders = this.allOrders.filter(
				(e) =>
					e.restaurant.type.toLowerCase().includes(filters.restaurantType.toLowerCase()) &&
					e.order.status.toLowerCase().includes(filters.orderStatus.toLowerCase()) &&
                    e.order.price < maxPrice && e.order.price > minPrice && 
                    minDate < new Date(e.order.date) && new Date(e.order.date) < maxDate
			);
			this.showModal = false;
			document.getElementById('appContainer').style.overflow = 'unset';
			document.getElementById('appContainer').style.height = 'unset';
		},
		search(name) {
			this.searchBar = name;
			this.orders = this.orders.filter((e) => e.restaurant.name.toLowerCase().includes(this.searchBar.toLowerCase()));
		},
		openModal() {
			this.showModal = true;
			document.getElementById('appContainer').style.overflow = 'hidden';
			document.getElementById('appContainer').style.height = '100vh';
		},
		closeModal() {
			this.showModal = false;
			document.getElementById('appContainer').style.overflow = 'unset';
			document.getElementById('appContainer').style.height = 'unset';
		},
		saveReview(rating, comment) {
			console.log(rating);
			console.log(comment);
			const retVal = {
				userId: this.user.id,
				restaurantId: this.modifiedOrder.restaurant.id,
				comment: comment,
				grade: parseInt(rating),
			};
			Server.createReview(retVal).then((resp) => {
				console.log('Resp', resp.data);
			});
			this.showReview = false;
			document.getElementById('appContainer').style.overflow = 'unset';
			document.getElementById('appContainer').style.height = 'unset';
		},
		closeComment() {
			this.showReview = false;
			document.getElementById('appContainer').style.overflow = 'unset';
			document.getElementById('appContainer').style.height = 'unset';
		},

		openDialog(order) {
			console.log(this.user);
			if (this.user.role == 'Manager' && order.order.status == 'InPreparation') {
				this.modifiedOrder = order;
				this.confirmModal = true;
				document.getElementById('appContainer').style.overflow = 'hidden';
				document.getElementById('appContainer').style.height = '100vh';
			} else if (this.user.role == 'Customer' && order.order.status == 'Delivered') {
				this.modifiedOrder = order;
				this.showReview = true;
				document.getElementById('appContainer').style.overflow = 'hidden';
				document.getElementById('appContainer').style.height = '100vh';
			} else if (this.user.role == 'Customer' && order.order.status == 'InPreparation') {
				this.modifiedOrder = order;
				this.description = 'Are you sure you want to cancel your order?';
				this.confirmModal = true;
				document.getElementById('appContainer').style.overflow = 'hidden';
				document.getElementById('appContainer').style.height = '100vh';
			} else if (this.user.role == 'Deliverer' && order.order.status == 'InTransport') {
				this.modifiedOrder = order;
				this.description = 'Are you sure you want to change status from in transport to delivered?';
				this.confirmModal = true;
				document.getElementById('appContainer').style.overflow = 'hidden';
				document.getElementById('appContainer').style.height = '100vh';
			} else if (this.user.role == 'Deliverer' && order.order.status == 'WaitingForDelivery') {
				this.modifiedOrder = order;
				this.description = 'Send request?';
				this.confirmModal = true;
				document.getElementById('appContainer').style.overflow = 'hidden';
				document.getElementById('appContainer').style.height = '100vh';
			} else if (this.user.role == 'Manager' && order.order.status == 'Processing') {
				this.modifiedOrder = order;
				this.description = 'Confirm delivery request?';
				this.confirmModal = true;
				document.getElementById('appContainer').style.overflow = 'hidden';
				document.getElementById('appContainer').style.height = '100vh';
			}
		},

		closeConfirmModal() {
			this.confirmModal = false;
			document.getElementById('appContainer').style.overflow = 'unset';
			document.getElementById('appContainer').style.height = 'unset';
		},
		saveConfirm() {
			console.log(this.modifiedOrder.order.status);
			console.log(this.user.role);
			if (this.user.role == 'Manager' && this.modifiedOrder.order.status == 'InPreparation') {
				this.modifiedOrder.order.status = 'WaitingForDelivery';
				this.updateOrderStatus();
				this.confirmModal = false;
				document.getElementById('appContainer').style.overflow = 'unset';
				document.getElementById('appContainer').style.height = 'unset';
			} else if (this.user.role == 'Deliverer' && this.modifiedOrder.order.status == 'InTransport') {
				this.modifiedOrder.order.status = 'Delivered';
				this.updateOrderStatus();
				this.confirmModal = false;
				document.getElementById('appContainer').style.overflow = 'unset';
				document.getElementById('appContainer').style.height = 'unset';
			} else if (this.user.role == 'Deliverer' && this.modifiedOrder.order.status == 'WaitingForDelivery') {
				this.confirmModal = false;
				for (var i = 0; i < this.orders.length; i++) {
					if (this.orders[i].order.id == this.modifiedOrder.order.id) {
						this.orders[i].order.status = 'Processing';
						this.orders[i].deliverer = this.user;
						this.orders[i].order.delivererId = this.user.id;
						break;
					}
				}
				this.updateOrderStatus();
				document.getElementById('appContainer').style.overflow = 'unset';
				document.getElementById('appContainer').style.height = 'unset';
			} else if (this.user.role == 'Manager' && this.modifiedOrder.order.status == 'Processing') {
				this.confirmModal = false;
				for (var i = 0; i < this.orders.length; i++) {
					if (this.orders[i].order.id == this.modifiedOrder.order.id) {
						this.orders[i].order.status = 'InTransport';
						break;
					}
				}
				this.updateOrderStatus();
				document.getElementById('appContainer').style.overflow = 'unset';
				document.getElementById('appContainer').style.height = 'unset';
			} else if (this.user.role == 'Customer' && this.modifiedOrder.order.status == 'InPreparation') {
				this.confirmModal = false;
				for (var i = 0; i < this.orders.length; i++) {
					if (this.orders[i].order.id == this.modifiedOrder.order.id) {
						this.orders[i].order.status = 'Canceled';
						break;
					}
				}
				this.updateOrderStatus();
				document.getElementById('appContainer').style.overflow = 'unset';
				document.getElementById('appContainer').style.height = 'unset';
			}
		},
		updateOrderStatus() {
			Server.updateOrderStatus(this.modifiedOrder).then((resp) => {
				if (resp.success) {
					this.modifiedOrder = resp.data;
				}
			});
		},
	},
};
</script>

<style scoped>
.home-container {
	/* padding-top: 10px; */
	display: flex;
	flex-direction: column;
	min-height: calc(100vh - 70px);
	position: relative;
	background-color: #d7e2f8;
	/* width: 80%; */
	/* margin: auto; */
	/* overflow: auto; */
}
.advanced {
	margin-top: 70px;
	margin-bottom: -85px;
}
.sorters {
	margin-top: 50px;
}
.home {
	display: flex;
	flex-direction: column;
	min-height: calc(100vh - 70px);

	position: relative;
	background-color: #d7e2f8;
	/* overflow: hidden; */
}
.orders {
	height: 100%;
	overflow: auto;
	margin-top: 50px;
}
</style>
