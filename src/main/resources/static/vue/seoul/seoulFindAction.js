const { createApp, onMounted, ref } = Vue
const { createPinia } = Pinia

const seoulApp = createApp({
	setup() {
		// store읽기
		const store = useSeoulStore()
		// ref
		const addressRef = ref('')
		// onMounted()
		onMounted(() => {
			store.seoulFindData()
		})
		// return
		return {
			store,
			addressRef
		}
	}
})
seoulApp.use(createPinia())
seoulApp.mount('#seoul_find')