<template>
    <div class="restaurant-list">
        <div  :key="user" v-for="user in users">
            <CustomerOrder :user="user"/>
        </div>
    </div>
</template>


<script>
    import CustomerOrder from './CustomerOrder'
export default {
    name: 'CustomerOrders',
    data(){
        return {
            search: ''
        }
    },
    created(){
       // EventBus.$on('filter-changed',(search)=>{
        //    console.log(search);
       // });
    },
    props:{
        users: Array,
    },
    name: 'CustomerOrders',
    props:{
        user: Array,
    },
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