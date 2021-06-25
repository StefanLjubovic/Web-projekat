<template>
    <div class="container rounded border border-white" bg-white>
        <div class="picture">
            <img class="rounded" v-bind:src="restaurant.logo">
        </div>
        <div class="details">
            <h3>{{restaurant.name}}</h3>
            <p>{{restaurant.location}}</p>
        </div>
        <div class="grade">
            <h3 class="star"><i class="bi bi-star-fill text-warning"></i></h3>
            <h4 class="text-warning">{{restaurant.grade}}</h4>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Restaurant',
    props:{
        restaurant: Object
    },
    emits:['login-user']
}
</script>

<style scoped>
    div.container{
        width:50vw ;
        height:10vh ;
        display: grid;
        grid-template-columns:100px 1fr 50px ;
        gap: 10px;
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