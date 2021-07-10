<template>
	<div class="bcg-color">
		<SearchBar :advanced-filter="advancedFilter" @search="searchUsers" class="search" @restaurantSearch="filterUsers"  />
		<!-- <SortUsers class="sort" @sortName="sortName" @sortSurname="sortSurname" @sortUsername="sortUsername"  @sortPoints="sortPoints" @suspiciousUsers="suspiciousUsers" /> -->
		<AdvancedUsersSearch v-if="advancedSearch" @close-modal="closeModal" @applyFilters="applyFilters"  @suspiciousUsers="suspiciousUsers" />
		<UserTable class="tables" :users="users" @sortName="sortName" @sortSurname="sortSurname" @sortUsername="sortUsername"  @sortPoints="sortPoints"   @sortType="sortType" />
	</div>
</template>

<script>
import SearchBar from '@/components/SearchBar.vue';
import UserTable from '@/components/UserTable.vue';
import SortUsers from '@/components/SortUsers.vue';
import AdvancedUsersSearch from '@/components/AdvancedUsersSearch.vue';
import Server from '@/server';
export default {
	components: {
		UserTable,
		SearchBar,
		SortUsers,
		AdvancedUsersSearch,
	},
	data() {
		return {
			users: [],
			advancedFilter: true,
			advancedSearch: false,
			allUsers: [],
		};
	},
	methods: {
		searchUsers(text) {
			this.users = this.allUsers.filter((e) => (e.firstName + ' ' + e.lastName + ' ' + e.username).toLowerCase().includes(text.toLowerCase()));
		},
		sortName(i) {
			if (i == 1) {
				let x = this.users.sort((a, b) => (a.firstName > b.firstName ? 1 : -1));
			} else {
				let x = this.users.sort((a, b) => (a.firstName > b.firstName ? -1 : 1));
			}
		},
		sortSurname(i) {
			if (i == 1) {
				let x = this.users.sort((a, b) => (a.lastName > b.lastName ? 1 : -1));
			} else {
				let x = this.users.sort((a, b) => (a.lastName > b.lastName ? -1 : 1));
			}
		},
		sortUsername(i) {
			if (i == 1) {
				let x = this.users.sort((a, b) => (a.username > b.username ? 1 : -1));
			} else {
				let x = this.users.sort((a, b) => (a.username > b.username ? -1 : 1));
			}
		},
		sortType(i) {
			if (i == 1) {
				let x = this.users.sort((a, b) => (a.role > b.role ? 1 : -1));
			} else {
				let x = this.users.sort((a, b) => (a.role > b.role ? -1 : 1));
			}
		},
        sortPoints(i){
            const correct =   i == 1 ?  1 : -1;
			const incorrect = i == 1 ? -1 :  1;
            console.log(this.users.map(e => e.collectedPoints), correct, incorrect);
            this.users.sort((a, b) => (a?.collectedPoints > b?.collectedPoints ? correct : incorrect));
            console.log(this.users);
        },
		filterUsers() {
			this.advancedSearch = true;
			document.getElementById('appContainer').style.overflow = 'hidden';
			document.getElementById('appContainer').style.height = '100vh';
		},
		closeModal() {
			this.advancedSearch = false;
			document.getElementById('appContainer').style.overflow = 'unset';
			document.getElementById('appContainer').style.height = 'unset';
		},
		suspiciousUsers(i) {
			if (i == 1) {
				Server.getSuspiciousUsers().then((resp) => {
					console.log('Resp', resp.data);
					if (resp.success) {
						this.users = resp.data;
                        this.closeModal()
					}
				});
			} else {
				this.users = this.allUsers;
			}
		},
		applyFilters(filters) {
			this.users = this.allUsers.filter((e) => e.role.toLowerCase().includes(filters.userRole.toLowerCase()));
            console.log(filters.customerType);
            console.log(this.users.filter(e => e?.reward));
            if(!!filters.customerType)
                this.users = this.users.filter(e => !!(e?.reward?.name == filters.customerType))
			this.advancedSearch = false;
			document.getElementById('appContainer').style.overflow = 'unset';
			document.getElementById('appContainer').style.height = 'unset';
		},
	},
	async created() {
		Server.getAllUsers().then((resp) => {
			if (resp.success) {
				this.allUsers = JSON.parse(JSON.stringify(resp.data));
				this.users = resp.data;
			}
		});
	},
};
</script>

<style scoped>
.search {
	margin-top: 80px;
}
.bcg-color {
	background-color: #d7e2f8;
	width: 100%;
	min-height: calc(100% - 70px);
}
.tables {
	padding-top: 75px;
}
.sort {
	margin-top: 130px;
}

.toggle {
	width: 100px;
	height: 100px;
}
</style>
