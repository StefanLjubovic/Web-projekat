<template >
	<div class="home">
		<!-- <Header @login-user="loginUser"/> -->
		<div class="home-container" >
			<SearchBar  @search="filterRestaurants"/>
			<Restaurants @restaurant-info="goToRestaurant" :restaurants="restaurants" class="restaurants" />
		</div>
	</div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header.vue";
import Restaurants from "@/components/Restaurants.vue";
import SearchBar from "@/components/SearchBar.vue";
import UserOptions from "@/components/UserOptions.vue";
const allRestaurants = [
		{
			name: "Front",
			description: "Chinese restaurant with saul",
			type: "chinese",
			status: true,
			logo: 'petrus.jpg',
			location: "Sutjeska 3",
			grade: "4.3",
		},
		{
			name: "Petrus",
			description: "Modern restaurant in the city center",
			type: "barbecue",
			status: false,
			logo: 'petrus.jpg',
			location: "Bulevar 4",
			grade: "4.7",
		},
		{
			name: "Petrus",
			description: "Modern restaurant in the city center",
			type: "barbecue",
			status: false,
			logo: 'petrus.jpg',
			location: "Bulevar 4",
			grade: "4.7",
		},{
			name: "Petrus",
			description: "Modern restaurant in the city center",
			type: "barbecue",
			status: false,
			logo: 'petrus.jpg',
			location: "Bulevar 4",
			grade: "4.7",
		},{
			name: "Petrus",
			description: "Modern restaurant in the city center",
			type: "barbecue",
			status: false,
			logo: 'petrus.jpg',
			location: "Bulevar 4",
			grade: "4.7",
		},{
			name: "Petrus",
			description: "Modern restaurant in the city center",
			type: "barbecue",
			status: false,
			logo: 'petrus.jpg',
			location: "Bulevar 4",
			grade: "4.7",
		},{
			name: "Petrus",
			description: "Modern restaurant in the city center",
			type: "barbecue",
			status: false,
			logo: 'petrus.jpg',
			location: "Bulevar 4",
			grade: "4.7",
		},
	]

export default {
	watch: {
		$route(to, from) {},
	},
	data() {
		return {
			restaurants: [],
			restaurant:{}
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
	},
	created() {
		this.restaurants = allRestaurants;
	},
	name: "Home",
	components: {
		Header,
		Restaurants,
		SearchBar,
		UserOptions,
	},
};
</script>

<style scoped>
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
}

</style>
