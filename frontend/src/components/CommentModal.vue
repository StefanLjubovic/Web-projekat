<template>
    <transition name="modal">
        <div class="modal-mask">
          <div class="modal-wrapper">
            <div class="modal-item-container">
                <Comment @saveReview="$emit('saveReview')" @close-modal="$emit('close-modal')"/>
    </div>  
          </div>
        </div>
      </transition>
</template>

<script>
import Comment from "@/components/Comment.vue";
export default {
    data(){
        return{
        }
    },
    components:{
        Comment
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
body {
    background-color: #eee
}

div.stars {
    width: 270px;
    display: inline-block
}

.mt-200 {
    margin-top: 200px
}

input.star {
    display: none
}

label.star {
    float: right;
    padding: 10px;
    font-size: 36px;
    color: #4A148C;
    transition: all .2s
}

input.star:checked~label.star:before {
    content: '\f005';
    color: #FD4;
    transition: all .25s
}

input.star-5:checked~label.star:before {
    color: #FE7;
    text-shadow: 0 0 20px #952
}

input.star-1:checked~label.star:before {
    color: #F62
}

label.star:hover {
    transform: rotate(-15deg) scale(1.3)
}

label.star:before {
    content: '\f006';
    font-family: FontAwesome
}
</style>