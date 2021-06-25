<template>
    <button class="btn btn-danger">aaa</button>
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
    }
    button.btn-danger{
        border-radius: 50%;
        display: none;
    }
</style>
