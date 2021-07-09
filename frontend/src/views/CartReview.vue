<template>
	<div class="cart-container">
        <h1 class="title">Checkout</h1>
        <div class="cart-card">
            <CartItemsList />
            <div class="footer">
                <div class="total-price">Total price: {{totalPrice}} RSD</div>
                <button class="order-button" @click="orderNow">Order now</button>
            </div>
        </div>
	</div>
</template>

<script>
// @ is an alias to /src
import CartItemsList from '@/components/Cart/CartItemsList.vue'
import server from '../server';
import Swal from 'sweetalert2';
export default {
	computed: {
		user() {
			return this.$store.getters.getUser;
		},
		cart() {
			return this.$store.getters.getCart;
		},
		userExists() {
			return !!this.user.firstName;
		},
		cartLength() {
			return this.cart?.items?.length || '0';
		},
        totalPrice(){
            return this?.cart?.items?.reduce((a, b) => a + b.price, 0) || "0";
        }
	},

    methods:{
        async orderNow(){
            const data = {
                ...this.cart,
                price: this.totalPrice,
                buyerId: this.user.id
            }
            console.log(data);
            const resp = await server.createOrder(data);
            if(resp.success){
                Swal.fire({ 
                    title: 'Your order is on the way!',
                    icon: 'success',
                    confirmButtonText: 'Okay',
                    timer: 2000,
                    timerProgressBar: true
                })
                localStorage.removeItem("cart");
                this.$store.commit("clearCart");
                this.$router.push("/");
            }
        }
    },
    components:{
        CartItemsList
    }
};
</script>

<style scoped>

.cart-container {
	position: relative;
	display: flex;
	flex-direction: column;
    width: 100vw;
	min-height: calc(100vh - 70px);
	background-color: #d7e2f8;
    align-items: center;
    overflow-y: auto;
}
.cart-card{
    width: 888px;
    padding: 20px;
    background-color: white;
    margin-top: 30px;
    border-radius: 20px;
    box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
}
.title{
    margin-top: 30px;
    text-align: left;
    width: 888px;
}
.order-button{
    flex: 1;
    border: unset;
    background-color: #5E639B;
    color: white;
    padding: 15px 40px;
    border-radius: 15px;
    font-weight: bold;
    cursor: pointer;
    outline: unset;
    float: right;
}
.footer{
    margin-top: 10px;
    display: flex;
    flex-direction: row;
    align-items: center;
}
.total-price{
    flex: 1;
    color: #5e639b;
	font-weight: 600;
    padding-right: 20px;
    text-align: right;
}
</style>
