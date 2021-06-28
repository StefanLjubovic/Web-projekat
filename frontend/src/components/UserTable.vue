<template>
<div class="background">
<div class="container">
    <table class="table table-color">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Surname</th>
      <th scope="col">Email</th>
      <th scope="col">Username</th>
      <th scope="col">Type</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="(user,i) in users" :key="i">
      <td>{{i+1}}</td>
      <td>{{user.name}}</td>
      <td>{{user.surname}}</td>
      <td>{{user.email}}</td>
       <td>{{user.username}}</td>
       <td>{{user.type}}</td>
    </tr>
  </tbody>
</table>
</div>
</div>
</template>


<script>
export default {
    data(){
      return{
        users:[
          {
            name:'pera',
            surname:'peric',
            email:'pera@gmail.com',
            username:'aaa',
            type:'Deliverer',
          },
          {
            name:'pera',
            surname:'peric',
            email:'pera@gmail.com',
            username:'aaa',
            type:'Deliverer',
          },
          {
            name:'pera',
            surname:'peric',
            email:'pera@gmail.com',
            username:'aaa',
            type:'Deliverer',
          }
        ]
      }
    },
}
</script>

<style scoped>  
.table-color{
  background: white;
  border-radius: 15px;
}
</style>