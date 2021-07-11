<template>
	<transition name="modal">
		<div class="modal-mask">
			<div class="modal-wrapper">
				<div class="modal-item-container">
					<div class="modal-item-image">
						<div class="modal-close" @click="$emit('closeModal')">
							<i class="fas fa-times"></i>
						</div>
						<div class="image-container" v-if="!!item.image">
							<img class="item-image" :src="getImage(item.image)" alt="Image" />
							<div class="remove-image" @click="removeImage"><i class="fas fa-times"></i> Remove image</div>
						</div>

						<div class="image-placeholder" @click="onPickFile" v-if="!item.image">
							<i class="far fa-images fa-3x"  v-bind:class="{ error: error['logo'] }"></i>
							<input type="file" @change="uploadPhoto" accept="image/*" style="display:none" ref="fileInput" />
						</div>
					</div>
					<div class="modal-item-body">
						<div class="form-group ">
							<label for="nameInput" v-bind:class="{ error: error['name'] }">Name:</label>
							<input type="text" class="form-control" placeholder="Enter name here...*" v-model="item.name" />
						</div>
						<div class="form-group ">
							<label for="nameInput" v-bind:class="{ error: error['description'] }">Description:</label>
							<textarea type="text" class="form-control" placeholder="Enter description here..." v-model="item.description" rows="3" />
						</div>
						<div class="body-row">
							<div class="form-group ">
								<label for="nameInput" v-bind:class="{ error: error['type'] }">Type:</label>
								<div><div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="inlineRadioOptions" v-model="item.type" value="food" />
									<label class="form-check-label" for="inlineRadio1">Food</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="inlineRadioOptions" v-model="item.type" value="drink" />
									<label class="form-check-label" for="inlineRadio2">Drink</label>
								</div></div>
							</div>
							<div class="form-group ">
								<label for="nameInput" v-bind:class="{ error: error['size'] }">Size:</label>
                                <div class="item-size-container">
								    <input type="number" class="form-control" placeholder="Enter size*" v-model="item.size" />
                                    <span>{{item.type == 'drink' ? 'L': 'g'}}</span>
                                </div>
							</div>
                            <div class="form-group ">
								<label for="nameInput" v-bind:class="{ error: error['price'] }">Price:</label>
								<div class="item-size-container">
								<input type="number" class="form-control" placeholder="Enter price*" v-model="item.price" />
								<span> RSD</span>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-item-footer">
						<div class="price-container">
						</div>
						<button class="submit-order" @click="submitItem">
							Save
						</button>
					</div>
				</div>
			</div>
		</div>
	</transition>
</template>

<script>
import server from '../../server';
import Swal from 'sweetalert2'
export default {
	data() {
		return {
			item: {
				image: '',
                type: 'food'
			},
			error: {},
			imageFile: null,
			newImage: false,
		};
	},
	methods: {
		show() {
			this.$modal.show('my-first-modal');
		},
		hide() {
			this.$modal.hide('my-first-modal');
		},
		onPickFile() {
			console.log('Open images');
			this.$refs.fileInput.click();
		},
		uploadPhoto(event) {
			const files = event.target.files;
			const fileReader = new FileReader();
			fileReader.addEventListener('load', () => {
				this.item.image = fileReader.result;
			});
			fileReader.readAsDataURL(files[0]);
			this.imageFile = files[0];
		},
		removeImage() {
			this.item.image = '';
			this.newImage = true;
		},
        containesError(){
            for(const key in this.error){
                console.log(key, this.error[key]);
                if(this.error[key])
                    return true
            }
            return false
        },
        checkValid(){
            this.error['name'] = !this.item.name;
            this.error['price'] = !this.item.price;
            this.error['size'] = !this.item.size;
            this.error['logo'] = !this.item.image;
			this.nameError = false;
			const sameNameItem = this.restaurant.items.findIndex(e => e.name == this.item.name)
			if(sameNameItem > -1 && this.restaurant.items[sameNameItem].id != this.item.id){ // Izmena postojeceg {
				this.error['name'] = true;
				this.nameError = true;
				Swal.fire({
					title: 'Error!',
					text: "Item with that name already exists...",
					icon: 'error',
					confirmButtonText: 'Okay',
					timer: 3000,
					timerProgressBar: true
				})
			}

            return !this.containesError();
        },
        showErrorMessage(){
            Swal.fire({
                title: 'Error!',
                text: "You didn't feel all fields...",
                icon: 'error',
                confirmButtonText: 'Okay',
                timer: 3000,
                timerProgressBar: true
            })
        },
        async uploadImage(){
            const respImage = await server.uploadImage(this.imageFile);
            if(respImage.success){
                console.log(respImage);
                const image = respImage.data.filename;
                return image;
            }
            return 0;
        },
        notifySuccess(){
            Swal.fire({ 
                title: 'Item is saved!',
                icon: 'success',
                confirmButtonText: 'Okay',
                timer: 3000,
                timerProgressBar: true,
				nameError: false
            })
        },
		async submitItem(event) {
            event.preventDefault();
            const valid = this.checkValid();
            if(!valid){
				if(!this.nameError)
                	this.showErrorMessage();
                return;
            }
			let image = this.item.image;
			if (!this.item.id || this.newImage) {
				image = await this.uploadImage();
                if(!image){
                    this.notiftError();
					return;
                }
			}
            this.item.image = image;
			const resp = await server.saveItem(this.item, this.restaurant.id);
            if(resp.success){
                this.notifySuccess();
                this.$emit("closeSuccess");
            }else{
                this.notiftError();
            }
		},
        getImage(image){
            if(image.includes("data")){
                return image;
            }
            return server.getImage(image);
        }
	},
	props: ['selectedItem', 'restaurant'],
	mounted() {
		if (!!this.selectedItem) {
            console.log('Take selected item', this.selectedItem);
			this.item = JSON.parse(JSON.stringify(this.selectedItem));
		}
	},
};
</script>

