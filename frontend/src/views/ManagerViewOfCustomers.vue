<template>
<div class="home">
		<!-- <Header @login-user="loginUser"/> -->
		<div class="home-container" >
        <SearchBar class="search" :advanced-orders="advancedOrders" @orders-search="openModal" @search="search" />
        <SortOrder @sortName="sortByName" @sortPrice="sortByPrice" @sortDate="sortByDate" class="sorters"/>
        <OrdersSearch v-if="showModal" @close-modal="closeModal" class="advanced" @applyFilters="applyFilters"/>
    	<CustomerOrders :users="users" :filters="filters" class="orders" @openDialog="openDialog"/>
        <CommentModal @close-modal="closeComment" v-if="showReview" @saveReview="saveReview"/>
        <ConfirmModal v-if="confirmModal" :title="title" :description="description" @cancelConfirm="closeConfirmModal"/>
        </div>
	</div>
</template>

<script>
import CustomerOrders from "@/components/CustomerOrders.vue";
import OrdersSearch from "@/components/OrdersSearch.vue";
import SearchBar from "@/components/SearchBar.vue";
import SortOrder from "@/components/SortOrders.vue";
import CommentModal from "@/components/CommentModal.vue";
import Comment from "@/components/Comment.vue";
import ConfirmModal from "@/components/ConfirmModal.vue";
const allUsers = [
		{
			name: "Pera",
			surname: "Peric",
			type: "Barbeque",
            date: "12.05.2021.",
            status: "InPreparation",
            searchBar:'',
             totalPrice: 0,
			items:[
                {
                   item: "Pljeskavica",
                   price: "300"
                },
                {
                    item: "Pljeskavica",
                   price: "300"
                },
                {
                    item: "Pljeskavica",
                   price: "300"
                }
            ]
		},
		{
			name: "Pera",
			surname: "Peric",
			type: "Italian",
             totalPrice: 0,
             status: "InPreparation",
			items:[
                {
                   item: "Pljeskavica",
                   price: "300"
                },
                {
                    item: "Pljeskavica",
                   price: "300"
                },
                {
                    item: "Pljeskavica",
                   price: "300"
                }
            ]
		},
        {
			name: "Pera",
			surname: "Peric",
			type: "Italian",
            totalPrice: 0,
            status: "InPreparation",
			items:[
                {
                   item: "Pljeskavica",
                   price: "3000"
                },
                {
                    item: "Pljeskavica",
                   price: "300"
                },
                {
                    item: "Pljeskavica",
                   price: "300"
                }
            ]
		},
        {
			name: "Dana",
			surname: "Peric",
			type: "Chinese",
             totalPrice: 0,
             status: "InPreparation",
			items:[
                {
                   item: "Pljeskavica",
                   price: "300"
                },
                {
                    item: "Pljeskavica",
                   price: "150"
                },
                {
                    item: "Pljeskavica",
                   price: "300"
                }
            ]
		},
        {
			name: "Ana",
			surname: "Peric",
			type: "chinese",
            status: "InPreparation",
             totalPrice: 0,
			items:[
                {
                   item: "Pljeskavica",
                   price: "100"
                },
                {
                    item: "Pljeskavica",
                   price: "300"
                },
                {
                    item: "Pljeskavica",
                   price: "300"
                }
            ]
		},
	]
export default {
    data(){
        return{
            users:[],
            advancedOrders:true,
            filters: {},
            showModal: false,
            showReview: false,
            title: 'Change order status',
            description: 'Are you sure you want to change status from in preparation to waiting for delivery?',
            confirmModal:false,
            user:{}
        }
    },
    components:{
        CustomerOrders,
        SearchBar,
        OrdersSearch,
        SortOrder,
        CommentModal,
        Comment,
        ConfirmModal
    },
    created(){
        this.user=this.$store.getters.getUser;
        this.users=allUsers;
        let sum=0;
        for(var i=0;i<this.users.length;i++){
            for(var j=0;j<this.users[i].items.length;j++){
                sum+=parseInt(this.users[i].items[j].price)
            }
            this.users[i].totalPrice=sum;
            sum=0;
        }
    },
    methods:{
        sortByName(i){
            console.log(i);
            if(i==1){
                let x = this.users.sort((a, b) => (a.name > b.name ? 1 : -1));
            }else{
                let x = this.users.sort((a, b) => (a.name > b.name ? -1 : 1));
            }
        },
        sortByPrice(i){
            console.log(i);
            if(i==1){
                let x = this.users.sort((a, b) => (a.totalPrice > b.totalPrice ? 1 : -1));
                console.log(x);
            }else{
                let x = this.users.sort((a, b) => (a.totalPrice > b.totalPrice ? -1 : 1));
            }
        },
        sortByDate(i){

        },
        applyFilters(filters,search){
            this.users = allUsers.filter(e => e.totalPrice<parseInt(search.priceTo) && e.totalPrice>parseInt(search.priceFrom));
            this.filters=filters;
            this.users = allUsers.filter(e => e.type.toLowerCase().includes(filters.restaurantType.toLowerCase()) && 
            e.status.toLowerCase().includes(filters.orderStatus.toLowerCase()));
            this.showModal=false;
            document.getElementById('appContainer').style.overflow = 'unset';
          document.getElementById('appContainer').style.height = 'unset';
        },
        search(name){
            this.searchBar=name;
            this.users = allUsers.filter(e => e.name.toLowerCase().includes(this.searchBar.toLowerCase()));
        },
        openModal(){
          this.showModal=true;
          document.getElementById('appContainer').style.overflow = 'hidden';
          document.getElementById('appContainer').style.height = '100vh';
        },
        closeModal(){
            this.showModal=false;
            document.getElementById('appContainer').style.overflow = 'unset';
          document.getElementById('appContainer').style.height = 'unset';
        },
        saveReview(rating,comment){
        this.showReview=false;
           document.getElementById('appContainer').style.overflow = 'unset';
          document.getElementById('appContainer').style.height = 'unset'; 
        },
        closeComment(){
            this.showReview=false;
           document.getElementById('appContainer').style.overflow = 'unset';
          document.getElementById('appContainer').style.height = 'unset'; 
        },
        openDialog(){
            if(this.user.role=="Admin"){
            this.confirmModal=true
            document.getElementById('appContainer').style.overflow = 'hidden';
          document.getElementById('appContainer').style.height = '100vh';
            }else{
            this.showReview=true
            document.getElementById('appContainer').style.overflow = 'hidden';
          document.getElementById('appContainer').style.height = '100vh';
            }
        },
        closeConfirmModal(){
            this.confirmModal=false
            document.getElementById('appContainer').style.overflow = 'hidden';
          document.getElementById('appContainer').style.height = '100vh';
        }
    }
}
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
.advanced{
    margin-top: 70px;
    margin-bottom: -85px;
}
.sorters{
    margin-top:50px;
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
.orders{
	height: 100%;
	overflow: auto;
    margin-top:50px; ;
}

</style>