<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesSheet" href="/css/map.css" type="text/css">
<link rel="stylesheet" href="/css/toast.css">
<script>
const SESSION_ID = '${ sessionScope.userid }'
const CNO = '${ param.cno }'
</script>
<style type="text/css">
.page-link, .a-btn {
	cursor: pointer;
}
</style>
</head>
<body>
	<!-- ****** Breadcumb Area Start ****** -->
	<div class="breadcumb-area" style="background-image: url(/img/bg-img/breadcumb.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="bradcumb-title text-center">
						<h2>${ vo.title }</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="breadcumb-nav">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<!-- ****** Breadcumb Area End ****** -->

	<!-- ****** Archive Area Start ****** -->
	<section class="archive-area section_padding_80">
		<div class="container">
			<div class="row">
				<table class="table">
					<tbody>
						<tr>
							<td width="30%" class="text-center" rowspan="6">
								<img src="${ vo.image1 }" style="width: 100%; height: 320px">
							</td>
							<td colspan="2"><h3>${ vo.title }</h3></td>
						</tr>
						<tr>
							<td width="15%" class="text-center">주소</td>
							<td width="55%">${ vo.address }</td>
						</tr>
						<tr>
							<td width="15%" class="text-center">안내</td>
							<td width="55%">${ vo.avo.infocenter }</td>
						</tr>
						<tr>
							<td width="15%" class="text-center">이용시간</td>
							<td width="55%">${ vo.avo.usetime }</td>
						</tr>
						<tr>
							<td width="15%" class="text-center">휴무일</td>
							<td width="55%">${ vo.avo.restdate }</td>
						</tr>
						<tr>
							<td width="15%" class="text-center">주차</td>
							<td width="55%">${ vo.avo.parking }</td>
						</tr>
					</tbody>
				</table>
				<table class="table">
					<tbody>
						<tr>
							<td>${ vo.avo.msg }</td>
						</tr>
						<tr>
							<td class="text-right">
								<a href="javascript:history.back()" class="btn btn-sm btn-danger">목록</a>
							</td>
						</tr>
					</tbody>
				</table>
				<table class="table">
					<tbody>
						<tr>
							<td class="text-center">
								<div class="map_wrap">
							    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
							    <div id="menu_wrap" class="bg_white">
						        <div class="option">
					            <div>
				                <form onsubmit="searchPlaces(); return false;">
			                    키워드 : <input type="text" value="${ addr }" id="keyword" size="15"> 
			                    <button type="submit">검색하기</button> 
				                </form>
					            </div>
						        </div>
						        <hr>
						        <ul id="placesList"></ul>
						        <div id="pagination"></div>
							    </div>
								</div>
								<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8cbbd5d669a90ce360aaa6cf764e5bfb&libraries=services"></script>
								<script src="/vue/map.js"></script>
							</td>
						</tr>
					</tbody>
				</table>
				<script src="/vue/axios.js"></script>
				<script src="/vue/reply/commonsReplyStore.js"></script>
				<!-- Comment Area Start -->
        <div id="comment">
					<div class="comment_area section_padding_50 clearfix text-left">
						<h4 class="mb-30">댓글 ({{ store.count }})</h4>
					
						<ol>
							<!-- Single Comment Area -->
							<li class="single_comment_area" v-for="(rvo, idx) in store.list" :key="idx">
								<div class="comment-wrapper d-flex" v-if="rvo.group_tab === 0">
									<!-- Comment Meta -->
									<div class="comment-author">
										<img v-if="rvo.sex === '남자'" src="/img/man.png">
										<img v-else="rvo.sex === '여자'" src="/img/woman.png">
									</div>
									<!-- Comment Content -->
									<div class="comment-content">
										<span class="comment-date text-muted">{{ rvo.dbday }}</span>
										<h5>{{ rvo.name }}</h5>
										<p>{{ rvo.msg }}</p>
										<a class="a-btn" v-if="store.sessionId == rvo.id" 
											@click="store.toggleUpdate(rvo.no, rvo.msg)">{{ store.upReplyNo === rvo.no ? '취소' : '수정' }}</a>
										<a class="a-btn active" v-if="store.sessionId == rvo.id" @click="store.commonsDelete(rvo.no)">삭제</a>
										<a class="a-btn" v-if="store.sessionId != null"
											@click="store.toggleReply(rvo.no)">{{ store.reReplyNo === rvo.no ? '취소' : '댓글' }}</a>
										
										<div class="comment-form" style="padding-top: 5px" v-if="store.upReplyNo === rvo.no">
											<form action="#" method="post">
												<textarea ref="msg" v-model="store.updateMsg[rvo.no]" cols="50" rows="5" placeholder="Message"
													style="float: left;display: inline-block;"></textarea>
												<button type="button" class="btn-primary"
													style="float: left;width: 80px;height: 100px;display: inline-block;" 
													@click="store.replyUpdate(rvo.no)">댓글수정</button>
											</form>
										</div>
										<div class="comment-form" style="padding-top: 5px" v-if="store.reReplyNo === rvo.no">
											<form action="#" method="post">
												<textarea ref="msg" v-model="store.replyMsg[rvo.no]" cols="50" rows="5" placeholder="Message"
													style="float: left;display: inline-block;"></textarea>
												<button type="button" class="btn-primary"
													style="float: left;width: 80px; height: 100px; display: inline-block" 
													@click="store.replyReply(rvo.no)">댓글</button>
											</form>
										</div>
									</div>
								</div>
								
								<ol class="children" v-if="rvo.group_tab === 1">
                  <li class="single_comment_area">
                    <div class="comment-wrapper d-flex">
                      <!-- Comment Meta -->
                      <div class="comment-author">
                        <img src="/img/man.png" v-if="rvo.sex === '남자'">
                        <img src="/img/woman.png" v-else>
                      </div>
                      <!-- Comment Content -->
                      <div class="comment-content">
                        <span class="comment-date text-muted">{{ rvo.dbday }}</span>
                        <h5>{{ rvo.name }}</h5>
                        <p>{{ rvo.msg }}</p>
                        <a class="a-btn" v-if="store.sessionId == rvo.id" 
													@click="store.toggleUpdate(rvo.no, rvo.msg)">{{ store.upReplyNo === rvo.no ? '취소' : '수정' }}</a>
                        <a v-if="store.sessionId === rvo.id" class="active a-btn" @click="store.commonsDelete(rvo.no)">삭제</a>
                      </div>
                      <div class="comment-form" style="padding-top: 5px" v-if="store.upReplyNo === rvo.no">
											<form action="#" method="post">
													<textarea ref="msg" v-model="store.updateMsg[rvo.no]" cols="50" rows="5" placeholder="Message"
														style="float: left; display: inline-block"></textarea>
													<button type="button" class="btn-primary"
														style="float: left;width: 80px;height: 100px;display: inline-block;" 
														@click="store.replyUpdate(rvo.no)">댓글수정</button>
												</form>
											</div>
                    </div>
                  </li>
                </ol>
								
							</li>
						</ol>
					</div>
					
					<!-- Leave A Comment -->
					<div class="leave-comment-area section_padding_50 clearfix" v-if="store.sessionId != ''">
						<div class="comment-form">
							<form action="#" method="post">
								<textarea ref="msgRef" v-model="store.msg" cols="95" rows="5" placeholder="Message"
									style="float: left;display: inline-block;"></textarea>
								<button type="button" class="btn-primary"
									style="float: left;width: 80px;height: 100px;display: inline-block;" @click="store.commonsInsert(msgRef)">댓글쓰기</button>
							</form>
						</div>
					</div>
					
					<div class="col-12">
						<div class="pagination-area d-sm-flex mt-15">
							<nav aria-label="#">
								<ul class="pagination">
									<li class="page-item" v-if="store.startPage > 1">
										<a class="page-link" @click="store.movePage(store.startPage - 1)">
										이전 <i class="fa fa-angle-double-left" aria-hidden="true"></i></a>
									</li>
								
									<li :class="i == store.curpage ? 'page-item active' : 'page-item'" 
										v-for="i in store.range" :key="i">
										<a class="page-link" @click="store.movePage(i)">{{ i }}</a>
									</li>
									
									<li class="page-item" v-if="store.endPage < store.totalpage">
										<a class="page-link" @click="store.movePage(store.endPage + 1)">
										다음 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
									</li>
								</ul>
							</nav>
							<div class="page-status">
								<p>Page {{ store.curpage }} of {{ store.totalpage }} results</p>
							</div>
						</div>
					</div>
					<jsp:include page="../commons/toast.jsp"></jsp:include>
				</div>
				<script>
					const { createApp, onMounted, ref } = Vue
					const { createPinia } = Pinia
					
					const commonApp = createApp({
						setup() {
							const store = useCommonsReplyStore()
							const msgRef = ref(null)
							
							onMounted(() => {
								store.sessionId = SESSION_ID
								store.commonsListData(CNO)
								store.connect(SESSION_ID)
							})
							
							return {
								store,
								msgRef
							}
						}
					})
					commonApp.use(createPinia())
					commonApp.mount('#comment')
				</script>
			</div>
		</div>
	</section>
</body>
</html>