<style scoped>
.modal-mask {
	position: fixed;
	z-index: 3;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	transition: opacity 0.3s ease;
	padding: 20px 0px;
	overflow: auto;
}

.modal-item-container {
	width: 700px;
	margin: 0px auto;
	/* padding: 20px 30px; */
	background-color: #fff;
	border-radius: 20px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
	transition: all 0.3s ease;
	display: flex;
	flex-direction: column;
}

.modal-item-image {
	border-radius: 20px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
	overflow: hidden;
	position: relative;
}

.modal-header h3 {
	margin-top: 0;
	color: #42b983;
}

.modal-item-body {
	padding: 20px;
}
.modal-item-body > h3 {
	color: #42405f;
	font-weight: 600;
}
.modal-item-body > p {
	color: #8f8fa1;
}

.modal-enter {
	opacity: 0;
}

.modal-leave-active {
	opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
	-webkit-transform: scale(1.1);
	transform: scale(1.1);
}

.item-image {
	width: 100%;
}

.modal-close {
	position: absolute;
	top: 20px;
	right: 20px;
	border-radius: 100px;
	background-color: white;
	width: 30px;
	height: 30px;
	display: flex;
	align-items: center;
	justify-content: center;
	cursor: pointer;
}

.modal-item-footer {
	border-top: 1px solid #8f8fa1;
	padding: 15px 20px;
	display: flex;
	flex-direction: row;
	align-items: center;
}

.amount-button {
	border: 1px solid #dfdfdf;
	height: 30px;
	width: 30px;
	border-radius: 100px;
	background-color: white;
	cursor: pointer;
	outline: unset;
}
.amount {
	margin: 0px 15px;
}
.price-container {
	flex: 1;
}

.submit-order {
	background-color: #fddf6d;
	border: unset;
	width: 300px;
	color: #42405f;
	font-weight: 600;
	border-radius: 100px;
	padding: 10px 0;
	cursor: pointer;
}

.image-placeholder {
	height: 250px;
	width: 100%;
	display: flex;
	flex-direction: row;
	justify-items: center;
	align-items: center;
	text-align: center;
	vertical-align: middle;
	cursor: pointer;
}
.image-placeholder > i {
	width: 100%;
	color: #8f8fa1;
	opacity: 0.7;
}

.image-placeholder:hover > i {
	opacity: 1;
}

.price-container {
	display: flex;
	align-items: center;
}
.price-container > label {
	margin-right: 10px;
}
.remove-image {
	position: absolute;
	bottom: 10px;
	right: 10px;
	cursor: pointer;
	background-color: white;
	border-radius: 100px;
	padding: 0 10px;
	color: #8f8fa1;
	font-weight: 500;
	/* color: white; */
}
.body-row{
    display: flex;
    justify-content: space-between;
}
.item-size-container{
    display: flex;
    align-items: flex-end;
}
.item-size-container > span{
    margin-left: 5px;
}
.error{
    color: #FC4C59 !important;
    font-weight: 600;
}
</style>
