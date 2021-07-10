<template>
	<div class="background">
		<div class="container">
			<table class="table table-color">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">
							Name
							<a href="#" class="href" @click="$emit('sortName', 1)"><i class="fas fa-chevron-up"></i></a>
							<a href="#" class="href down" @click="$emit('sortName', -1)"><i class="fas fa-chevron-down"></i></a>
						</th>
						<th scope="col">
							Surname
							<a href="#" class="href" @click="$emit('sortSurname', 1)"><i class="fas fa-chevron-up"></i></a>
							<a href="#" class="href down" @click="$emit('sortSurname', -1)"><i class="fas fa-chevron-down"></i></a>
						</th>
						<th scope="col">
							Username
							<a href="#" class="href" @click="$emit('sortUsername', 1)"><i class="fas fa-chevron-up"></i></a>
							<a href="#" class="href down" @click="$emit('sortUsername', -1)"><i class="fas fa-chevron-down"></i></a>
						</th>
						<th scope="col">Type
              <a href="#" class="href" @click="$emit('sortType', 1)"><i class="fas fa-chevron-up"></i></a>
							<a href="#" class="href down" @click="$emit('sortType', -1)"><i class="fas fa-chevron-down"></i></a>

            </th>
						<th scope="col" style="width: 200px">
							Collected points
							<a href="#" class="href" @click="$emit('sortPoints', 1)"><i class="fas fa-chevron-up"></i></a>
							<a href="#" class="href down" @click="$emit('sortPoints', -1)"><i class="fas fa-chevron-down"></i></a>
						</th>
						<th scope="col" style="text-align: center">Status</th>
					</tr>
				</thead>
				<tbody>
          <tr v-if="users?.length == 0">
            <td colspan="6" class="empty-table">You don't have any data for this filter...</td>
          </tr>
					<tr v-for="(user, i) in users" :key="i">
						<td>{{ i + 1 }}</td>
						<td>{{ user.firstName }}</td>
						<td>{{ user.lastName }}</td>
						<td>{{ user.username }}</td>
						<td>{{ user.role }}</td>
						<td style="text-align: right">{{ user.collectedPoints.toFixed(2) }}</td>
						<td  style="    text-align: center;">
							<button
              v-if="!user.status && user.role != 'Admin'"
								type="button"
								@click="changeText(i)"
								data-toggle="modal"
								data-target="#exampleModal"
								class="btn btn-danger btn-circle btn-sm"
							></button>

							<button
                v-if="user.status && user.role != 'Admin'"
								type="button"
								@click="changeText(i)"
								data-toggle="modal"
								data-target="#exampleModal"
								class="btn btn-success btn-circle btn-sm"
							></button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">{{ block.title }}</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						{{ block.content }}
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal" @click="changeStatus()">Confirm</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import Server from '@/server';
export default {
	data() {
		return {
			block: {
				title: 'Ban user',
				content: 'Are you sure you want to ban user?',
				id: '0',
			},
		};
	},
	props: {
		users: Array,
	},
	emits: ['suspiciousUsers', 'sortPoints', 'sortUsername', 'sortSurname', 'sortName',"sortType"],
	methods: {
		changeStatus() {
			this.users[this.block.id].status = !this.users[this.block.id].status;
			Server.updateUser(this.users[this.block.id]).then((resp) => {
				if (resp.success) {
					console.log('this.restaurants');
				}
			});
		},
		changeText(i) {
			this.block.id = i;
			if (!this.users[i].blocked) {
				this.block.title = 'Ban user';
				this.block.content = 'Are you sure you want to ban user?';
			} else {
				this.block.title = 'Unban user';
				this.block.content = 'Are you sure you want to unban user?';
			}
		},
	},
};
</script>

<style scoped>
.table-color {
	background: white;
	border-radius: 15px;
}
.down {
	color: #fc4c59;
}
.empty-table{
  text-align: center;
  padding: 100px 0;
  font-weight: 500;
}
</style>
