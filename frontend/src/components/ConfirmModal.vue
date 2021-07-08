<template>
   <transition name="modal">
        <div class="modal-mask">
          <div class="modal-wrapper">
            <div class="modal-item-container">
<div class="container">
    <div class="card">
        <div class="card-content">
            <div class="media-content">       
                <h2 class="text-light rate">{{title}}</h2>    
                <div class="row"> 
                </div>
                <div class="form-group">
                    <h5 class="text-light rate">{{description}}</h5>
                    <div class="row">
                    <input type="submit" class="btnRegister" @click="$emit('saveConfirm')"  value="Confirm"/>
                    <input type="submit" class="btnRegister" @click="$emit('cancelConfirm')"  value="Cancel"/>
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
export default {
  emits:['saveConfirm','cancelConfirm'],
  props:['title','description']
}
</script>

<style scoped>
.modal-mask {
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
.star-rating{
        display: inline-block;
        color:gold;
        font-size:2rem;
        position: relative;
    }
    .card{
         background-color: #5E639B;
        border-radius: 15px;
        width:30%;
        height: 250px;
        margin-top: -355px;
    }
    .star-rating .current{
        position: absolute;
        top:0;
        width: 80%;
        overflow: hidden;
        white-space: nowrap;
    }
    .container{
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        border-radius: 20px;
    }
    .comment{
        height: 300px;
        width: 80%;
        margin-left: 35px;
        margin-top: 15px;
    }
    .rate{
        margin-left: 10px;
        margin-top:10px;
    }
    .btnRegister{
    float: right;
    margin-top: 5%;
    border: none;
    border-radius: 1.5rem;
    padding: 2%;
    background-color: #FFC312;
    color: black;
    font-weight: 600;
    width: 30%;
    cursor: pointer;
    margin-right: 20px;
    margin-left: 10%;
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
    top:10px;
}
.title{
    margin-left: 25px;
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
}

</style>