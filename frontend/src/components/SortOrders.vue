<template>
	<div class="container">
		<div class="fraction ">
			<label for="" class="label href">Name</label>
			<a href="#" class="href" @click="$emit('sortName', 1)"><i class="fas fa-chevron-up"></i></a>
			<a href="#" class="href down" @click="$emit('sortName', -1)"><i class="fas fa-chevron-down"></i></a>
		</div>
		<div class="fraction ">
			<label for="" class="label href">Price</label>
			<a href="#" class="href" @click="$emit('sortPrice', 1)"><i class="fas fa-chevron-up"></i></a>
			<a href="#" class="href down" @click="$emit('sortPrice', -1)"><i class="fas fa-chevron-down"></i></a>
		</div>
		<div class="fraction ">
			<label for="" class="label href">Date</label>
			<a href="#" class="href" @click="$emit('sortDate', 1)"><i class="fas fa-chevron-up"></i></a>
			<a href="#" class="href down" @click="$emit('sortDate', -1)"><i class="fas fa-chevron-down"></i></a>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {};
	},
	methods: {},
};
</script>

<style scoped>
.container {
	position: absolute;
	width: 80%;
	left: 10%;
	right: 10%;
	top: 10px;
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: space-around;
}
.search-input {
	background-color: transparent;
	border: unset;
	flex: 1;
	color: white;
}
.search-input:focus {
	outline: unset;
}
.href {
	margin-top: 5px;
}
.label {
	margin-left: 15px;
	margin-right: 10px;
}
.fraction {
	background: #fddf6d;
	/* width: 380px; */
	width: 30%;
	border-radius: 10px;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
    align-items: center;
    justify-content: center;
    text-align: center;
}
.fraction > label{
    color: #1F1F43;
    font-weight: 500;
}

.down{
    color: #FC4C59;
}
</style>
