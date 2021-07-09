<template>
<div class="bcg-color">
    <SearchBar :advanced-filter="advancedFilter" @search="searchUsers" class="search" @restaurantSearch="filterUsers"/>
    <SortUsers class="sort" @sortName="sortName" @sortSurname="sortSurname" @sortUsername="sortUsername"/>
        <AdvancedUsersSearch v-if="advancedSearch" @close-modal="closeModal" @applyFilters="applyFilters"/>
    <UserTable class="tables" :users="users"/>
</div>
</template>

<script>
import SearchBar from "@/components/SearchBar.vue";
import UserTable from "@/components/UserTable.vue";
import SortUsers from "@/components/SortUsers.vue";
import AdvancedUsersSearch from "@/components/AdvancedUsersSearch.vue";
import Server from '@/server'
export default {
    components:{
        UserTable,
        SearchBar,
        SortUsers,
        AdvancedUsersSearch
    },
    data(){
        return{
            users:[],
            advancedFilter: true,
            advancedSearch: false,
            allUsers: []
        }
    },
    methods:{
        searchUsers(text){
            this.users = this.users.filter(e => (e.firstName+' '+e.lastName +' '+ e.username).toLowerCase().includes(text.toLowerCase()));
        },
        sortName(i){
            if(i==1){
                let x = this.users.sort((a, b) => (a.firstName > b.firstName ? 1 : -1));
            }else{
                let x = this.users.sort((a, b) => (a.firstName > b.firstName ? -1 : 1));
            }
        },
        sortSurname(i){
            if(i==1){
                let x = this.users.sort((a, b) => (a.lastName> b.lastName ? 1 : -1));
            }else{
                let x = this.users.sort((a, b) => (a.lastName > b.lastName ? -1 : 1));
            }
        },
        sortUsername(i){
            if(i==1){
                let x = this.users.sort((a, b) => (a.username> b.username ? 1 : -1));
            }else{
                let x = this.users.sort((a, b) => (a.username > b.username ? -1 : 1));
            }
        },
        filterUsers(){
            this.advancedSearch = true;
			document.getElementById('appContainer').style.overflow = 'hidden';
			document.getElementById('appContainer').style.height = '100vh';
        },
        closeModal(){
            this.advancedSearch = false;
           document.getElementById('appContainer').style.overflow = 'unset';
          document.getElementById('appContainer').style.height = 'unset'; 
        },
        applyFilters(filters){

            this.users = this.allUsers.filter((e) => e.role.toLowerCase().includes(filters.userRole.toLowerCase()));
            this.advancedSearch = false;
           document.getElementById('appContainer').style.overflow = 'unset';
          document.getElementById('appContainer').style.height = 'unset';
        }
    },
    async created() {
        Server.getAllUsers().then(resp=>{
          if(resp.success){
              	this.allUsers = JSON.parse(JSON.stringify(resp.data));
				this.users=resp.data;
		}
        });
		
	},
}
</script>

<style scoped>
    .search{
        margin-top: 80px;
    }
    .bcg-color{
         background-color: #D7E2F8;
         width: 100%;
         height: 100%;
    }
    .tables{
        margin-top:135px;
    }
    .sort{
        margin-top: 130px;
    }
</style>