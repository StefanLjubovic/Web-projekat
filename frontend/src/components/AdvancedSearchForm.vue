<template>
<div class="background">
    <SearchBar class="search"/>
   <div class="container register">
                <div class="row">
                    <div class="col-md-9 register-right">
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <h3 class="register-heading">Advanced search</h3>
                                <div class="row register-form">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="nameInput">Search by:</label>
                                            <select class="selectpicker form-control combobox" title="Choose one of the following..." v-model="search" @change="onChange">
                                                <option>Name</option>
                                                <option>Type</option>
                                                <option>Location</option>
                                                 <option>Grade</option>
                                            </select>
                                        </div>
                                    </div>
                                    
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="nameInput">Filter by:</label>
                                            <select class="selectpicker form-control combobox" @change="onChange" title="Choose one of the following..." v-model="filter">
                                                <option>Open restaurants</option>
                                                <option>Type</option>
                                            </select>
                                        </div>
                                        <input type="submit" class="btnRegister" value="Search"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-row btn-row">
                            <button class="btn-primary" ref="hrefClick" v-for="(filter, i) in filters" :key="filter">{{filter}}<a class="btn-secundary fas fa-times" @click="deleteFilter(i)" href="#" > </a></button> 
                        </div>
                    </div>
                    
                </div>

            </div>
</div>
</template>

<script>
import SearchBar from "@/components/SearchBar.vue";
export default {
    components:{
        SearchBar
    },
    data(){
        return{
            search:'',
            filters:[],

        }
    },
    methods:{
        onChange(event){
            for (let i = 0; i < this.filters.length; i++) {
                if(this.filters[i]==event.target.value){
                    return;
                }
            }
            this.filters.push(event.target.value);
        },
        deleteFilter(i){
            this.filters.splice(i, 1);
        }

    }
}
</script>

<style scoped>
.register{
    padding: 3%;
    position: absolute;
    left:20%;
    top:5%;
}
.btn-primary{
        width: 100px;
       background-color: #FDDF6D;
        color: #42405F;
        font-weight: 500;
        border: unset;
        margin-right: 15px;
        margin-left: 10px;
        height: 35px;
        border-radius: 5px;
        display: flex;
        flex-direction: row;
    }
.btn-row{
    position: absolute;
    left:40px;
    bottom: 30px;

}
.btn-secundary{
     background-color: #FDDF6D;
        color: #42405F;
        border:none;
        pointer-events: pointer;
        z-index: 2;
        cursor: pointer;
        margin-top:5px;
        margin-left: 5px;

}
.combobox{
    background-color: #FDDF6D;
        color: #42405F;
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
    position: relative;
}
</style>