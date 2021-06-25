<template>
    <div class="container mt-5 mb-5 d-flex justify-content-center">
    <div class="card px-1 py-4">
        <div class="card-body">
            <div class="d-flex flex-row"><img class="logo" src="../assets/logo_transparent.png"></div>
            <span class="header">
                <h6 class="register" style="display: inline">Register account:</h6>
                <button type="button" @click="loginPage" class="btn btn-outline-primary border border-light" style="display: inline">Login</button>
            </span>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <!-- <label for="name">Name</label> --> <input class="form-control" type="text" placeholder="Username" v-model="username"> </div>
                          <div class="alert alert-danger" v-if="userError" role="alert">
                            Field must not be empty!
                        </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <!-- <label for="name">Name</label> --> <input class="form-control" type="text" placeholder="Password" v-model="password"> </div>
                        <div class="alert alert-danger" v-if="passwordError" role="alert">
                            Field must not be empty!
                        </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <!-- <label for="name">Name</label> --> <input class="form-control" type="text" placeholder="Name" v-model="name"> </div>
                        <div class="alert alert-danger" v-if="nameError" role="alert">
                            Field must not be empty!
                        </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <!-- <label for="name">Name</label> --> <input class="form-control" type="text" placeholder="Surname" v-model="surname"> </div>
                        <div class="alert alert-danger" v-if="surnameError" role="alert">
                            Field must not be empty!
                        </div>
                </div>
            </div>
            <div class="form-group">
                <label>Date of birth:</label>
                <div>
                        <!-- <label for="name">Name</label> --> <input class="form-control" type="text" placeholder="dd/mm/yyyy" v-model="dateOfBirth">
                        <div class="alert alert-danger" v-if="dateError" role="alert">
                            Field must not be empty!
                        </div>
                </div>
            </div>
            <div class="form-group">
                <label for="exampleFormControlSelect1">Gender:</label>
                <select class="form-control" id="exampleFormControlSelect1" v-model="gender">
                    <option selected="selected">Male</option>
                    <option>Female</option>
                </select>
            </div>
            <div class="form-group">
                <label for="exampleFormControlSelect1">User type:</label>
                <select class="form-control" v-model="userType">
                    <option selected="selected">Customer</option>
                    <option>Manager</option>
                    <option>Deliverer</option>
                </select>
            </div>
            <button class="btn btn-primary btn-block confirm-button" @click="confirmForm">Confirm</button>
        </div>
    </div>
</div>
</template>

<script>
export default {
    watch: {
		$route(to, from) {},
	},
    data(){
        return{
            username: '',
            password: '',
            name: '',
            surname: '',
            gender: 'Male',
            dateOfBirth: '',
            userType:'Customer',
            userError : false,
            passwordError : false,
            nameError : false,
            surnameError : false,
            dateError : false,
            confirmError : false,
        }
    },
    methods:{
        confirmForm(){
            this.userError=false
            this.passwordError=false
            this.nameError=false
            this.surnameError=false
            this.dateError=false
            this.confirmError=false
            if(this.username=='')this.userError=true
            if(this.password=='')this.passwordError=true
            if(this.name=='')this.nameError=true
            if(this.surname=='')this.surnameError=true
            var dateRegex=/^\d{2}[.//]\d{2}[.//]\d{4}$/
            if(!(this.dateOfBirth.match(dateRegex))) this.dateError=true;
            if(this.userError || this.passwordError || this.nameError || this.surnameError || this.dateError) return;

            const newUser={
                username : this.username,
                password:this.password,
                name: this.name,
                surname:this.surname,
                gender:this.gender,
                dateOfBirth:this.dateOfBirth
            }
            console.log(newUser)
        },
        loginPage(){
            this.$router.push({ path: '/login' });
        }
    }
}
</script>

<style scoped>
    body {
    background-color: #FFEBEE
}
.logo{
    width:150px;
    margin-left: -20px;
}
.card {
    width: 400px;
    background-color: #fff;
    border: none;
    border-radius: 12px
}
.flex-row{
    margin-bottom:-20px;
}
label.radio {
    cursor: pointer;
    width: 100%
}

label.radio input {
    position: absolute;
    top: 0;
    left: 0;
    visibility: hidden;
    pointer-events: none
}

label.radio span {
    padding: 7px 14px;
    border: 2px solid #eee;
    display: inline-block;
    color: #039be5;
    border-radius: 10px;
    width: 100%;
    height: 48px;
    line-height: 27px
}

label.radio input:checked+span {
    border-color: #039BE5;
    background-color: #81D4FA;
    color: #fff;
    border-radius: 9px;
    height: 48px;
    line-height: 27px
}

.form-control {
    margin-top: 10px;
    height: 48px;
    border: 2px solid #eee;
    border-radius: 10px
}

.form-control:focus {
    box-shadow: none;
    border: 2px solid #039BE5
}

.agree-text {
    font-size: 12px
}

.terms {
    font-size: 12px;
    text-decoration: none;
    color: #039BE5
}

.confirm-button {
    height: 50px;
    border-radius: 10px
}

.display-false{
    display:none
}
.display-true{
    display:inline
}
.register{
    margin-right: 150px;
}
</style>