<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- ****** Breadcumb Area Start ****** -->
	<div class="breadcumb-area" style="background-image: url(/img/bg-img/breadcumb.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="bradcumb-title text-center">
						<h2>자유게시판</h2>
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
							<td class="text-left">
								<a href="insert" class="btn btn-sm btn-warning">새글</a>
							</td>
						</tr>
					</tbody>
				</table>
				<table class="table">
					<thead>
						<tr class="danger">
							<th width="10%" class="text-center">번호</th>
							<th width="45%" class="text-center">제목</th>
							<th width="15%" class="text-center">이름</th>
							<th width="20%" class="text-center">작성일</th>
							<th width="10%" class="text-center">조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="count" value="${count}"></c:set>
						<c:forEach var="vo" items="${list}">
							<tr>
								<td width="10%" class="text-center">${count}</td>
								<td width="45%" class="text-left">
									<%-- 상세보기 : primary key --%>
									<a href="detail?no=${vo.no}">${vo.subject}</a> &nbsp;
									<c:if test="${today == vo.dbday}">
										<sup><img src="/img/new.gif"></sup>
									</c:if>
									<c:if test="${vo.replycount != 0}">
										(${vo.replycount})
									</c:if>
								</td>
								<td width="15%" class="text-center">${vo.name}</td>
								<td width="20%" class="text-center">${vo.dbday}</td>
								<td width="10%" class="text-center">${vo.hit}</td>
							</tr>
							<c:set var="count" value="${count - 1}"></c:set>
						</c:forEach>
						<tr>
							<td class="text-center" colspan="5">
								<a href="list?page=${curpage > 1 ? curpage - 1 : curpage}" class="btn btn-sm btn-warning">이전</a>
								${curpage} page / ${totalpage} pages
								<a href="list?page=${curpage < totalpage ? curpage + 1 : curpage}" class="btn btn-sm btn-warning">다음</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</section>
</body>
</html>