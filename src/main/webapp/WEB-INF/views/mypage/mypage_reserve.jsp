<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table">
		<tbody>
			<tr>
				<td class="text-center"><h3>예약 목록</h3></td>
			</tr>
		</tbody>
	</table>
	<div id="reserveApp">
		<table class="table">
			<thead>
				<tr class="danger">
					<th class="text-center">예약번호</th>
					<th class="text-center">업체명</th>
					<th class="text-center"></th>
					<th class="text-center">예약일</th>
					<th class="text-center">예약시간</th>
					<th class="text-center">인원</th>
					<th class="text-center">등록일</th>
					<th class="text-center"></th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="(vo, idx) in store.reserve_list" :key="idx">
					<td class="text-center">{{ vo.no }}</td>
					<td>{{ vo.svo.title }}</td>
					<td class="text-center">
						<img :src="vo.svo.image1" style="width: 30px; height: 30px">
					</td>
					<td class="text-center">{{ vo.rday }}</td>
					<td class="text-center">{{ vo.rtime }}</td>
					<td class="text-center">{{ vo.rinwon }}</td>
					<td class="text-center">{{ vo.dbday }}</td>
					<td class="text-center">
						<button class="btn-xs btn-info">{{ vo.isReserve === 0 ? '예약대기' : '예약완료' }}</button>
						<button class="btn-xs btn-warning" style="margin-left: 2px">취소</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<script src="/vue/axios.js"></script>
	<script src="/vue/reserve/mypageStore.js"></script>
	<script>
		const { createApp, onMounted } = Vue
		const { createPinia } = Pinia
		
		const app = createApp({
			setup() {
				const store = useMypageStore()
				
				onMounted(() => {
					store.dataRecv()
				})
				
				return {
					store
				}
			}
		})
		app.use(createPinia())
		app.mount('#reserveApp')
	</script>
</body>
</html>