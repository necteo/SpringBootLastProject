const { defineStore } = Pinia

const useReserveStore = defineStore('reserve', {
	state: () => ({
		loc_list: [
			"강남구","강동구","강북구","강서구",
		  "관악구","광진구","구로구","금천구",
		  "노원구","도봉구","동대문구","동작구",
		  "마포구","서대문구","서초구","성동구",
		  "성북구","송파구","양천구","영등포구",
		  "용산구","은평구","종로구","중구","중랑구"
		],
		no: 0,
		loc: 'all',
		curpage: 1,
		totalpage: 0,
		food_list: [],
		image: 'https://img.freepik.com/premium-vector/no-photo-available-vector-icon-default-image-symbol-picture-coming-soon-web-site-mobile-app_87543-14040.jpg',
		title: '',
		day: '',
		time: '',
		inwon: '',
		isDate: true,
		isTime: false,
		isInwon: false,
		isReserveBtn: false,
		
		time_list: [], // 랜덤
		inwon_list: [
			'2명', '3명', '4명', '5명', '6명', '7명', '8명', '9명', '단체'
		]
	}),
	
	actions: {
		async dataRecv() {
			const { data } = await api.get('/reserve/food-list-vue', {
				params: {
					page: this.curpage,
					address: this.loc
				}
			})
			this.food_list = data.list
			this.loc = data.address
			this.curpage = data.curpage
			this.totalpage = data.totalpage
		},
		
		reset() {
			this.image = 'https://img.freepik.com/premium-vector/no-photo-available-vector-icon-default-image-symbol-picture-coming-soon-web-site-mobile-app_87543-14040.jpg'
			this.day = ''
			this.time = ''
			this.inwon = ''
			this.isTime = false,
			this.isInwon = false,
			this.isReserveBtn = false,
			this.time_list = []
		},
		
		dateSelect(day) {
			this.day = day
		},
		
		prev() {
			this.curpage = this.curpage > 1 ? this.curpage - 1 : this.curpage
			this.dataRecv()
		},
		
		next() {
			this.curpage = this.curpage < this.totalpage ? this.curpage + 1 : this.curpage
			this.dataRecv()
		},
		
		locChange() {
			this.curpage = 1
			this.dataRecv()
		},
		
		foodSelect(no, title, image1) {
			this.reset()
			this.no = no
			this.title = title
			this.image = image1
			this.isDate = true
		},
		
		timeSelect(time) {
			this.time = time
			this.isInwon = true
		},
		
		inwonSelect(inwon) {
			this.inwon = inwon
			this.isReserveBtn = true
		},
		
		async timeListData() {
			const { data } = await api.get('/reserve/time-list-vue')
			this.time_list = data
		},
		
		// 예약
		async reserveInsert() {
			const { data } = await api.post('/reserve/insert-vue', {
				cno: this.no,
				rday: this.day,
				rtime: this.time,
				rinwon: this.inwon
			})
			if (data === 'YES') {
				location.href = '/mypage/mypage-reserve'
			} else {
				alert('예약 실패입니다')
				this.reset()
			}
		}
	}
})