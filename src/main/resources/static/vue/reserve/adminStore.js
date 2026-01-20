const { defineStore } = Pinia

const useAdminStore = defineStore('admin', {
	state: () => ({
		reserve_list: []
	}),
	
	actions: {
		async dataRecv() {
			const { data } = await api.get('/admin/reserve-list-vue')
			console.log(data)
			this.reserve_list = data
		}
	}
})