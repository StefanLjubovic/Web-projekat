
<template>
<div class="container">
    <div class="card">
        <div class="card-content">
            <div class="media-content">       
                <h4 class="text-light rate">Rate the restaurant:</h4>    
                <div class="star"><star-rating :show-rating="false" v-model:rating="rating"/></div>
                <div class="row"> 
                    <h4 class="title text-light">Leave comment:</h4>
                </div>
                <div class="form-group">
                    <textarea  type="text" class="form-control comment" id="nameInput" placeholder="Comment*" v-model="comment"/>
                    <input type="submit" class="btnRegister" @click="$emit('saveReview',rating,comment)"  value="Submit"/>
                </div>
                <button class="small-img-btn cancel" @click="$emit('close-modal')"><i class="fas fa-times"></i></button>

            </div>
        </div>
        </div>
    </div>
</template>

<script>
import StarRating from 'vue-star-rating'
export default {
    components:{
        StarRating
    },
    data(){
        return{
            rating:'',
            comment:''
        }
    },
    methods:{
    },
    emits:['saveReview','close-modal'],
    
}
</script>


<style scoped>
    .star-rating{
        display: inline-block;
        color:gold;
        font-size:2rem;
        position: relative;
    }
    .card{
         background-color: #5E639B;
        border-radius: 15px;
        width:40%;
        height: 550px;
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
    width: 50%;
    cursor: pointer;
    margin-right: 50px;
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
.star{
    margin-left: 100px;
}
</style>