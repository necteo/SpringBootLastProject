const { defineStore } = Pinia

const useMypageStore = defineStore('mypage', {
	state: () => ({
		reserve_list: []
	}),
	
	actions: {
		async dataRecv() {
			const { data } = await api.get('/mypage/reserve-list-vue')
			console.log(data)
			this.reserve_list = data
		}
	}
})