<template>
	<div class="my-cart-container">
        <div class="items-list-container" v-if="!isEmpty()">
            <p class="title">This is your cart:</p>
            <div class="items-list">
                <div class="item" v-for="item in group(cart.items)" :key="item">
                    <div class="item-image-container">
                        <img class="item-image" :src="getImage(item.image)" alt="" />
                    </div>
                    <div class="item-body">
                        <p class="item-name">{{item.name}}</p>
                        <p for="price" class="price">{{item.price.toFixed(2)}} RSD x {{item.count}} </p>
                        <!-- <p for="" class="total-item-price"></p> -->
                    </div>
                </div>
                <p class="total-item-price" v-if="!!user?.reward">Discount: {{user?.reward?.discount * 100}}%</p>
                <p class="total-item-price">Total price: {{totalPrice().toFixed(2)}} RSD</p>
            </div>
            <div class="footer">
                <button class="checkout" @click="checkout">
                    Checkout
                </button>
            </div>
        </div>
        <div class="empty-cart" v-if="isEmpty()">
            <div>
                <div class="empty-cart-icon" >
                    <i class="fas fa-shopping-cart fa-3x"></i>
                </div>
                <p>Your cart is empty</p>
                <label for="">Find your favourite meal</label>
            </div>
        </div>
    </div>
</template>
<script>
import server from '../../server';
export default {
	watch: {
		$route(to, from) {},
	},
	computed: {
		cart() {
			return this.$store.getters.getCart;
		},
        user(){
            return this.$store.getters.getUser;
        }
	},
	methods: {
        isEmpty(){
            return !this.$store.getters.getCart.items?.length;
        },
        group(){
            let ordersDict = {}
            for(const item of this.cart.items){
                if(!ordersDict[item.name])
                    ordersDict[item.name] = {count: 0, ...item};
                ordersDict[item.name].count++
            }
            return Object.values(ordersDict)
        },
        getImage(image) {
			return server.getImage(image);
		},
        checkout(){
            console.log('Checkout');
            this.$router.push({name: 'Checkout'});
        },
        totalPrice(){
            const discount = this?.user?.reward?.discount || 0;
            return this.cart.items.reduce((a, b) => a + b.price, 0) * (1 - discount);
        }
	},
	created() {
		// this.user = User;
	},
	emits: [],
};
</script>
<style scoped>
    .my-cart-container{
        width: 350px;
        min-height: 150px;
        background-color: #FDDF6D;
        border-radius: 20px;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);
        max-height: 400px;
        display: flex;
        flex-direction: column;
    }
    .empty-cart{
        flex: 1;
        text-align: center;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        color: #42405F;
    }
    .empty-cart p {
        color: #42405F;
        font-weight: bold;
        font-size: 19px;
        margin-bottom: 10px;
    }
    .empty-cart label{
        color: #8F8FA1;
        font-weight: 600;
        /* font-size: 19px; */
    }
    .items-list-container{
        padding: 10px 15px;
    }
    .item{
        display: flex;
        flex-direction: row;
        align-items: center;
        margin: 10px 0;
    }
    .title{
        color: #5E639B;
        font-weight: 600;
    }
    .item-image-container {
        overflow: hidden;
        border-radius: 10px;
    }
    .item-image {
        width: 130px;
        max-height: 100px;
        object-fit: contain;
    }
    .item-name{
        font-weight: 600;
        margin-bottom: unset;
        color: #5E639B;
    }
    .item-body{
        margin-left: 5px;
        flex: 1;
    }
    .price{
        color: #8F8FA1;
        font-size: 14px;
        font-weight: 500;
        /* margin-bottom: unset; */
    }
    .total-item-price{
        margin-bottom: unset;
        color: #5E639B;
        /* font-size: 14px; */
        font-weight: 500;
        text-align: right;
        width: 100%;
    }
    .footer{
        width: 100%;
        display: flex;
        margin-top: 10px;
    }
    .checkout{
        flex: 1;
        border: unset;
        background-color: #5E639B;
        color: white;
        padding: 15px;
        border-radius: 15px;
        margin-bottom: 5px;
        font-weight: bold;
        cursor: pointer;
        outline: unset;
    }
    .checkout:hover{
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
    }
</style>
