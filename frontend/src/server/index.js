//Mylib.js

import axios from 'axios';
import { handleError } from 'vue';

let server = {};
const baseUrl = 'http://localhost:8080'
// this (vue instance) is passed as that , so we
// can read and write data from and to it as we please :)
server.getAllRestaurants = () => {
    const token = localStorage.getItem('token')
    const options = {
        method: "GET",
        headers: {
            "Content-Type": 'application/json;charset=UTF-8',
            "Accept": 'application/json',
            "Authorization": token,
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


server.getRestaurantById = (id) => {
    const options = {
        method: "GET",
        url: `${baseUrl}/restaurantById?id=${id}`,
    };
    return axios(options)
        .then(response => handleSuccess(response))
        .catch(error => handelError(error));
}

server.getUserByToken = (token) => {
    const options = {
        method: "GET",
        url: `${baseUrl}/userByToken?token=${token}`,
    };
    return axios(options)
        .then(response => handleSuccess(response))
        .catch(error => handelError(error));

}

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

server.uploadImage = file => {
    const token = localStorage.getItem("token")
    // ImagePicker saves the taken photo to disk and returns a local URI to it
    let localUri = file.uri;
    let filename = localUri.split('/').pop();
        console.log(filename);
    // Infer the type of the image
    let match = /\.(\w+)$/.exec(filename);
    let type = match ? `image/${match[1]}` : `image`;

    // Upload the image using the fetch and FormData APIs
    let formData = new FormData();
    // Assume "photo" is the name of the form field the server expects
    formData.append('file', { uri: localUri, name: filename, type });
        const options = {
            method: "POST",
            body: formData,
            headers: {'Authorization': token,'content-type': 'multipart/form-data',},
        };
        return fetch(`${baseUrl}/upload`, options).then(response => response.json()).catch(error => handelError(error));
}

server.createRestaurant=(data)=>{
    
    const token = localStorage.getItem("token");
    const options={
        method: "POST",
        headers:{
            "Content-Type": 'application/json;charset=UTF-8',
            "Authorization": token,
        },
        url: `${baseUrl}/createRestaurant`,
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