const { defineStore } = Pinia

const useCommonsReplyStore = defineStore('commons_reply', {
	state: () => ({
		list: [],
		curpage: 1,
		totalpage: 0,
		startPage: 0,
		endPage: 0,
		cno: 0,
		sessionId: '',
		msg: '',
		count: 0,
		// update관련
		upReplyNo: null,
		updateMsg: {},
		reReplyNo: null,
		replyMsg: {}
	}),
	
	getters: {
		// 페이지 출력
		range: (state) => {
			const arr = []
			for (let i = state.startPage; i <= state.endPage; i++) {
				arr.push(i) // push (저장) , pop (삭제)
			}
			return arr
		}
	},
	
	actions: {
		// then(response=>{})
		setPageData({ list, curpage, totalpage, startPage, endPage, cno, count }) {
			this.list = list
			this.curpage = curpage
			this.totalpage = totalpage
			this.startPage = startPage
			this.endPage = endPage
			this.cno = cno
			this.count = count
		},
		// prev() / next() / pageChange()
		movePage(page) {
			this.curpage = page
			this.commonsListData(this.cno)
		},
		
		async commonsListData(cno) {
			this.cno = cno
			const { data } = await api.get('/commons/list_vue', {
				params: {
					page: this.curpage,
					cno: this.cno
				}
			})
			this.setPageData(data)
		},
		
		async commonsInsert(msgRef) {
			if (this.msg.trim() === '') {
				msgRef.focus()
				return
			}
			const { data } = await api.post('/commons/insert_vue', {
				cno: this.cno,
				msg: this.msg,
				page: this.curpage
			})
			this.setPageData(data)
			this.msg = ''
		},
		// 삭제
		async commonsDelete(no) {
			const { data } = await api.delete('/commons/delete_vue', {
				params: {
					no: no,
					cno: this.cno,
					page: this.curpage
				}
			})
			console.log(data)
			this.setPageData(data)
		},
		// update
		toggleUpdate(no, msg) {
			this.upReplyNo  = this.upReplyNo === no ? null : no
			this.updateMsg[no] = msg
			this.reReplyNo = null
		},
		async replyUpdate(no) {
			const { data } = await api.put('/commons/update_vue', {
				no: no,
				cno: this.cno,
				page: this.curpage,
				msg: this.updateMsg[no]
			})
			this.setPageData(data)
			this.upReplyNo = null
		},
		// reply
		toggleReply(no) {
			this.reReplyNo  = this.reReplyNo === no ? null : no
			this.replyMsg[no] = ''
			this.upReplyNo = null
		},
		async replyReply(no) {
			const { data } = await api.post('/commons/reply_reply_insert_vue', {
				no: no,
				cno: this.cno,
				page: this.curpage,
				msg: this.replyMsg[no]
			})
			this.setPageData(data)
			this.reReplyNo = null
		}
	}
})