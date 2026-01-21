const { defineStore } = Pinia

const useMypageStore = defineStore('mypage', {
	state: () => ({
		reserve_list: [],
		reserve_detail: {},
		show: false,
		stomp: null
	}),

	actions: {
		connect(id) {
			const sock = new SockJS('/ws')
			this.stomp = Stomp.over(sock)
			
			this.stomp.connect({}, () => {
				this.stomp.subscribe('/sub/notice/' + id, msg => {
					this.showToast(msg.body)
					this.dataRecv()
				})
			})
		},
		async dataRecv() {
			const { data } = await api.get('/mypage/reserve-list-vue')
			console.log(data)
			this.reserve_list = data
		},
		
		async reserveRequest(no) {
			const { data } = await api.get('/mypage/reserve-cancel-vue', {
				params: {
					no: no
				}
			})
			console.log(data)
			this.reserve_list = data
		},
		
		async reserveDetail(no) {
			const { data } = await api.get('/mypage/reserve-detail-vue', {
				params: {
					no: no
				}
			})
			this.reserve_detail = data
			this.show = true
		},
			
		showToast(message){
		  const toast = document.getElementById("reserveToast")
		  const toastMsg = document.getElementById("toastMsg")

		  toastMsg.innerText = message;
		  toast.classList.add("show");

		  // 3초 후 자동 닫힘
		  
		  setTimeout(() => {
		     hideToast()
		  }, 5000);
		}
	}
})

function hideToast() {
	const toast = document.getElementById("reserveToast");
	toast.classList.remove("show");
}