const { defineStore } = Pinia

const useAdminStore = defineStore('admin', {
	state: () => ({
		reserve_list: []
	}),
	
	actions: {
		async dataRecv() {
			const { data } = await api.get('/admin/reserve-list-vue')
			this.reserve_list = data
		},
	
		async reserveOk(no, id) {
			const { data } = await api.get('/admin/reserve-ok-vue', {
				params: {
					no: no,
					id: id
				}
			})
			this.reserve_list = data
		},
		
		async reserveDelete(no, id) {
			const { data } = await api.get('/admin/reserve-delete-vue', {
				params: {
					no: no,
					id: id
				}
			})
			this.reserve_list = data
		}
	}
})