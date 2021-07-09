<template>
	<div class="my-cart-container">
        <div class="empty-cart">
            <div>
                <div class="empty-cart-icon">
                    <i class="fas fa-shopping-cart fa-3x"></i>
                </div>
                <p>Your cart is empty</p>
                <label for="">Find your favourite meal</label>
            </div>
        </div>
    </div>
</template>
<script>
export default {
	watch: {
		$route(to, from) {},
	},
	computed: {
		cart() {
			return this.$store.getters.getCart;
		},
	},
	methods: {
		
	},
	created() {
		// this.user = User;
	},
	emits: [],
};
</script>
<style scoped>
    .my-cart-container{
        width: 250px;
        min-height: 150px;
        background-color: #FDDF6D;
        border-radius: 20px;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
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
</style>
