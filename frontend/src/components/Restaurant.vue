<template>
    <div class="container" @click="openRestaurant()">
        <div class="picture" v-if="restaurant.logo != ''" >
            <img class="rounded-image" :src="getImage(restaurant.logo)" v-bind:alt="restaurant.logo">
        </div>
        <div class="restaurant-info">
            <h3>{{restaurant.name}}</h3>
            <label for="desctiption">{{restaurant.description}}</label>
            <p class="restaurant-type">{{getRestaurantType(restaurant.type)}}</p>
        </div>
        <div class="restaurant-details">
            <div class="restaurant-location">
                <p>📍:  {{restaurant.location.address}}</p>
            </div>
                        <div class="restaurant-status">
                {{getRestaurantStatus()}}
            </div>
            <div class="grade">
                <i class="fas fa-star" style="color: #FAE480"></i>
                <label>{{restaurant.grade?.toFixed(2)}}</label>
            </div>
        </div>
    </div>
</template>

<script>
import server from '../server';
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
    emits:['restaurant-info'],
    methods:{
        async getImgUrl(pic) {
            console.log(pic);
            return pic ? require(pic) : ''
        },
        openRestaurant() {
            this.$router.push({ name: 'Restaurant',params: { id: this.restaurant.id } });
        },
            getRestaurantStatus(){
            return this.restaurant.status ? `✋🏼 Closed ✋🏼` : "👍🏼 Open"
        },
         getRestaurantType(type){
            let retValue = type;
            switch (type) {
                case 'Barbeque':
                    retValue = `🍖 ${type}`;
                    break;
                case 'Chinese':
                    retValue = `🥡 ${type}`
                    break;
                case 'italian':
                    retValue = `🍕 ${type}`
                    break;
                case 'Similarly':
                    retValue = `🍖 ${type}`
                    break;
                default:
                    return type;
            }
            return retValue;
        },
        getImage(image) {
			return server.getImage(image);
		}
    },
    mounted(){
        // const aa = async () => {
        //     const image = await server.getImage(this.$props.restaurant.logo);
        //     this.restaurant.logo = image
        // };
        // aa();
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
        cursor: pointer;
    }
    .container:hover{
        box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.25);
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

    .restaurant-status{
        flex: 1;
        font-weight: 500;
        color: #8F8FA1;
        font-weight: 600; 
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