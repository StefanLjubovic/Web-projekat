<template >
	<div class="home">
		<!-- <Header @login-user="loginUser"/> -->
		<RestaurantInfo :restaurant="restaurant"/>
		<RestaurantNavigator @change-view="changeView" :selectedView="selectedView" />
		<RestaurantLocation v-if="selectedView == 'informations'" :restaurant="restaurant"/>
		<RestaurantReviews v-if="selectedView == 'reviews'" :restaurant="restaurant" />
		<RestaurantItems v-if="selectedView == 'items'" :restaurant="restaurant" @refreshRestaurant="refreshRestaurant"/>
	</div>
</template>
<script>
import Server from "@/server";
import Header from "@/components/Header.vue";
import Restaurants from "@/components/Restaurants.vue";
import SearchBar from "@/components/SearchBar.vue";
import RestaurantInfo from '@/components/RestaurantInfo.vue'
import RestaurantNavigator from '@/components/RestaurantNavigator.vue'

import RestaurantItems from '@/components/RestaurantViews/RestaurantItems.vue'
import RestaurantReviews from '@/components/RestaurantViews/RestaurantReviews.vue'
import RestaurantLocation from '@/components/RestaurantViews/RestaurantLocation.vue'

export default {
	
	data() {
		return {
			restaurants: [],
			restaurant:{},
			selectedView: 'items'
		};
	},
	methods:{
		changeView(view){
			this.selectedView = view;
		},
		refreshRestaurant(){
			console.log('Refreshing restaurant...');
			const id = this.$route.params.id;
			console.log(id);
			Server.getRestaurantById(id).then(resp => {
				if(resp.success){
					this.restaurant = resp.data;
					console.log(this.restaurant)
				}
			})
		}
	},
	created() {
		// this.restaurants = allRestaurants;
		const id = this.$route.params.id;
		console.log(id);
		Server.getRestaurantById(id).then(resp => {
			if(resp.success){
				this.restaurant = resp.data;
				console.log(this.restaurant)
			}
		})
	},

	name: "RestaurantReview",
	components: {
		Header,
		Restaurants,
		SearchBar,
		RestaurantInfo,
		RestaurantNavigator,
		RestaurantItems,
		RestaurantReviews,
		RestaurantLocation
	},
};
</script>

<style scoped>
    .home{
		padding-top: 10px;
		/* flex: 1; */
		min-height: calc(100% - 70px);
		background-color: #D7E2F8;
    }
</style>