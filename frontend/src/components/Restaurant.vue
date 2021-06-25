<template>
    <div class="container">
        <div class="picture" v-if="restaurant.logo" >
            <img class="rounded-image" :src="getImgUrl(restaurant.logo)" v-bind:alt="restaurant.logo">
        </div>
        <div class="restaurant-info">
            <h3>{{restaurant.name}}</h3>
            <label for="desctiption">{{restaurant.description}}</label>
        </div>
        <div class="restaurant-details">
            <div class="restaurant-location">
                <p>📍:  {{restaurant.location}}</p>
            </div>
            <div class="grade">
                <i class="fas fa-star" style="color: #FAE480"></i>
                <label>{{restaurant.grade}}</label>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Restaurant',
    props:{
        restaurant: {
            type: Object,
            default: {
                logo: ''
            }
        }
    },
    methods:{
        getImgUrl(pic) {
            return require('../assets/'+pic)
        }
    }
}
</script>

<style scoped>
    div.container{
        margin-top:10px;
        color: #42405F;
        border-radius: 20px;
        display: flex;
        flex-direction: row;
        background-color: white;
        padding: 17px;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
        min-width: 800px;
        width: 80%;
    }
    .picture{
        width: 200px;
    }
    .restaurant-info{
        flex: 1;
        padding: 0px 10px;
        height: 100%;
    }
    .restaurant-info > label{
        color: #8F8FA1;
        font-weight: 500;
        font-size: 14px;
    }

    .restaurant-details{
        display: flex;
        flex-direction: column;
        /* background-color: red; */
        /* justify-content: flex-end; */
        text-align: right;
    }
    .restaurant-location{
        flex: 1;
        color: #8F8FA1;
        font-weight: 600;
    }
    .grade{
         display: flex;
         flex-direction: row;
         align-items: center;
         justify-content: flex-end;
    }
    .grade > label {
        margin-bottom: unset;
        margin-left: 5px;
        color: #8F8FA1;
        font-weight: 600;
    }
    .rounded-image{
        width: 100%;
        height: auto;
        border-radius: 10px;
    }
</style>