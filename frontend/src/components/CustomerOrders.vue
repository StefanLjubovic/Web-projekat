<template>
    <div class="restaurant-list">
        <div  :key="user" v-for="user in users">
            <CustomerOrder @openDialog="$emit('openDialog')" @applyFilters="apply" :filters="filters" :user="user"/>
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
        users: Array,
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