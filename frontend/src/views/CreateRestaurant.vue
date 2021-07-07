<template>
<div class="background">
   <div class="container register">
                <div class="row">
                    <div class="col-md-9 register-right">
                        <div class="logo-div" v-if="logo == ''">
                            <button class="img-btn"><i class="far fa-images fa-3x"></i></button>
                            <button class="small-img-btn" @click="onPickFile"><i class="fas fa-camera"></i></button>
                            <input type="file" @change="uploadPhoto" accept="image/*" style="display:none" ref="fileInput"/>
                        </div>
                        <div class="logo-div" v-if="logo != ''">
                            <img class="rounded-image img-btn" :src="logo">
                            <button class="small-img-btn" @click="onPickFile"><i class="fas fa-camera"></i></button>
                            <input type="file" @change="uploadPhoto" accept="image/*" style="display:none" ref="fileInput"/>
                        </div>
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <h3 class="register-heading">Register restaurant</h3>
                                <div class="row register-form">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="nameInput">Name:</label>
                                            <input type="text" class="form-control" id="nameInput" placeholder="Name*" v-model="name"/>
                                            <div class="alert alert-danger" v-if="nameError" role="alert">
                                                 Field must not be empty!
                                             </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="type">Restaurant type:</label>
                                            <select name="cars" id="type"  class="form-control" v-model="type">
                                            <option value="volvo">Italian🍕</option>
                                            <option value="saab">Chinese🥡</option>
                                            <option value="mercedes">Barbeque🍖</option>
                                            <option value="audi">Similarly🍗</option>
                                            </select>
                                        </div>
                                    </div>
                                    
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="location">Location:</label>
                                            <input type="email" class="form-control" id="location" placeholder="Location *" v-model="location" />
                                        </div>
                                         <div class="form-group">
                                            <label for="manager">Manager:</label>
                                            <div v-if="managers.length">
                                            <select name="manager" id="manager" class="form-control" v-model="manager">
                                            <option v-bind:key="manager.firstname" v-for="manager in managers">
                                                {{manager.firstName}} {{manager.lastName}}
                                            </option>
                                            </select>
                                            </div>
                                            <div class="form-row" v-else>
                                              <div class="col">
                                                <label class="text-light margin">None available</label>
                                              </div>
                                              <div class="col">
                                                <button type="button" class="btn btn-warning" @click="registerManager">Register one</button>
                                              </div>
                                            </div>
                                        </div>
                                        <input type="submit" class="btnRegister" @click="saveRestaurant"  value="Register"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <LoginModal :form-type="formType" v-if="showLoginModal" @close="closeLogin"/>
</div>
</template>

<script>
import LoginModal from "@/components/Login/LoginModal.vue"
import Server from '@/server'
export default {
    data(){
        return{
            logo: '',
            nameError: false,
            name: '',
            manager: {},
            type: 'Italian',
            location: '',
            managers:[
            ],
            showLoginModal: false,
            formType: 'register'
        }
    },
    methods:{
        getImgUrl(pic) {
            return require('../assets/'+pic)
        },
        uploadPhoto(event){
            const files=event.target.files
            const fileReader=new FileReader()
            fileReader.addEventListener('load',()=>{
                this.logo=fileReader.result
            })
            console.log(files[0])
            fileReader.readAsDataURL(files[0])
        },
        onPickFile(){
            this.$refs.fileInput.click()
        },
        saveRestaurant(event){
            event.preventDefault()
            this.nameError=false
            if(this.name=='')this.nameError=true
            if(this.nameError) return
        },
        registerManager(){
            this.showLoginModal = true;
          document.getElementById('appContainer').style.overflow = 'hidden';
          document.getElementById('appContainer').style.height = '100vh';
        },
        closeLogin(){
          this.showLoginModal = false;
          document.getElementById('appContainer').style.overflow = 'unset';
          document.getElementById('appContainer').style.height = 'unset';
        }
    },
    async created() {
        Server.getAllManagers().then(resp=>{
            console.log("Resp", resp.data);
            if(resp.success){
				this.managers = resp.data;
			}
        })
	},
    components:{
        LoginModal
    }
}
</script>

<style scoped>
.register{
    padding: 3%;

}
.form-group > label {
	color: white;
	margin-bottom: unset;
    font-size: 20px;
    
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
    border-radius: 50%;
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