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
  </div>
</template>

<script>
// let searchTimeout = null;
export default {
  data: () => {
	return {
	  userName: "",
	  searchTimeout: null,
	};
  },
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
		height: 34px;
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

</style>