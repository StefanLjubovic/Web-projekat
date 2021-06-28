<template>
<div class="bcg-color">
    <SearchBar class="search"/>
    <UserTable class="tables"/>
</div>
</template>

<script>
import SearchBar from "@/components/SearchBar.vue";
import UserTable from "@/components/UserTable.vue";
export default {
    components:{
        UserTable,
        SearchBar
    }
}
</script>

<style scoped>
    .search{
        margin-top: 80px;
    }
    .bcg-color{
         background-color: #D7E2F8;
         width: 100%;
         height: 100%;
    }
    .tables{
        margin-top:80px;
    }
</style>