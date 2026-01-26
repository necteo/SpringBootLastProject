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
	<!-- ****** Categories Area Start ****** -->
	<section class="categories_area clearfix" id="about">
		<div class="container">
			<div class="row">
				<div class="col-12 col-md-6 col-lg-4">
					<div class="single_catagory wow fadeInUp" data-wow-delay=".3s">
						<img src="/img/seoul.jpg" style="width: 330px; height: 238px">
						<div class="catagory-title">
							<a href="#">
								<h5>서울여행코스</h5>
							</a>
						</div>
					</div>
				</div>
				<div class="col-12 col-md-6 col-lg-4">
					<div class="single_catagory wow fadeInUp" data-wow-delay=".6s">
						<img src="/img/busan.jpg" style="width: 330px; height: 238px">
						<div class="catagory-title">
							<a href="#">
								<h5>부산여행코스</h5>
							</a>
						</div>
					</div>
				</div>
				<div class="col-12 col-md-6 col-lg-4">
					<div class="single_catagory wow fadeInUp" data-wow-delay=".9s">
						<img src="/img/jeju.jpg" style="width: 330px; height: 238px">
						<div class="catagory-title">
							<a href="#">
								<h5>제주여행코스</h5>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- ****** Categories Area End ****** -->

	<!-- ****** Blog Area Start ****** -->
	<section class="blog_area section_padding_0_80">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-12 col-lg-8">
					<div class="row">

						<!-- Single Post -->
						<div class="col-12">
							<div class="single-post wow fadeInUp" data-wow-delay=".2s">
								<!-- Post Thumb -->
								<div class="post-thumb">
									<embed src="http://youtube.com/embed/hZObjTVVQSE\u0026pp=YAHIAQHwAQG6AwIYAugFAaIGFQFfVp7j1eODHTLoEH0RKc5j727PBZAHAg%3D%3D"
										style="width: 700px; height: 450px">
								</div>
								<!-- Post Content -->
								<div class="post-content">
									<div class="post-meta d-flex">
										<div class="post-author-date-area d-flex">
											<!-- Post Author -->
											<div class="post-author">
												<a href="#">By Marian</a>
											</div>
											<!-- Post Date -->
											<div class="post-date">
												<a href="#">May 19, 2017</a>
											</div>
										</div>
										<!-- Post Comment & Share Area -->
										<div class="post-comment-share-area d-flex">
											<!-- Post Favourite -->
											<div class="post-favourite">
												<a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> 10</a>
											</div>
											<!-- Post Comments -->
											<div class="post-comments">
												<a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 12</a>
											</div>
											<!-- Post Share -->
											<div class="post-share">
												<a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
											</div>
										</div>
									</div>
									<a href="#">
										<h2 class="post-headline">Boil The Kettle And Make A Cup Of Tea Folks, This Is Going To Be A Big
											One!</h2>
									</a>
									<p>Tiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
										exercitation ullamco laboris nisi ut aliquip ex ea commodoconsequat.</p>
									<a href="#" class="read-more">Continue Reading..</a>
								</div>
							</div>
						</div>

						<!-- Single Post -->
						<c:forEach var="vo" items="${jList}">
							<div class="col-12 col-md-6">
								<div class="single-post wow fadeInUp" data-wow-delay=".4s">
									<!-- Post Thumb -->
									<div class="post-thumb">
										<img src="${vo.image1}">
									</div>
									<!-- Post Content -->
									<div class="post-content">
										<div class="post-meta d-flex">
											<div class="post-author-date-area d-flex">
												<!-- Post Author -->
												<div class="post-author">
													<a href="#">${vo.address}</a>
												</div>
												<!-- Post Date -->
												<div class="post-date">
													<a href="#">${vo.hit}</a>
												</div>
											</div>
											<!-- Post Comment & Share Area -->
											<div class="post-comment-share-area d-flex">
												<!-- Post Favourite -->
												<div class="post-favourite">
													<a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> 10</a>
												</div>
												<!-- Post Comments -->
												<div class="post-comments">
													<a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 12</a>
												</div>
												<!-- Post Share -->
												<div class="post-share">
													<a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
												</div>
											</div>
										</div>
										<a href="#">
											<h4 class="post-headline">${vo.title}</h4>
										</a>
									</div>
								</div>
							</div>
						</c:forEach>

						<!-- ******* List Blog Area Start ******* -->

						<!-- Single Post -->
						<c:forEach var="vo" items="${bList}">
							<div class="col-12">
								<div class="list-blog single-post d-sm-flex wow fadeInUpBig" data-wow-delay=".2s">
									<!-- Post Thumb -->
									<div class="post-thumb">
										<img src="${vo.image1}">
									</div>
									<!-- Post Content -->
									<div class="post-content">
										<div class="post-meta d-flex">
											<div class="post-author-date-area d-flex">
												<!-- Post Author -->
												<div class="post-author">
													<a href="#">${vo.address}</a>
	
												</div>
												<!-- Post Date -->
												<div class="post-date">
													<a href="#">${vo.hit}</a>
												</div>
											</div>
											<!-- Post Comment & Share Area -->
											<div class="post-comment-share-area d-flex">
												<!-- Post Favourite -->
												<div class="post-favourite">
													<a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> 10</a>
												</div>
												<!-- Post Comments -->
												<div class="post-comments">
													<a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 12</a>
												</div>
												<!-- Post Share -->
												<div class="post-share">
													<a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
												</div>
											</div>
										</div>
										<a href="#">
											<h4 class="post-headline">${vo.title}</h4>
										</a>
										<p>사용시간: ${vo.avo.usetime}</p>
										<p>휴무일: ${vo.avo.restdate}</p>
										<p>주차: ${vo.avo.parking}</p>
										<a href="#" class="read-more">Continue Reading..</a>
									</div>
								</div>
							</div>
						</c:forEach>

					</div>
				</div>

				<!-- ****** Blog Sidebar ****** -->
				<div class="col-12 col-sm-8 col-md-6 col-lg-4">
					<div class="blog-sidebar mt-5 mt-lg-0">
						<!-- Single Widget Area -->
						<div class="single-widget-area about-me-widget text-center">
							<div class="widget-title">
								<h6>About Me</h6>
							</div>
							<div class="about-me-widget-thumb">
								<img src="/img/about-img/1.jpg" alt="">
							</div>
							<h4 class="font-shadow-into-light">Shopia Bernard</h4>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt</p>
						</div>

						<!-- Single Widget Area -->
						<div class="single-widget-area popular-post-widget">
							<div class="widget-title text-center">
								<h6>서울 인기 맛집</h6>
							</div>
							<!-- Single Popular Post -->
							<c:forEach var="vo" items="${sList}">
							<div class="single-populer-post d-flex">
								<img src="${vo.image1}">
								<div class="post-content">
									<a href="#">
										<h6>${vo.title}</h6>
									</a>
									<p>${vo.address}</p>
								</div>
							</div>
							</c:forEach>
						</div>

						<!-- Single Widget Area -->
						<div class="single-widget-area newsletter-widget">
							<div class="widget-title text-center">
								<h6>실시간 인기 검색어</h6>
							</div>
              <c:forEach var="rvo" items="${rList }" varStatus="s">
	              <p>${s.index+1}.${rvo.word}</p>
              </c:forEach>
                            
							<%--<div class="newsletter-form">
								<form action="#" method="post">
									<input type="email" name="newsletter-email" id="email" placeholder="Your email">
									<button type="submit"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
								</form>
							</div>--%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- ****** Blog Area End ****** -->
</body>

</html>