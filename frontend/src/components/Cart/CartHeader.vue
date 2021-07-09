<template>
	<div class="cart-header-container">
        
        <h3>Customer information</h3>
        <div class="information-row">
            <label for="Name" class="information-label">Name: </label>
            <label for="" class="information-label">{{user.firstName}} {{user.lastName}}</label>
        </div>
        <div class="information-row">
            <label for="Name" class="information-label">Gender: </label>
            <label for="" class="information-label">{{user.gender}}</label>
        </div>
    </div>
</template>
<script>

export default {
    props:['user']
};
</script>
<style scoped>
    
</style>
