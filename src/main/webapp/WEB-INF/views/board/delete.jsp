<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
						<h2>삭제하기</h2>
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
			<div class="row text-center" style="width: 400px; margin: 0 auto">
				<form method="post" action="delete_ok">
					<table class="table">
						<tbody>
							<tr>
								<td class="text-center">
									비밀번호:<input type="password" size="15" name="pwd" class="input-sm">
									<input type="hidden" name="no" value="${no}">
								</td>
							</tr>
							<tr>
								<td class="text-center">
									<button class="btn-sm btn-danger" type="submit">삭제</button>
									<button class="btn-sm btn-success" type="button" onclick="javascript:history.back()">취소</button>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</section>
</body>
</html>