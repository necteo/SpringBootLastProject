<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.a-btn {
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
						<h2>상세 보기</h2>
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
			<div class="row text-center" style="width: 800px; margin: 0 auto">
				<table class="table">
					<tbody>
						<tr>
							<th width="20%" class="danger text-center">번호</th>
							<td width="30%">${vo.no}</td>
							<th width="20%" class="danger text-center">작성일</th>
							<td width="30%">${vo.dbday}</td>
						</tr>
						<tr>
							<th width="20%" class="danger text-center">이름</th>
							<td width="30%">${vo.name}</td>
							<th width="20%" class="danger text-center">조회수</th>
							<td width="30%">${vo.hit}</td>
						</tr>
						<tr>
							<th width="20%" class="danger text-center">제목</th>
							<td colspan="3" class="text-left">${vo.subject}</td>
						</tr>
						<tr>
							<td colspan="4" class="text-left" valign="top" height="200">
								<pre style="white-space: pre-wrap; border: none; background-color: white">${vo.content}</pre>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="text-right">
								<a href="update?no=${vo.no}" class="btn btn-xs btn-success">수정</a>
								<a href="delete?no=${vo.no}" class="btn btn-xs btn-info">삭제</a>
								<a href="list" class="btn btn-xs btn-warning">목록</a>
							</td>
						</tr>
					</tbody>
				</table>
        <!-- Comment Area Start -->
        <div id="comment">
					<div class="comment_area section_padding_50 clearfix text-left">
						<h4 class="mb-30">댓글 ({{count}})</h4>
					
						<ol>
							<!-- Single Comment Area -->
							<li class="single_comment_area" v-for="(rvo, idx) in list" :key="idx">
								<div class="comment-wrapper d-flex">
									<!-- Comment Meta -->
									<div class="comment-author">
										<img v-if="rvo.sex === '남자'" src="/img/man.png">
										<img v-else="rvo.sex === '여자'" src="/img/woman.png">
									</div>
									<!-- Comment Content -->
									<div class="comment-content">
										<span class="comment-date text-muted">{{rvo.dbday}}</span>
										<h5>{{rvo.name}}</h5>
										<p>{{rvo.msg}}</p>
										<a class="a-btn" v-if="sessionId == rvo.id" @click="replyUpdate(rvo.no)">수정</a>
										<a class="a-btn active" v-if="sessionId == rvo.id" @click="replyDelete(rvo.no)">삭제</a>
									</div>
								</div>
							</li>
						</ol>
					</div>
					
					<!-- Leave A Comment -->
					<div class="leave-comment-area section_padding_50 clearfix" v-if="sessionId != ''">
						<div class="comment-form">
							<form action="#" method="post">
								<textarea ref="msg" v-model="msg" cols="95" rows="5" placeholder="Message"
									style="float: left;display: inline-block;"></textarea>
								<button type="button" class="btn-primary"
									style="float: left;width: 80px;height: 100px;display: inline-block;" @click="replyWrite()">댓글쓰기</button>
							</form>
						</div>
					</div>
				</div>
				<script>
					const commentApp = Vue.createApp({
						data() {
							return {
								list: [],
								count: 0,
								bno: '${vo.no}',
								sessionId: '${sessionScope.userid}',
								msg: ''
							}
						},
						mounted() {
							this.dataRecv()
						},
						methods: {
							dataRecv() {
								axios.get('/reply/list_vue', {
									params: {
										bno: this.bno
									}
								}).then(res => {
									console.log(res.data)
									this.list = res.data.list
									this.count = res.data.count
								})
							},
							replyWrite() {
								if (this.msg === '') {
									this.$refs.msg.focus()
									return
								}
								axios.post('/reply/insert_vue', {
									bno: this.bno,
									msg: this.msg,
								}).then(res => {
									console.log(res.data)
									this.list = res.data.list
									this.count = res.data.count
									this.msg = ''
								})
							},
							replyDelete(no) {
								axios.delete('/reply/delete_vue', {
									params: {
										bno: this.bno,
										no: no
									}
								}).then(res => {
									console.log(res.data)
									this.list = res.data.list
									this.count = res.data.count
								})
							}
						}
					})
					commentApp.mount('#comment')
				</script>
			</div>
		</div>
	</section>
</body>
</html>