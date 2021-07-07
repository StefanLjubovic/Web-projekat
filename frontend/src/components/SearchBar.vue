<template>
  <div class="search-container">
	  <i class="fas fa-search" style="color: white; opacity: 0.5; margin-right: 10px"></i>
		<input
		type="search"
		class="search-input"
		placeholder="Search"
		aria-label="Search"
		v-model="userName"
		v-on:input="searchHandler"
		aria-describedby="search-addon"
		/>
		<button type="button" class="btn" v-if="advanced" @click="advancedSearch">Advanced search</button>
		<button type="button" class="btn" v-if="advancedOrders" @click="searchOrders">Advanced search</button>
  </div>
</template>

<script>
export default {
  data: () => {
	return {
	  userName: "",
	  searchTimeout: null,
	  advanced: false,
	};
  },
  emits:['advanced-search','search'],
  props:['advancedFilter','advancedOrders'],
  methods: {
	searchHandler() {
	  if (!this.searchTimeout) {
		clearTimeout(this.searchTimeout);
		this.searchTimeout = null;
	  }
	  this.searchTimeout = setTimeout(() => {
		this.$emit("search", this.userName);
	  }, 700);
	},
	advancedSearch(){
		this.$router.push({ path: '/advanced-search' });
	}
  },
  mounted(){
        if(this.advancedFilter !=null){
			  this.advanced=this.advancedFilter;
		  }
    },
};
</script>


<style scoped>
	.search-container{
		position: absolute;
		width: 80%;
		left: 10%;
		right: 10%;
		top: 10px;
		display: flex;
		flex-direction: row;
		background-color: #767aaa;
		border-radius: 100px;
		padding: 0px 11px;
		height: 39px;
		align-items: center;
		box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
	}
	.search-input{
		background-color: transparent;
		border: unset;
		flex: 1;
		color: white;
	}
	.search-input:focus{
		outline: unset
	}
	.btn{
        width: 150px;
        background-color: #FDDF6D;
        color: #42405F;
        font-weight: 500;
        border: unset;
		border-radius: 100px;
    }

</style>