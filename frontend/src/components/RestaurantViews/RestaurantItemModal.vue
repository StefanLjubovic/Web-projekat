<template>
    <transition name="modal">
        <div class="modal-mask">
          <div class="modal-wrapper">
            <div class="modal-item-container">
                <div class="modal-item-image">
                    <img class="item-image" :src="getImage(item)" alt="" />
                    <div class="modal-close" @click="$emit('closeModal')">
                        <i class="fas fa-times"></i>
                    </div>
                </div>
                <div class="modal-item-body">
                    <h3>{{item.name}}</h3>
                    <p>{{item.description}}</p>
                    <div class="informations">
                        <div class="size">Portion size: <b>{{item.size}}g</b></div>
                        <div class="price">Price: <b>{{item.price}} RSD</b></div>
                    </div>
                    <!-- <textarea name="" id="" cols="30" rows="10"></textarea> -->
                </div>
                <div class="modal-item-footer" v-if="user?.role == 'Customer'">
                    <div class="amount-container">
                        <button class="amount-button" @click="decAmount" >
                            <i class="fas fa-minus"></i>
                        </button>
                        <label for="" class="amount">{{amount}}</label>
                        <button class="amount-button" @click="incAmount">
                            <i class="fas fa-plus"></i>
                        </button>
                    </div>
                    <button class="submit-order" @click="submitOrder">
                        Add to cart
                    </button>
                </div>
              
            </div>
          </div>
        </div>
      </transition>
</template>

<script>
import Swal from 'sweetalert2';
import server from '../../server';
import store from '../../store/index'
export default {
    data(){
        return{
            amount: 1
        }
    },
    props:['item', 'restaurant', 'user'],
    mounted(){
    },
    methods:{
        show () {
            this.$modal.show('my-first-modal');
        },
        hide () {
            this.$emit('closeModal')
        },
        getImage() {
			return server.getImage(this.item.image);
		},
        incAmount(){
            this.amount++;
        },
        decAmount(){
            this.amount--;
        },submitOrder(){
            Swal.fire({ 
                title: 'Items are added to your cart',
                icon: 'success',
                confirmButtonText: 'Okay',
                timer: 2000,
                timerProgressBar: true
            })
            const newOrderArray = new Array(this.amount).fill(this.item);
            store.commit("addItems", newOrderArray);
            store.commit("setRestaurantId", this.restaurant.id)
            this.hide();
        }
    },
    
}
</script>

<style scoped>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  transition: opacity 0.3s ease;
  padding-top: 20px;
}

.modal-item-container {
  width: 700px;
  margin: 0px auto;
  /* padding: 20px 30px; */
  background-color: #fff;
  border-radius: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  display: flex;
  flex-direction: column;
}

.modal-item-image{
    border-radius: 20px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
    overflow: hidden;
    position: relative;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-item-body {
  margin: 20px;
}
.modal-item-body > h3{
    color: #42405F;
	font-weight: 600;
}
.modal-item-body > p{
    color: #8f8fa1;
}

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}

.item-image{
    width: 100%;
}

.modal-close{
    position: absolute;
    top: 20px;
    right: 20px;
    border-radius: 100px;
    background-color: white;
    width: 30px;
    height: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
}

.modal-item-footer{
    border-top: 1px solid #8f8fa1;
    padding: 15px 20px;
    display: flex;
    flex-direction: row;
    align-items: center;
}

.amount-button{
    border: 1px solid #dfdfdf;
    height: 30px;
    width: 30px;
    border-radius: 100px;
    background-color: white;
    cursor: pointer;
    outline: unset;
}
.amount{
    margin: 0px 15px;
}
.amount-container{
    flex: 1;
}

.submit-order{
    background-color: #FDDF6D;
    border: unset;
    width: 300px;
    color: #42405F;
	font-weight: 600;
    border-radius: 100px;
    padding: 10px 0;
    cursor: pointer;
}
.price{
    color: #42405f;
	font-weight: 500;
}
.size{
    color: #42405f;
	font-weight: 500;
    flex: 1;
}
.informations{
    display: flex;

}
</style>