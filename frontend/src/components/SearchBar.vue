<template>
<div class="input-group rounded">
  <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search"
    aria-describedby="search-addon" v-model="search" @keypress="emitSearchValue"/>
  <span class="input-group-text border-0" id="search-addon">
    <i class="fas fa-search"></i>
  </span>
</div>
</template>

<script>
import EventBus from '@/event-bus.js'
export default {
  data(){
    return{
      search:''
    }
  },
  emits:['filter-changed'],
  methods:{
    emitSearchValue(){
     // EventBus.$emit('filter-changed',search)
    }
  }
}
</script>

<style scoped>
    div{
        width:400px;
        
    }
</style>