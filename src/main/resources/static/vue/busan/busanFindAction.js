const { createApp, onMounted, ref } = Vue
const { createPinia } = Pinia

const busanApp = createApp({
	setup() {
		// store읽기
		const store = useBusanStore()
		// ref
		const addressRef = ref('')
		// onMounted()
		onMounted(() => {
			store.busanFindData()
		})
		// return
		return {
			store,
			addressRef
		}
	}
})
busanApp.use(createPinia())
busanApp.mount('#busan_find')