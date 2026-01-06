const { defineStore } = Pinia
// store => 저장 공간 (출력에 필요한 데이터를 모아서 관리)
// static 변수 => 멤버
const initialState = () => ({
	list: [], // 검색된 데이터
	curpage: 1,
	totalpage: 0,
	startPage: 0,
	endPage: 0,
	fd: '해수욕장',
	selected: 12
})
const useJejuStore = defineStore('jeju_find', {
	// 모든 컴포넌트에서 사용이 가능하게 공유 변수 설정
	state: initialState,
	// return이 있는 경우(함수)
	getters: {
		range: (state) => {
			const arr = []
			for (let i = state.startPage; i <= state.endPage; i++) {
				arr.push(i) // 맨 뒤에 값을 설정
			}
			return arr
		}
	},
	// 기능 => 사용자 요청 (void)
	actions: {
		// 서버로부터 요청값 받기
		async jejuFindData() {
			const res = await api.get('/jeju/find_vue', {
				params: {
					page: this.curpage,
					selected: this.selected,
					fd: this.fd
				}
			})
			console.log(res.data)
			this.setPageData(res.data)
		},
		setPageData(data) {
			this.list = data.list
			this.curpage = data.curpage
			this.totalpage = data.totalpage
			this.startPage = data.startPage
			this.endPage = data.endPage
			this.selected = data.selected
			this.fd = data.fd
		},
		// prev / next / pageChange
		movePage(page) {
			this.curpage = page
			this.jejuFindData()
		},
		find(findRef) {
			if (this.fd === '') {
				findRef.focus()
				return
			}
			this.curpage = 1
			this.jejuFindData()
		}
	}
})