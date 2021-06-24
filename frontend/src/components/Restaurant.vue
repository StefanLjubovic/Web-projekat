<template>
    <div class="container">
        <div class="picture">
            <img class="rounded" v-bind:src="restaurant.logo">
        </div>
        <div class="details">
            <h3>{{restaurant.name}}</h3>
            <p>{{restaurant.location}}</p>
        </div>
        <div class="grade">
            <h3 class="star"><i class="bi bi-star"></i></h3>
            <h4>{{restaurant.grade}}</h4>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Restaurant',
    props:{
        restaurant: Object
    },
}
</script>

<style scoped>
    div.container{
        width:50vw ;
        height:10vh ;
        display: grid;
        grid-template-columns:100px 1fr 50px ;
        gap: 10px;
        background: #f4f4f4;
        margin-top:10px;
    }
    .details{
        margin-left: 20px;
        display: grid;
       grid-template-rows: 1fr 1fr;

    }
    .grade{
        display: grid;
       grid-template-rows: 1fr 1fr;
    }
    .rounded{
        width: 95%;
        height: auto;
    }
    .star{
        margin-top:10px;
    }
</style>