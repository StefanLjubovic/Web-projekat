<template>
    <div class="container">
        <div class="row">
            <div class="fraction ">
            <label for="" class="label href">Name</label>
            <a href="#" class="href" @click="$emit('sortName',1)"><i class="fas fa-chevron-up"></i></a>
            <a href="#" class="href" @click="$emit('sortName',-1)"><i class="fas fa-chevron-down"></i></a>
            </div>
             <div class="fraction ">
            <label for="" class="label href">Price</label>
            <a href="#" class="href" @click="$emit('sortPrice',1)"><i class="fas fa-chevron-up"></i></a>
            <a href="#" class="href" @click="$emit('sortPrice',-1)"><i class="fas fa-chevron-down"></i></a>
            </div>
             <div class="fraction ">
            <label for="" class="label href">Date</label>
            <a href="#" class="href"  @click="$emit('sortDate',1)"><i class="fas fa-chevron-up"></i></a>
            <a href="#" class="href" @click="$emit('sortDate',1)"><i class="fas fa-chevron-down"></i></a>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
        }
    },
    methods:{

    }
}
</script>


<style scoped>
	.container{
		position: absolute;
		width: 80%;
		left: 10%;
		right: 10%;
		top: 10px;
		display: flex;
		flex-direction: row;
        background: white;
		border-radius: 10px;
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
    .href{
        margin-top:5px;
    }
    .label{
        margin-left: 15px;
        margin-right: 10px;
    }
    .fraction{
        background:  #e0e0d1;
        width: 380px;
        border-radius: 10px;
    }
</style>