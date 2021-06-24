<template>
  <div class="home">
    <Header/>
    <div class="parent">
    <SearchBar class="search"/>
    <Restaurants :restaurants="restaurants" class="restaurants"/>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from '@/components/Header.vue'
import Restaurants from '@/components/Restaurants.vue'
import SearchBar from '@/components/SearchBar.vue'
export default {
  watch:{
    '$route'(to,from){
    }
  },
  data(){
    return {
      restaurants: []
    }
  },
      created(){
        this.restaurants=[
        {
            name: "Front",
            type: "chinese",
            status: true,
            logo: '../assets/front.jfif',
            location: "Sutjeska 3",
            grade: '4.3'
        },
        {
            name: "Petrus",
            type: "barbecue",
            status: false,
            logo: '../assets/petrus.jpg',
            location: "Bulevar 4",
            grade:'4.7'
        }
        ]
    },
  
  name: 'Home',
  components: {
    Header,
    Restaurants,
    SearchBar
  }
}
</script>

<style scoped>
  .parent{
    margin-top: 50px;
    position:relative;
  }
  .search{
    position:absolute;
    top: -25%;
    left: 54%;
  }
  
</style>
