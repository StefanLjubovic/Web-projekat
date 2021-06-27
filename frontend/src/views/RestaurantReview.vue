<template >
	<div class="home">
		<!-- <Header @login-user="loginUser"/> -->
		<RestaurantInfo />
		<RestaurantNavigator @change-view="changeView" />
		<RestaurantInformations v-if="selectedView == 'informations'"/>
		<RestaurantReviews v-if="selectedView == 'reviews'"/>
		<RestaurantItems v-if="selectedView == 'items'"/>
	</div>
</template>
<script>
import Header from "@/components/Header.vue";
import Restaurants from "@/components/Restaurants.vue";
import SearchBar from "@/components/SearchBar.vue";
import RestaurantInfo from '@/components/RestaurantInfo.vue'
import RestaurantNavigator from '@/components/RestaurantNavigator.vue'

import RestaurantItems from '@/components/RestaurantViews/RestaurantItems.vue'
import RestaurantReviews from '@/components/RestaurantViews/RestaurantReviews.vue'
import RestaurantInformations from '@/components/RestaurantViews/RestaurantInformations.vue'

export default {
	
	data() {
		return {
			restaurants: [],
			restaurant:{},
			selectedView: 'reviews'
		};
	},
	methods:{
		filterRestaurants(text){
			this.restaurants = allRestaurants.filter(e => e.name.toLowerCase().includes(text.toLowerCase()));
		},
		loginUser(){
      		this.$router.push({ path: '/login' });
    	},
		goToRestaurant(name){
			this.restaurants.forEach((value,index)=>{
				if(value.name===name)
					this.restaurant=value;
			});
			
		},
		changeView(view){
			this.selectedView = view;
			console.log(`Changing view into ${view}`);
		}
	},
	created() {
		// this.restaurants = allRestaurants;
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
		RestaurantInformations
	},
};
</script>

<style scoped>
    .home{
		padding-top: 10px;
		/* flex: 1; */
		min-height: calc(100% - 140px);
		background-color: #D7E2F8;
    }
</style>