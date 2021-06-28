<template>
   <div class="container rounded bg-white mt-5 mb-5 color">
    <div class="row">
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right text-light">Profile Settings</h4>
                </div>
                <div class="row mt-2">
                    <div class="col-md-6"><label class="labels">Name</label><input type="text" class="form-control" placeholder="first name" v-model="user.name"></div>
                    <div class="col-md-6"><label class="labels">Surname</label><input type="text" class="form-control" v-model="user.lastname" placeholder="surname"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-12"><label class="labels">Email Address</label><input type="text" class="form-control" placeholder="email address"  v-model="user.email"></div>
                    <div class="col-md-12"><label class="labels">Username</label><input type="text" class="form-control" placeholder="username" v-model="user.username"></div>
                     <div class="col-md-12"><label class="labels">Date of birth</label><input type="text" class="form-control" placeholder="dd.mm.yyyy." v-model="user.date"></div>
                    <div class="col-md-12"><label class="labels">Gender</label> <select class="form-control" v-model="user.gender"><option value="Male">Male</option><option value="Female">Female</option> </select>  </div>
                </div>
                <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="button">Save Profile</button></div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center experience"><span class="text-right text-light">Change password</span><span class="border px-3 p-1 add-experience btn" @click="changePassword">&nbsp;Change password</span></div><br>
                <div class="col-md-12"><label class="labels">Current password</label><input type="password" class="form-control" :v-bind="user.password"></div> <br>
                <div class="col-md-12"><label class="labels">New password</label><input type="password" class="form-control" :v-model="password"></div>
            </div>
        </div>
    </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            user:{
                name: 'Pera',
                lastname:'Peric',
                username: 'pera',
                email:'pera@gmail.com',
                gender:'Male',
                password:'123',
                date:'11.05.1997.'

            },
            password:''
        }
    },
    methods:{
        changePassword(){
        }
    }
}
</script>

<style scoped>  
.form-control:focus {
    box-shadow: none;
    border-color: #BA68C8
}

.profile-button {
    background-color: #FFC312;
    color: black;
    box-shadow: none;
    border: none
}
.profile-button:hover {
    background-color: #FFC312;
    color: black;
}
.btn{
    background-color: #FFC312;
    color: black;
}
.profile-button:focus {
    background-color: #FFC312;
    color: black;
}

.profile-button:active {
    background-color: #FFC312;
    color: black;
}

.back:hover {
    color: #682773;
    cursor: pointer
}

.labels {
    font-size: 11px;
    color:white;
}

.add-experience:hover {
    background-color: #FFC312;
    color: black;
    cursor: pointer;
}
.background{
    background-color: #D7E2F8;
    width: 100%;
    height: 100%;
}
.row{
    background-color: #5E639B;
    border-radius: 20px;
}
.color{
    background-color: #D7E2F8;
     width: 100%;
    height: 100%;
}

</style>