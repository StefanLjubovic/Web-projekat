<template>
<div class="container">

    <div class="navigator">
        
        <div class="navigator-clip" v-bind:class="{ selected: selectedState=='items' }" @click="selectState('items')">
            <span>🍔 Items</span>
        </div>
        <div class="navigator-clip" v-bind:class="{ selected: selectedState=='reviews' }" @click="selectState('reviews')">
            <span><i class="fas fa-star" style="color: #FAE480"></i> Reviews</span>
        </div>
        <div class="navigator-clip" v-bind:class="{ selected: selectedState=='informations' }" @click="selectState('informations')">
            <span>📍 Location</span>
        </div>
    </div>
</div>
</template>
<script>
export default {
    data(){
        return({
            selectedState: 'reviews'
        })
    },
    setup() { 
        
    },
    methods:{
        selectState(state){
            this.selectedState = state;
            this.$emit("change-view",state)
        }
    }
}
</script>

<style scoped>
    .container{
        position: relative;
        z-index: 1;
    }
    .navigator{
        display: flex;
        flex-direction: row;
        align-items: flex-start;
        width: 60%;
        margin: auto;
        z-index: 1;
    }
    .navigator-clip{
        flex: 1;
        background-color: white;
        padding: 10px;
        margin: 0 2px;
        border-bottom-left-radius: 20px;
        border-bottom-right-radius: 20px;
        cursor: pointer;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
    }
    .navigator-clip{
        text-align: center;
        color: #8F8FA1;
        font-weight: 500;
    }
    .navigator-clip:hover{
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);
        padding-top: 20px;
    }
    .selected{
        background-color: #FAE480;
        padding-top: 20px;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);
        color: #1F1F43;
    }
    .selected i {
        color: #1F1F43;
    }
</style>