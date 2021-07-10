<template>
	<div style="width: 70%; margin: auto">
		<h3>Reviews</h3>
	</div>
	<div class="reviews-list">
        <div class="empty-reviews" v-if="reviews.length == 0">
            There aren't any review for this restaurant....
        </div>
		<div :key="review.username" v-for="review in reviews">
			<div class="review" v-if="checkReviewVisibility(review)" v-bind:class="{ selected: !checkUser(review) }" @click="approveReview(review)">
				<div class="review-message">
					{{ review.grade.comment }}
				</div>
				<div class="review-details">
					<label class="food-type">{{ review.food }}</label>
					<label class="date">{{ format_date(review.grade.date) }}</label>
				</div>
				<div class="review-user">
					<img class="user-image" :src="getImage(review)" alt="" />
					<label class="username" for="username">{{ review.user.username }}</label>
					<div class="restaurant-status username approved">
						{{ getApproved(review) }}
					</div>
					<div class="grade">
					<i class="fas fa-star icon" style="color: #FAE480"></i>
					<label  class="username">{{review.grade.grade}}</label>
					</div>
				</div>
			</div>
		</div>
	</div>
	<ConfirmModal :reject="reject" v-if="confirmModal" :title="title" :description="description" @rejectConfirm="rejectConfirm" @saveConfirm="saveConfirm" @cancelConfirm="closeConfirmModal" />
</template>
<script>
import ConfirmModal from '@/components/ConfirmModal.vue';
import moment from 'moment';
import Server from '@/server';
export default {
	computed: {
		user() {
			return this.$store.getters.getUser;
		},
	},
	props: {
		restaurant: Object,
	},
	components: {
		ConfirmModal,
	},
	data() {
		return {
			confirmModal: false,
			title: 'Approve review',
			description: 'If you approve review all customers will see it.',
			selectedReview: {},
			reviews: [],
			reject: true
		};
	},
	props: ['restaurant'],
	methods: {
		getImage(review) {
			console.log(review.image);
			return 'https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png';
		},
		format_date(value) {
			if (value) {
				return moment(value).format('DD.MM.YYYY.');
			}
		},
		getApproved(review) {
			console.log(review);
			if(review.grade.approved=="WaitingForApproval")
				return 'Waiting approval 🕐'
			else if(review.grade.approved=="Approved")
				return `Approved 👍🏼`
			else
				return 'Rejected 👎🏼'
		},
		checkReviewVisibility(review) {
			if (
				(this.user.role == 'Customer' && (review.grade.approved != "Approved")) ||
				(this.user.restaurantId != this.restaurant.id && this.user.role == 'Manager' && review.grade.approved != "Approved") ||
				(!this.user.firstName && review.grade.approved != "Approved")
				|| (this.user.role == 'Deliverer' && review.grade.approved != "Approved")
			)
				return false;
			return true;
		},
		checkUser(review) {
			if (this.user.role == 'Manager' && review.grade.approved == "WaitingForApproval") return true;
			return false;
		},
		approveReview(review) {
			this.selectedReview = review;
			this.confirmModal = true;
			document.getElementById('appContainer').style.overflow = 'hidden';
			document.getElementById('appContainer').style.height = '100vh';
		},
		closeConfirmModal() {
			this.confirmModal = false;
			document.getElementById('appContainer').style.overflow = 'unset';
			document.getElementById('appContainer').style.height = 'unset';
		},
		saveConfirm() {
			this.confirmModal = false;
			document.getElementById('appContainer').style.overflow = 'unset';
			document.getElementById('appContainer').style.height = 'unset';
			this.selectedReview.grade.approved = "Approved";
			Server.updateReview(this.selectedReview).then((resp) => {
				if (resp.success) {
					this.selectedReview = resp.data;
                    this.$emit("refreshRestaurant")
				}
			});
		},
		rejectConfirm(){
			this.confirmModal = false;
			document.getElementById('appContainer').style.overflow = 'unset';
			document.getElementById('appContainer').style.height = 'unset';
			console.log(this.selectedReview)
			this.selectedReview.grade.approved = "Rejected";
			Server.updateReview(this.selectedReview).then((resp) => {
				if (resp.success) {
					this.selectedReview = resp.data;
                    this.$emit("refreshRestaurant")
				}
			});
		}
	},
	mounted() {
		const id = this.$route.params.id;
		console.log(id);
		Server.getRestaurantReviews(id).then((resp) => {
			if (resp.success) {
				console.log(resp.data);
				this.reviews = resp.data;
			}
		});
	},
    emits:['refreshRestaurant']
};
</script>

<style scoped>
.reviews-list {
	display: flex;
	flex-wrap: wrap;
	padding: 10px 20px;
	position: relative;
	width: 100%;
    width: 888px;
    margin: auto;
	/* justify-content: center; */
}
.review {
	cursor: pointer;
	width: 400px;
	padding: 17px;
	background-color: white;
	border-radius: 20px;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
	display: flex;
	flex-direction: column;
	height: calc(100% - 10px);
	margin: 5px;
}
.grade{
	align-self: center;
	float:right;
}

.selected {
	pointer-events: none;
}
.review-user {
	overflow:auto;
	margin-top: 5px;
	border-top: 2px solid #8f8fa1;
	padding-top: 5px;
	display: flex;
	flex-direction: row;
	align-items: center;
}
.user-image {
	border-radius: 100%;
	width: 50px;
}
.username {
	padding-left: 10px;
	color: #8f8fa1;
	font-weight: 600;
}

.food-type {
	margin-top: 5px;
	margin-bottom: unset;
	color: #8f8fa1;
	font-size: 14px;
	flex: 1;
}
.approved {
	right: 5%;
	margin-top: -10px;
}
.review-message {
	flex: 1;
	color: #8f8fa1;
}
.review-details {
	display: flex;
}

.date {
	/* margin-top: 5px; */
	margin-bottom: unset;
	color: #8f8fa1;
	font-size: 14px;
	font-weight: 600;
}
.empty-reviews{
    color: #8f8fa1;
    font-weight: 500;
    text-align: center;
    width: 100%;
    padding: 10%;
}
</style>
