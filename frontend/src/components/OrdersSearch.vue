<template>
        <transition name="modal">
        <div class="modal-mask">
          <div class="modal-wrapper">
            <div class="modal-item-container">
         <div class="container register">
                <div class="row">
                    <div class="col-md-9 register-right">
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <h3 class="register-heading">Advanced search:</h3>
                                <div class="row register-form">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="nameInput">Filter by:</label>
                                            <div class="form-check">
                                                <div class="form-group">
                                            <label for="type">Restaurant type:</label>
                                            <select name="cars" id="type"  class="form-control" v-model="filters.restaurantType">
                                                <option value="">Clear</option>
                                            <option value="Italian">Italian🍕</option>
                                            <option value="Chinese">Chinese🥡</option>
                                            <option value="Barbeque">Barbeque🍖</option>
                                            <option value="Similarly">Similarly🍗</option>
                                            </select>
                                        </div>
                                            </div>
                                           <div class="form-check">
                                                <div class="form-group">
                                            <label for="type">Order status:</label>
                                            <select name="cars" id="type"  class="form-control" v-model="filters.orderStatus">
                                            <option value="">Clear</option>
                                            <option value="Processing">Processing</option>
                                            <option value="InPreparation">InPreparation</option>
                                            <option value="WaitingForDelivery"> WaitingForDelivery</option>
                                            <option value="InTransport">InTransport</option>
                                            <option value="Delivered">Delivered</option>
                                            <option value="Canceled">Canceled</option>
                                            </select>
                                        </div>
                                           </div>
                                            <input type="submit" class="btnRegister" @click="$emit('applyFilters',filters,search)"  value="Apply"/>

                                        </div>
                                    </div>
                                     <div class="col-md-6">
                                            <div class="form-group">
                                            <label for="nameInput">Search by:</label>
                                            <div class="form-check">
                                                <div class="form-group">
                                             <div class="row">
                                            <div class="col">
                                                <input type="text" class="form-control" placeholder="Price from" v-model="search.priceFrom">
                                            </div>
                                            <div class="col">
                                                <input type="text" class="form-control" placeholder="to" v-model="search.priceTo">
                                            </div>                                       
                                            </div>
                                             <label for="nameInput">Date of order:</label>
                                            <div class="row">
                                                <div class="col margin">
                                            <label for="start" class="text-light">Start date:</label>
                                            </div>
                                            <div class="col">
                                                <input type="date" id="start" name="trip-start" class="widthDate">
                                             </div>
                                             </div>

                                                       <div class="row">
                                                <div class="col margin">
                                            <label for="start" class="text-light">End date:</label>
                                            </div>
                                            <div class="col">
                                                <input type="date" class="widthDate" id="start" name="trip-start"
                                                
                                             min="2018-01-01">
                                             </div>
                                             </div>
                                        </div>
                                            </div>
                                            
                                        </div>
                                    </div>
                                    <button class="small-img-btn cancel" @click="$emit('close-modal')"><i class="fas fa-times"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
             </div>  
          </div>
        </div>
      </transition>
</template>

<script>
import Datepicker from 'vuejs-datepicker';
export default {
    data(){
      return{
          filters:{
        restaurantType: '',
          orderStatus: ''
          },
          search:{
              priceFrom: '',
              priceTo: '',
              name: false
          }
      }  
    },
    emits:['close-modal'],
     components: {
    Datepicker
  }
}
</script>

<style scoped>
.modal-mask{
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  /* height: 100%; */
  background-color: rgba(0, 0, 0, 0.5);
  transition: opacity 0.3s ease;
  height: 100vh;
    overflow-y: auto;
    overscroll-behavior-y: contain;
}

.modal-item-container {
  /* width: 700px; */
  /* margin: 0px auto; */
  /* padding: 20px 30px; */
  /* background-color: #fff; */
  /* border-radius: 20px; */
  /* box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33); */
  transition: all 0.3s ease;
  /* display: flex; */
  /* flex-direction: column; */
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


.register{
    padding: 3%;
    position: relative;
    margin-left: 23%;

}
.form-group > label {
	color: white;
	margin-bottom: unset;
    font-size: 20px;
    
}
.cancel{
    position: absolute;
    top:10px
}
.orderCheck{
    margin-left: 160px;
}
label {
    display: block;
    font: 1rem 'Fira Sans', sans-serif;
}
.widthDate{
    width: 160px;
}
input,
label {
    margin: .4rem 0;
}

.register-left{
    text-align: center;
    color: #fff;
    margin-top: 4%;
}
.logo-div{
    position: absolute;
    right: 100px;
    top:20px;
    width: 100px;
    height: 100px;
}
.register-left input{
    border: none;
    border-radius: 1.5rem;
    padding: 2%;
    width: 60%;
    background: #f8f9fa;
    font-weight: bold;
    color: #383d41;
    margin-top: 30%;
    margin-bottom: 3%;
    cursor: pointer;
}
.width{
    width: 200px;
}
.sort{
    margin-left: 20px;
}

.register-right{
    background-color: #5E639B;
    position: relative;
    border-top-left-radius: 10% 50%;
    border-bottom-left-radius: 10% 50%;
}
.register-left img{
    margin-top: 15%;
    margin-bottom: 5%;
    width: 25%;
    -webkit-animation: mover 2s infinite  alternate;
    animation: mover 1s infinite  alternate;
}
@-webkit-keyframes mover {
    0% { transform: translateY(0); }
    100% { transform: translateY(-20px); }
}
@keyframes mover {
    0% { transform: translateY(0); }
    100% { transform: translateY(-20px); }
}
.register-left p{
    font-weight: lighter;
    padding: 12%;
    margin-top: -9%;
}
.margin{
    margin-top: 5px;
    font-size: 20px;
}
.img-btn{
    border-radius: 8%;
    position: absolute;
    border: #FFC312;
    background: #FFC312;
    width: 100px;
    height: 100px;
    right: 0;
}
.icon{
    position: absolute;
     right: 15px;
    bottom: 16px;
    z-index: 2;
    pointer-events:none;
    cursor: pointer;
}
.small-img-btn{
    position: absolute;
    border-radius: 50%;
    width: 30px;
    height: 30px;
    right: 10px;
    bottom: 10px;
    z-index: 2;
    cursor: pointer;
}

.register .register-form{
    padding: 10%;
    margin-top: 10%;
}
.btnRegister{
    float: right;
    margin-top: 10%;
    border: none;
    border-radius: 1.5rem;
    padding: 2%;
    background-color: #FFC312;
    color: black;
    font-weight: 600;
    width: 50%;
    cursor: pointer;
}
.register .nav-tabs{
    margin-top: 3%;
    border: none;
    border-radius: 1.5rem;
    width: 28%;
    float: right;
}
.register .nav-tabs .nav-link{
    padding: 2%;
    height: 34px;
    font-weight: 600;
    color: #fff;
    border-top-right-radius: 1.5rem;
    border-bottom-right-radius: 1.5rem;
}
.register .nav-tabs .nav-link:hover{
    border: none;
}
.register .nav-tabs .nav-link.active{
    width: 100px;
    border-top-left-radius: 1.5rem;
    border-bottom-left-radius: 1.5rem;
}
.register-heading{
    text-align: center;
    margin-top: 8%;
    margin-bottom: -15%;
    color: white;
}
.background{
    background-color: #D7E2F8;
    width: 100%;
    height: 100%;
}
</style>