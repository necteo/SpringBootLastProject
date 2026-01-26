<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/toast.css">
<script type="text/javascript">
const ID = '${ sessionScope.userid }'
</script>
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
						<button class="btn-xs btn-info" v-if="vo.isReserve === 0">예약대기</button>
						<button class="btn-xs btn-success" v-else @click="store.reserveDetail(vo.no)">예약완료</button>
						<button class="btn-xs btn-warning" style="margin-left: 2px" v-if="vo.isCancel === 0"
							@click="store.reserveRequest(vo.no)">취소요청</button>
						<span class="btn btn-xs btn-default" style="margin-left: 2px" v-else>취소대기</span>
					</td>
				</tr>
			</tbody>
		</table>
		<div v-if="store.show">
			<table class="table">
				<tbody>
					<tr>
						<th colspan="8"><h3>예약정보</h3></th>
					</tr>
					<tr>
						<th class="text-center">예약번호</th>
						<td class="text-center">{{ store.reserve_detail.no }}</td>
						<th class="text-center">예약일</th>
						<td class="text-center">{{ store.reserve_detail.rday }}</td>
						<th class="text-center">예약시간</th>
						<td class="text-center">{{ store.reserve_detail.rtime }}</td>
						<th class="text-center">예약인원</th>
						<td class="text-center">{{ store.reserve_detail.rinwon }}</td>
					</tr>
				</tbody>
			</table>
			<table class="table">
				<tbody>
					<tr>
						<th><h3>맛집정보</h3></th>
					</tr>
					<tr>
						<td width="30%" class="text-center" rowspan="8">
							<img :src="store.reserve_detail.svo.image1" style="width: 100%; height: 120px">
						</td>
						<td colspan="2"><h3>{{ store.reserve_detail.svo.title }}</h3></td>
					</tr>
					<tr>
						<td width="15%" class="text-center">주소</td>
						<td width="55%">{{ store.reserve_detail.svo.address }}</td>
					</tr>
					<tr>
						<td colspan="2" class="text-right">
							<button class="btn-sm btn-warning" @click="store.show = false">닫기</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<jsp:include page="../commons/toast.jsp"></jsp:include> 
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
					store.connect(ID)
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