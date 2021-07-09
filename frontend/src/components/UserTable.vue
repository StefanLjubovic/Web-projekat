<template>
<div class="background">
<div class="container">
    <table class="table table-color">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Surname</th>
      <th scope="col">Username</th>
      <th scope="col">Type</th>
      <th scope="col">Collected points</th>
      <th scope="col">Status</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="(user,i) in users" :key="i">
      <td>{{i+1}}</td>
      <td>{{user.firstName}}</td>
      <td>{{user.lastName}}</td>
       <td>{{user.username}}</td>
       <td>{{user.role}}</td>
       <td>{{user.collectedPoints}}</td>
       <div v-if="user.status && user.role!='Admin'">
        <td><button type="button" @click="changeText(i)" data-toggle="modal" data-target="#exampleModal" class="btn btn-danger btn-circle btn-sm"></button></td>
       </div>
       <div  v-if="!user.status && user.role!='Admin'"> 
            <td><button type="button" @click="changeText(i)" data-toggle="modal" data-target="#exampleModal" class="btn btn-success btn-circle btn-sm"></button></td>
       </div>
    </tr>
  </tbody>
</table>
</div>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">{{block.title}}</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        {{block.content}}
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-primary"  data-dismiss="modal" @click="changeStatus()">Confirm</button>
      </div>
    </div>
  </div>
</div>
</div>
</template>


<script>
import Server from '@/server'
export default {
    data(){
      return{
        block:{
          title:'Ban user',
          content:'Are you sure you want to ban user?',
          id: '0'
        },
      }
    },
    props:{
      users:Array
    },
    methods:{
      changeStatus(){
          this.users[this.block.id].status=!this.users[this.block.id].status;
          Server.updateUser(this.users[this.block.id]).then(resp=>{
			      if(resp.success){
				      console.log('this.restaurants');
			      }
          });
      },
      changeText(i){
        this.block.id=i;
        if(!this.users[i].blocked){
          this.block.title='Ban user';
          this.block.content='Are you sure you want to ban user?';
        }else{
          this.block.title='Unban user';
          this.block.content='Are you sure you want to unban user?';
        }
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