<template>
    <div class="restaurant-list">
        <div  :key="order" v-for="order in orders">
            <CustomerOrder @openDialog="$emit('openDialog',order)" @applyFilters="apply" :filters="filters" :order="order"/>
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
    emits:['openDialog'],
    created(){
    },
    props:{
        orders: Array,
        filters: Array
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