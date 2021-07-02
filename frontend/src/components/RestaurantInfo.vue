<template>
<div class="container">
    <div class="restaurant-card">
        <div class="restaurant-image-container">
            <img class="rounded-image" :src="getImage(restaurant?.logo)" v-bind:alt="restaurant.logo">
        </div>
        <div class="restaurant-info-container">
            <div class="restaurant-info-header">
                <h3>{{restaurant.name}}</h3>
                <label for="desctiption">{{restaurant.description}}</label>
                <p class="restaurant-type">{{getRestaurantType(restaurant.type)}}</p>
            </div>
        </div>
         <div class="restaurant-details">
            <div class="restaurant-location">
                <p>📍:  {{restaurant?.location?.address}}</p>
            </div>
            <div class="restaurant-status">
                {{getRestaurantStatus()}}
            </div>
            <div class="grade"> 
                <i class="fas fa-star" style="color: #FAE480"></i>
                <label>{{restaurant.grade}}</label>
            </div>
        </div>
    </div>
</div>
</template>
<script>
export default {

    data(){
        return({
            restaurant: {}
        })
    },
    props:['restaurant'],
    created() {
        // this.restaurant = {
		// 	name: "Petrus",
		// 	description: "Modern restaurant in the city center",
		// 	type: "barbecue",
		// 	status: false,
		// 	logo: 'petrus.jpg',
		// 	location: "Bulevar 4",
		// 	grade: "4.7",
		// }
    },
    methods:{
        getImage(img) {
            return !!img ? require('../assets/'+img) : ""
        },
        getRestaurantType(type){
            let retValue = type;
            switch (type) {
                case 'barbecue':
                    retValue = `🍖 ${type}`;
                    break;
                case 'chinese':
                    retValue = `🥡 ${type}`
                    break;
                default:
                    return type;
            }
            return retValue;
        },
        getRestaurantStatus(){
            return this.restaurant.status ? `✋🏼 Closed ✋🏼` : "👍🏼 Open"
        }
    }
}
</script>

<style scoped>
    .container{
        position: relative;
        display: block;
        z-index: 10;
    }
    .restaurant-card{
        padding: 17px;
        background-color: white;
        width: 80%;
        border-radius: 20px;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
        margin: auto;
        margin-top: 10px;
        display: flex;
        /* align-items: center; */
    }
    .restaurant-info-container{
        flex: 1;
        /* background-color: green; */
        padding: 0px 10px;
    }
    .restaurant-image-container{
        max-width: 200px;
        border-radius: 12px;
        overflow: hidden;
    }
    .rounded-image{
        object-fit: contain;
        width: 100%;
        /* height: 100%; */
    }

    .restaurant-info-header > label{
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
    .restaurant-status{
        flex: 1;
        font-weight: 500;
        color: #8F8FA1;
        font-weight: 600; 
    }
    .restaurant-type{
        margin-bottom: unset;
        color: #8F8FA1;
        /* font-weight: 600; */
    }
</style>