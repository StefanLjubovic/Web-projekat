<template>
    <div style="width: 70%; margin: auto">
        <h3>Reviews</h3>
    </div>
    <div class="reviews-list">
        <div  :key="review.username" v-for="review in reviews">
            <div class="review">
                <div class="review-message">
                    {{review.text}}
                </div>
                <div class="review-details">
                    <label class="food-type">{{review.food}}</label>
                    <label class="date">{{format_date(review.date)}}</label>
                </div>
                <div class="review-user">
                    <img class="user-image" :src="getImage(review)" alt="">
                    <label class="username" for="username">{{review.username}}</label>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import moment from 'moment'
export default {
    data(){
        return{
            reviews:[
                {
                    text:"Preporučio bih ga.",
                    date: 1624749081190,
                    username: 'Pera peric',
                    food: 'Riba',
                    image: 'https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png'
                },{
                    text:"Predivan obrok. Brza dostava. Sviđa mi se.",
                    date: 1624749081190,
                    username: 'Predrag Stojacic',
                    food: 'Burger',
                    image: 'https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png'
                },{
                    text:"Test",
                    date: 1624749081190,
                    username: 'Marko Markovic',
                    food: 'Burger',
                    image: 'https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png'
                },{
                    text:"Najavili 71min. Stiglo za max 40min. Lepo, toplo, svi prilozi ispravni, dostavljač izašao u susret. 10/10",
                    date: 1624749081190,
                    username: 'Bosko Buha',
                    food: 'Testenine',
                    image: 'https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png'
                }
            ]
        }
    },
    methods:{
        getImage(review){
            console.log(review.image);
            return review.image
        },format_date(value){
         if (value) {
           return moment(value).format('DD.MM.YYYY.')
          }
      },
    }
}
</script>

<style scoped>
    .reviews-list{
        display: flex;
        flex-wrap: wrap;
        padding: 10px 40px;
        position: relative;
        width: 100%;
        justify-content: center;
    }
    .review{
        width: 400px;
        padding: 17px;
        background-color: white;
        border-radius: 20px;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
        display: flex;
        flex-direction: column;
        height: calc(100% - 10px);
        margin: 5px;
    }
    .review-user{
        margin-top: 5px;
        border-top: 2px solid #8F8FA1;
        padding-top: 5px;
        display: flex;
        flex-direction: row;
        align-items: center;
    }
    .user-image{
        border-radius: 100%;
        width: 50px;
    }
    .username{
        padding-left: 10px;
        color: #8F8FA1;
        font-weight: 600;
    }

    .food-type{
        margin-top: 5px;
        margin-bottom: unset;
        color: #8F8FA1;
        font-size: 14px;
        flex: 1;
    }
    .review-message{
        flex: 1;
        color: #8F8FA1;
    }
    .review-details{
        display: flex;
    }

    .date{
        /* margin-top: 5px; */
        margin-bottom: unset;
        color: #8F8FA1;
        font-size: 14px;
        font-weight: 600;
    }
</style>