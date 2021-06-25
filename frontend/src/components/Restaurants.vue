<template>
    <div :key="restaurant.name" v-for="restaurant in restaurants">
        <Restaurant :restaurant="restaurant"/>
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

<style>

</style>