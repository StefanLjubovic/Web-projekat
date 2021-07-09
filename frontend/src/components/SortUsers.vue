<template>
    <div class="container">
        <div class="row">
            <div class="fraction ">
            <label for="" class="label href">First name</label>
            <a href="#" class="href" @click="$emit('sortName',1)"><i class="fas fa-chevron-up"></i></a>
            <a href="#" class="href down" @click="$emit('sortName',-1)"><i class="fas fa-chevron-down"></i></a>
            </div>
             <div class="fraction ">
            <label for="" class="label href">Last name</label>
            <a href="#" class="href" @click="$emit('sortSurname',1)"><i class="fas fa-chevron-up"></i></a>
            <a href="#" class="href down" @click="$emit('sortSurname',-1)"><i class="fas fa-chevron-down"></i></a>
            </div>
             <div class="fraction ">
            <label for="" class="label href">Username</label>
            <a href="#" class="href"  @click="$emit('sortUsername',1)"><i class="fas fa-chevron-up"></i></a>
            <a href="#" class="href down" @click="$emit('sortUsername',-1)"><i class="fas fa-chevron-down"></i></a>
            </div>
             <div class="fraction ">
            <label for="" class="label href">Points colected</label>
            <a href="#" class="href"  @click="$emit('sortPoints',1)"><i class="fas fa-chevron-up"></i></a>
            <a href="#" class="href down" @click="$emit('sortPoints',-1)"><i class="fas fa-chevron-down"></i></a>
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
	.container {
	position: absolute;
	width: 100%;
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
	width: 22%;
	border-radius: 10px;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
    align-items: center;
    justify-content: center;
    text-align: center;
    margin-right: 30px;
}
.fraction > label{
    color: #1F1F43;
    font-weight: 500;
}

.down{
    color: #FC4C59;
}
.row{
    width: 100%;
}
</style>