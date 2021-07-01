<template>
    <button type="button" class="btn btn-primary" @click="$emit('login-user')">Log in</button>
    <span>
    <button class="btn btn-warning btn-circle btn-circle-sm m-1" @click="$emit('show-options')" ><i class="fas fa-angle-down"></i></button>
    </span>
</template>

<script>
export default {
  emits:['login-user','show-options']
}
</script>

<style scoped>
    button.btn-primary{
        width: 100px;
        background-color: #FDDF6D;
        color: #42405F;
        font-weight: 500;
        border: unset;
        margin-right: 15px;
    }
    .btn-circle {
  width: 45px;
  height: 45px;
  line-height: 45px;
  text-align: center;
  padding: 0;
  border-radius: 50%;
}

.btn-circle i {
  position: relative;
  top: -1px;
}

.btn-circle-sm {
  width: 35px;
  height: 35px;
  line-height: 35px;
  font-size: 0.9rem;
}

.btn-circle-lg {
  width: 55px;
  height: 55px;
  line-height: 55px;
  font-size: 1.1rem;
}

.btn-circle-xl {
  width: 70px;
  height: 70px;
  line-height: 70px;
  font-size: 1.3rem;
}

</style>
