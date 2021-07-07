<template>
    <div class="restaurant-list">
        <div  :key="user" v-for="user in users">
            <CustomerOrder @applyFilters="apply" :user="user"/>
        </div>
    </div>
</template>


<script>
    import CustomerOrder from './CustomerOrder'
export default {
    data(){
        return {
            search: ''
        }
    },
    methods:{
        apply(filters){
            console.log(filters);
        }
    },
    created(){
    },
    props:{
        users: Array,
    },
    name: 'CustomerOrders',
    components:{
        CustomerOrder
    },
}
</script>

<style scoped>
.restaurant-list{
    padding-top: 50px;
    padding-bottom: 20px;
    display: flex;
    flex: 1;
    flex-direction: column;
    overflow-y: auto;
}
</style>