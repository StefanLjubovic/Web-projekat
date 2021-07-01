//Mylib.js

import axios from 'axios';
import { handleError } from 'vue';

let server = {};
const baseUrl = 'http://localhost:8080'
// this (vue instance) is passed as that , so we
// can read and write data from and to it as we please :)
server.getAllRestaurants = () => {
    const options = {
        method: "GET",
        headers: {
            "Content-Type": 'application/json;charset=UTF-8',
            "Accept": 'application/json',
        },
        url: `${baseUrl}/restaurants/`,
    };
    return axios(options)
        .then(response => handleSuccess(response))
        .catch(error => handelError(error));
};

server.login = (data) => {
    const options = {
        method: "POST",
        url: `${baseUrl}/login`,
        data: data
    };
    
    return axios(options)
        .then(response => handleSuccess(response))
        .catch(error => handelError(error));
};

server.register=(data)=>{
    const options={
        method: "post",
        headers:{
            "Content-Type": 'application/json;charset=UTF-8',
        },
        url: `${baseUrl}/register`,
        data: data
    };
    return axios(options)
        .then(response=>handleSuccess(response))
        .catch(error=>handleError(error));
}

function handelError(error) {
    console.log('Error');
    return { success: false, data: error };
}
function handleSuccess(response) {
    return { success: true, data: response.data };
}


export default server;