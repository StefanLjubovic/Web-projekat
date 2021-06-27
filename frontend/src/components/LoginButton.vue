<template>
    <button type="button" class="btn btn-primary" @click="$emit('login-user')">Log in</button>
</template>

<script>
export default {
    emits:['login-user']
}
</script>

<style scoped>
    button.btn-primary{
        width: 100px;
        background-color: #FDDF6D;
        color: #42405F;
        font-weight: 500;
        border: unset;
    }
    button.btn-danger{
        border-radius: 50%;
        display: none;
    }
</style>
