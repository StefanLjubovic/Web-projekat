<template >
	<div class="home">
		<!-- <Header @login-user="loginUser"/> -->
		<div class="home-container" >
			<SearchBar :advanced-filter="advancedFilter" @restaurantSearch="restaurantSearch"  @search="filterRestaurants"/>
			<SortRestaurants  @sortName="sortName" @sortLocation="sortLocation" @sortAverageGrade="sortAverageGrade" class="sort"/>
			<Restaurants @restaurant-info="goToRestaurant" :restaurants="restaurants" class="restaurants" />
			<AdvancedRestaurantSearch @close-modal="closeModal" v-if="advancedSearch" @applyFilters="applyFilters"/>
		</div>
	</div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header.vue";
import Restaurants from "@/components/Restaurants.vue";
import SearchBar from "@/components/SearchBar.vue";
import UserOptions from "@/components/UserOptions.vue";
import SortRestaurants from "@/components/SortRestaurants.vue";
import AdvancedRestaurantSearch from "@/components/AdvancedRestaurantSearch.vue";
import Server from '@/server'
export default {
	watch: {
		$route(to, from) {},
	},
	data() {
		return {
			restaurants: [],
			restaurant:{},
			advancedFilter:true,
			advancedSearch: false,
			restaurantsFromServer: []
		};
	},
	methods:{
		filterRestaurants(text){
			this.restaurants = this.restaurants.filter(e => e.name.toLowerCase().includes(text.toLowerCase())
			|| e.location.address.toLowerCase().includes(text.toLowerCase()) || e.type.toLowerCase().includes(text.toLowerCase())
			|| e.grade.toString().toLowerCase().includes(text.toLowerCase()));
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
		closeModal(){
			this.advancedSearch=false;
			document.getElementById('appContainer').style.overflow = 'unset';
          document.getElementById('appContainer').style.height = 'unset';
		},
		restaurantSearch(){
			this.advancedSearch=true;
			document.getElementById('appContainer').style.overflow = 'hidden';
          	document.getElementById('appContainer').style.height = '100vh';
		},
		sortName(i){
			if(i==1){
				 let x = this.restaurants.sort((a, b) => (a.name > b.name ? 1 : -1));
			}else{
				let x = this.restaurants.sort((a, b) => (a.name > b.name ? -1 : 1));
			}
		},
		sortLocation(i){
			if(i==1){
				let x = this.restaurants.sort((a, b) => (a.location.address > b.location.address ? 1 : -1));
			}else{
				let x = this.restaurants.sort((a, b) => (a.location.address > b.location.address ? -1 : 1));
			}
		},
		applyFilters(filters){
			if(filters.openRestaurants!="Open"){
			this.restaurants = this.restaurants.filter(e => e.type.toLowerCase().includes(filters.restaurantType.toLowerCase()))
			}else{
				this.restaurants = this.restaurants.filter(e => e.type.toLowerCase().includes(filters.restaurantType.toLowerCase())
				&& !e.status)
			}
			this.advancedSearch=false;
			document.getElementById('appContainer').style.overflow = 'unset';
          document.getElementById('appContainer').style.height = 'unset';		
		},
		sortAverageGrade(i){

		}
	},
	async created() {
		// this.restaurants = allRestaurants;
		Server.getAllRestaurants().then(resp => {
			console.log("Resp", resp.data);
			if(resp.success){
				this.restaurants = resp.data;
				console.log(this.restaurants);
			}
		})
	},
	name: "Home",
	components: {
		Header,
		Restaurants,
		SearchBar,
		UserOptions,
		SortRestaurants,
		AdvancedRestaurantSearch
	},
};
</script>

<style scoped>
.sort{
	margin-top:50px;
}

.home-container {
	/* padding-top: 10px; */
	position: relative;
	flex: 1;
	display: flex;
	flex-direction: column;
	/* width: 80%; */
	/* margin: auto; */
	/* overflow: auto; */
}
.home{
	display: flex;
	flex-direction: column;
	min-height: 100vh;
	
	/* max-height: 100vh; */
	position: relative;
	background-color: #D7E2F8;
	/* overflow: hidden; */
}
.restaurants{
	height: 100%;
	overflow: auto;
	margin-top:50px;
}

</style>
