<template>
    <div class="container" @click="$emit('openDialog',order)">
        <div class="picture">
            <i class="far fa-user fa-5x" rounded-image></i>
        </div>
        <div class="restaurant-info">
            <h3>Customer: {{order.user.firstName}} {{order.user.lastName}}</h3>
            <div v-if="order.deliverer !=null"><h5>Deliverer🚚:{{order.deliverer.firstName}} {{order.deliverer.lastName}}</h5></div> 
            <label for="desctiption">Ordered food:&nbsp;</label>
            <label for="desctiption" v-for="article in articles" :key="article">{{article.name}} x{{article.count}}&nbsp;</label><br>
        </div>
        <div class="restaurant-details">
            <div class="restaurant-location">
                <p>Order status📍:{{order.order.status}} </p>
                <p>Price💸:{{order.order.price}}</p>
                <p>Date of order📆:{{order.order.date}} <b></b></p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CustomerOrder',
     props:{
        order: Object,
        filters: Array
    },
    data(){
        return{
            price: 0,
            articles: []
        }
    },
    emits:['openDialog'],
    methods:{
    },
    created(){
        let ordersDict={}
            for(const item of this.order.order.items){
                if(!ordersDict[item.name])
                    ordersDict[item.name]={count: 0,...item};
                ordersDict[item.name].count++
            }
            this.articles=Object.values(ordersDict)
            return Object.values(ordersDict)
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