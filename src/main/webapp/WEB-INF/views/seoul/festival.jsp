<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesSheet" href="/css/map.css" type="text/css">
</head>
<body>
	<!-- ****** Breadcumb Area Start ****** -->
	<div class="breadcumb-area" style="background-image: url(/img/bg-img/breadcumb.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="bradcumb-title text-center">
						<h2>${vo.title}</h2>
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
							<td width="30%" class="text-center" rowspan="7">
								<img src="${vo.image1}" style="width: 100%; height: 320px">
							</td>
							<td colspan="2"><h3>${vo.title}</h3></td>
						</tr>
						<tr>
							<td width="15%" class="text-center">주소</td>
							<td width="55%">${vo.address}</td>
						</tr>
						<tr>
							<td width="15%" class="text-center">사용시간</td>
							<td width="55%">${vo.fvo.usetime}</td>
						</tr>
						<tr>
							<td width="15%" class="text-center">소요시간</td>
							<td width="55%">${vo.fvo.spendtime}</td>
						</tr>
						<tr>
							<td width="15%" class="text-center">이벤트</td>
							<td width="55%">${vo.fvo.eventstartdate} ~ ${vo.fvo.eventenddate}</td>
						</tr>
						<tr>
							<td width="15%" class="text-center">상영시간</td>
							<td width="55%">${vo.fvo.playtime}</td>
						</tr>
						<tr>
							<td width="15%" class="text-center">연령제한</td>
							<td width="55%">${vo.fvo.agelimit}</td>
						</tr>
					</tbody>
				</table>
				<table class="table">
					<tbody>
						<tr>
							<td>${vo.fvo.msg}</td>
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
			                    키워드 : <input type="text" value="강남구 맛집" id="keyword" size="15"> 
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
			</div>
		</div>
	</section>
</body>
</html>