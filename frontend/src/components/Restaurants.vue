<template>
    <div class="restaurant-list">
        <div  :key="restaurant.name" v-for="restaurant in restaurants">
            <Restaurant @restaurant-info="$emit('restaurant-info',restaurant.name)" :restaurant="restaurant"/>
        </div>
    </div>
</template>

<script>
    import Restaurant from './Restaurant'
    import EventBus from '@/event-bus.js'
export default {
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
    name: 'Restaurants',
    props:{
        restaurants: Array,
    },
    components:{
        Restaurant
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
