<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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
				<td class="text-center"><h3>공지 목록</h3></td>
			</tr>
		</tbody>
	</table>
	<table class="table">
		<tbody>
			<tr>
				<td>
					<a href="notice-insert" class="btn btn-sm btn-danger">등록</a>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="table">
		<thead>
			<tr class="success">
				<th class="text-center" width="10%">번호</th>
				<th class="text-center" width="45%">제목</th>
				<th class="text-center" width="15%">이름</th>
				<th class="text-center" width="20%">작성일</th>
				<th class="text-center" width="10%">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="count" value="${ count }"/>
				<c:forEach var="vo" items="${ list }">
					<tr>
						<td class="text-center" width="10%">${ count }</td>
						<td width="45%"><a href="notice-detail?no=${ vo.no }">[${ vo.type }]&nbsp;${ vo.subject }</a></td>
						<td class="text-center" width="15%">${ vo.name }</td>
						<td class="text-center" width="20%">${ vo.dbday }</td>
						<td class="text-center" width="10%">${ vo.hit }</td>
					</tr>
				<c:set var="count" value="${ count - 1 }"/>
			</c:forEach>
			<tr>
				<td colspan="5" class="text-center">
					<a href="#" class="btn btn-sm btn-primary">이전</a>
					${ curpage } page / ${ totalpage } pages
					<a href="#" class="btn btn-sm btn-primary">다음</a>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>