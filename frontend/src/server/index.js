//Mylib.js

import axios from 'axios';

let server = {};
const baseUrl = 'http://localhost:8080';
// this (vue instance) is passed as that , so we
// can read and write data from and to it as we please :)
server.getAllRestaurants = () => {
	const token = localStorage.getItem('token');
	const options = {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			Accept: 'application/json',
			Authorization: token,
		},
		url: `${baseUrl}/restaurants/`,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

server.getAllOrders = (id) => {
	const token = localStorage.getItem('token');
	const options = {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			Accept: 'application/json',
		},
		url: `${baseUrl}/getOrders?id=${id}`,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};
server.getRestaurantReviews = (id) => {
	const token = localStorage.getItem('token');
	const options = {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			Accept: 'application/json',
		},
		url: `${baseUrl}/restaurant/reviews?id=${id}`,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

server.getSuspiciousUsers = () => {
	const token = localStorage.getItem('token');
	const options = {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			Accept: 'application/json',
		},
		url: `${baseUrl}/userList/suspicious`,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

server.getImage = (image) => {
	if (image?.includes(baseUrl)) return image;
	return !!image ? `${baseUrl}/${image}` : '';
};

server.login = (data) => {
	const options = {
		method: 'POST',
		url: `${baseUrl}/login`,
		data: data,
	};

	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};
server.updateRestaurant = (data) => {
	const options = {
		method: 'POST',
		url: `${baseUrl}/updateRestaurant`,
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
		},
		data: data,
	};

	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

server.getRestaurantById = (id) => {
	const options = {
		method: 'GET',
		url: `${baseUrl}/restaurantById?id=${id}`,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};
server.getManager = (id) => {
	const options = {
		method: 'GET',
		url: `${baseUrl}/getManagerById?id=${id}`,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

server.saveItem = (item, restaurantId) => {
	const options = {
		method: 'POST',
		url: `${baseUrl}/saveItem/${restaurantId}`,
		data: item,
	};

	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

server.getUserByToken = (token) => {
	const options = {
		method: 'GET',
		url: `${baseUrl}/userByToken?token=${token}`,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

server.register = (data) => {
	const options = {
		method: 'post',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
		},
		url: `${baseUrl}/register`,
		data: data,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

server.registerRestaurant = (data) => {
	const options = {
		method: 'post',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			Accept: 'application/json',
		},
		url: `${baseUrl}/restaurants/register`,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

server.updateUser = (data) => {
	const options = {
		method: 'put',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			Accept: 'application/json',
		},
		url: `${baseUrl}/userList/update`,
		data: data,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

server.updateReview = (data) => {
	const options = {
		method: 'put',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			Accept: 'application/json',
		},
		url: `${baseUrl}/restaurant/reviewsUpdate`,
		data: data,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

server.updateOrderStatus = (data) => {
	const options = {
		method: 'put',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			Accept: 'application/json',
		},
		url: `${baseUrl}/orders/update`,
		data: data,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

server.updatePassword = (data) => {
	const options = {
		method: 'put',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			Accept: 'application/json',
		},
		url: `${baseUrl}/editProfile/updatePassword`,
		data: data,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

server.getAllManagers = () => {
	const options = {
		mehod: 'get',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			Accept: 'application/json',
		},
		url: `${baseUrl}/restaurants/managers`,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

server.getAllUsers = () => {
	const options = {
		mehod: 'get',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			Accept: 'application/json',
		},
		url: `${baseUrl}/userList`,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

server.uploadImage = (file) => {
	const token = localStorage.getItem('token');
	// Upload the image using the fetch and FormData APIs
	let formData = new FormData();
	formData.set('enctype', 'multipart/form-data');
	const filename = file.name;
	// Assume "photo" is the name of the form field the server expects
	formData.append('file', file);
	formData.append('filename', filename);
	console.log(formData);
	const options = {
		method: 'POST',
		body: formData,
		// headers: {'Authorization': token,
		// 'content-type': 'multipart/form-data',},
	};
	return fetch(`${baseUrl}/upload`, options)
		.then((response) => response.json())
		.then(handleSuccess)
		.catch(handleError);
};

server.createRestaurant = (data) => {
	const token = localStorage.getItem('token');
	const options = {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			Authorization: token,
		},
		url: `${baseUrl}/createRestaurant`,
		data: data,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};
server.createReview = (data) => {
	const token = localStorage.getItem('token');
	const options = {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			Authorization: token,
		},
		url: `${baseUrl}/orders/saveReview`,
		data: data,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};
server.createOrder = (data) => {
	const token = localStorage.getItem('token');
	const options = {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			Authorization: token,
		},
		url: `${baseUrl}/createOrder`,
		data: data,
	};
	return axios(options)
		.then((response) => handleSuccess(response))
		.catch((error) => handleError(error));
};

function handleError(error) {
	console.log('Error');
	return { success: false, data: error.response.data };
}
function handleSuccess(response) {
	return { success: true, data: response.data || response };
}

export default server;
