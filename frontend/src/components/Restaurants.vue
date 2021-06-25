<template>
    <div class="restaurant-list">
        <div  :key="restaurant.name" v-for="restaurant in restaurants">
            <Restaurant :restaurant="restaurant"/>
        </div>
    </div>
</template>

<script>
    import Restaurant from './Restaurant'
export default {
    name: 'Restaurants',
    props:{
        restaurants: Array,
    },
    components:{
        Restaurant
    }
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
