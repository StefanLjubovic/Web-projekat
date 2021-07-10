<template>
	<div class="home">
		<!-- <Header @login-user="loginUser"/> -->
		<div class="home-container">
			<SearchBar :advanced-filter="advancedFilter" @restaurantSearch="restaurantSearch" @search="filterRestaurants" />
			<SortRestaurants @sortName="sortName" @sortLocation="sortLocation" @sortAverageGrade="sortAverageGrade" class="sort" />
			<Restaurants @restaurant-info="goToRestaurant" :restaurants="restaurants" class="restaurants" />
			<AdvancedRestaurantSearch @close-modal="closeModal" v-if="advancedSearch" @applyFilters="applyFilters" />
		</div>
	</div>
</template>

<script>
// @ is an alias to /src
import Header from '@/components/Header.vue';
import Restaurants from '@/components/Restaurants.vue';
import SearchBar from '@/components/SearchBar.vue';
import UserOptions from '@/components/UserOptions.vue';
import SortRestaurants from '@/components/SortRestaurants.vue';
import AdvancedRestaurantSearch from '@/components/AdvancedRestaurantSearch.vue';
import Server from '@/server';
export default {
	watch: {
		$route(to, from) {},
	},
	data() {
		return {
			restaurants: [],
			allRestaurants: [],
			restaurant: {},
			advancedFilter: true,
			advancedSearch: false,
			restaurantsFromServer: [],
		};
	},
	methods: {
		filterRestaurants(text) {
			this.restaurants = this.allRestaurants.filter(
				(e) =>
					e.name.toLowerCase().includes(text.toLowerCase()) ||
					e.location.address.toLowerCase().includes(text.toLowerCase()) ||
					e.type.toLowerCase().includes(text.toLowerCase()) ||
					e.grade
						.toString()
						.toLowerCase()
						.includes(text.toLowerCase())
			);
		},
		loginUser() {
			this.$router.push({ path: '/login' });
		},
		goToRestaurant(name) {
			this.restaurants.forEach((value, index) => {
				if (value.name === name) this.restaurant = value;
			});
		},
		closeModal() {
			this.advancedSearch = false;
			document.getElementById('appContainer').style.overflow = 'unset';
			document.getElementById('appContainer').style.height = 'unset';
		},
		restaurantSearch() {
			this.advancedSearch = true;
			document.getElementById('appContainer').style.overflow = 'hidden';
			document.getElementById('appContainer').style.height = '100vh';
		},
		sortName(i) {
			if (i == 1) {
				let x = this.restaurants.sort((a, b) => (a.name > b.name ? 1 : -1));
			} else {
				let x = this.restaurants.sort((a, b) => (a.name > b.name ? -1 : 1));
			}
		},
		sortLocation(i) {
			if (i == 1) {
				let x = this.restaurants.sort((a, b) => (a.location.address > b.location.address ? 1 : -1));
			} else {
				let x = this.restaurants.sort((a, b) => (a.location.address > b.location.address ? -1 : 1));
			}
		},
		applyFilters(filters) {
			if (filters.openRestaurants != 'Open') {
				this.restaurants = this.allRestaurants.filter((e) => e.type.toLowerCase().includes(filters.restaurantType.toLowerCase()));
			} else {
				this.restaurants = this.allRestaurants.filter((e) => e.type.toLowerCase().includes(filters.restaurantType.toLowerCase()) && e.opened);
			}
			this.advancedSearch = false;
			document.getElementById('appContainer').style.overflow = 'unset';
			document.getElementById('appContainer').style.height = 'unset';
		},
		sortAverageGrade(i) {
			const correct = i == 1 ? 1 : -1;
			const incorrect = i == 1 ? -1 : 1;
			this.restaurants.sort((a, b) => (a.grade > b.grade ? correct : incorrect));
		},
	},
	async created() {
		// this.restaurants = allRestaurants;
		Server.getAllRestaurants().then((resp) => {
			console.log('Resp', resp.data);
			if (resp.success) {
				this.restaurants = resp.data;
				this.allRestaurants = JSON.parse(JSON.stringify(resp.data));
				let x = this.restaurants.sort((a, b) => (a.status > b.status ? 1 : -1));
			}
		});
	},
	name: 'Home',
	components: {
		Header,
		Restaurants,
		SearchBar,
		UserOptions,
		SortRestaurants,
		AdvancedRestaurantSearch,
	},
};
</script>

<style scoped>
.sort {
	margin-top: 50px;
}

.home-container {
	position: relative;
	flex: 1;
	display: flex;
	flex-direction: column;
}
.home {
	display: flex;
	flex-direction: column;
	min-height: calc(100vh - 70px);
	position: relative;
	background-color: #d7e2f8;
}
.restaurants {
	height: 100%;
	overflow: auto;
	margin-top: 50px;
}
</style>
