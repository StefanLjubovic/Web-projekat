<template>
    <div :key="restaurant.name" v-for="restaurant in restaurants">
        <Restaurant :restaurant="restaurant"/>
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

<style>

</style>