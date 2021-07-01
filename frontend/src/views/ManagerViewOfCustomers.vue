<template>
    <SearchBar class="search"/>
    	<CustomerOrders :users="users" class="orders" />
</template>

<script>
import CustomerOrders from "@/components/CustomerOrders.vue";
import SearchBar from "@/components/SearchBar.vue";
const allUsers = [
		{
			name: "Pera",
			surname: "Peric",
			type: "chinese",
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
			type: "chinese",
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
			type: "chinese",
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
			type: "chinese",
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
			type: "chinese",
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
	]
export default {
    data(){
        return{
            users:[]
        }
    },
    components:{
        CustomerOrders,
        SearchBar
    },
    created(){
        this.users=allUsers;
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
.search{
    margin-top:75px;
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
}

</style>