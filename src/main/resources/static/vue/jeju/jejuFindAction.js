const { createApp, onMounted, ref } = Vue
const { createPinia } = Pinia

const jejuApp = createApp({
	setup() {
		// store읽기
		const store = useJejuStore()
		// ref
		const selectedRef = ref('')
		const findRef = ref('')
		// onMounted()
		onMounted(() => {
			store.jejuFindData()
		})
		// return
		return {
			store,
			selectedRef,
			findRef
		}
	}
})
jejuApp.use(createPinia())
jejuApp.mount('#jeju_find')