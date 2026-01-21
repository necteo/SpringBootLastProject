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
				<td class="text-center"><h3>공지 내용</h3></td>
			</tr>
		</tbody>
	</table>
	<table class="table">
		<tbody>
			<tr>
				<th width="20%" class="text-center success">번호</th>
				<td width="30%" class="text-center">${ vo.no }</td>
				<th width="20%" class="text-center success">작성일</th>
				<td width="30%" class="text-center">${ vo.regdate }</td>
			</tr>
			<tr>
				<th width="20%" class="text-center success">이름</th>
				<td width="30%" class="text-center">${ vo.name }</td>
				<th width="20%" class="text-center success">조회수</th>
				<td width="30%" class="text-center">${ vo.hit }</td>
			</tr>
			<tr>
				<th width="20%" class="text-center success">제목</th>
				<td colspan="3">${ vo.subject }</td>
			</tr>
			<tr>
				<td colspan="4" class="text-left" valign="top" height="200">
					<pre style="white-space: pre-wrap; background-color: white; border: none">${ vo.content }</pre>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="text-right">
					<a href="notice-update?no=${ vo.no }" class="btn btn-sm btn-warning">수정</a>
					<a href="notice-delete?no=${ vo.no }" class="btn btn-sm btn-warning">삭제</a>
					<a href="notice-list" class="btn btn-sm btn-warning">목록</a>
				</td>
			</tr>
			<c:if test="${ vo.filecount > 0 }">
				<c:forTokens items="${ vo.filename }" delims="," var="img">
					<tr>
						<td class="text-center" colspan="4">
							<img src="/upload/${ img }" style="width: 600px; height: 350px">
						</td>
					</tr>
				</c:forTokens>
			</c:if>
		</tbody>
	</table>
</body>
</html>