<template>
    <transition name="modal">
        <div class="modal-mask">
          <div class="modal-wrapper">
            <div class="modal-item-container">
                <LoginElement v-if="state == 'login'"    @changeState="changeState" @close="close"/>
                <RegisterForm v-if="state == 'register'" @changeState="changeState" @close="close"/>
            </div>
          </div>
        </div>
      </transition>
</template>

<script>
import LoginElement from "@/components/LoginElement.vue"
import RegisterForm from "@/components/RegisterForm.vue"
export default {
    data(){
        return{
            state: 'login'
        }
    },
    methods: {
        
    },
    props:[],
    mounted(){
    },
    methods:{
        changeState(state){
            this.state = state;
        },
        close(){
            this.$emit('close')
        }
    },
    components:{
        LoginElement,
        RegisterForm
    }
    
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