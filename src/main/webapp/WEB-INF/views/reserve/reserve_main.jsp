<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
const SESSION_ID = '${ sessionScope.userid }'
const CNO = '${ param.cno }'
</script>
<style>
  .card{
    box-shadow:0 4px 10px rgba(0,0,0,0.05);
    border:none;
    border-radius:12px;
  }
  .card-header{A
    font-weight:700;
    font-size:1.1rem;
  }
  #food_list{
    height:600px;
    overflow-y:auto;
  }
  .food-item:hover{
    background-color:#f1f1f1;
    cursor:pointer;
  }
  img#food_poster{
    border-radius:8px;
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
						<h2>맛집 예약</h2>
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
				<div class="container my-4">
			    <div class="row g-4">
			      <!-- 맛집 정보 -->
			      <div class="col-md-4">
			        <div class="card h-100">
			          <div class="card-header bg-danger text-white text-center">맛집 정보</div>
			          <div class="card-body" id="food_list">
			           
			          </div>
			        </div>
			      </div>
			
			      <!-- 예약일 정보 -->
			      <div class="col-md-5">
			        <div class="card h-100">
			          <div class="card-header bg-info text-white text-center">예약일 정보</div>
			          <div class="card-body text-center" id="food_rdays">
			            
			          </div>
			        </div>
			      </div>
			
			      <!-- 예약 정보 -->
			      <div class="col-md-3">
			        <div class="card h-100">
			          <div class="card-header bg-success text-white text-center">예약 정보</div>
			          <div class="card-body text-center">
			            <img id="food_poster" src="" alt="poster" style="display:none">
			            <table class="table table-borderless text-start" style="display:none" id="reserve_info">
			              <tbody>
			                <tr><td class="text-muted">업체명</td><td id="food_name">-</td></tr>
			                <tr><td class="text-muted">예약일</td><td id="food_reserve_day">-</td></tr>
			                <tr><td class="text-muted">예약시간</td><td id="food_reserve_time">-</td></tr>
			                <tr><td class="text-muted">예약인원</td><td id="food_reserve_inwon">-</td></tr>
			              </tbody>
			            </table>
			            <form method="post" action="../reserve/reserve_insert.do" id="reserveBtn" style="display:none">
			              <input type="hidden" name="fno" id="rfno">
			              <input type="hidden" name="day" id="rdays">
			              <input type="hidden" name="time" id="rtime">
			              <input type="hidden" name="inwon" id="rinwon">
			              <button type="submit" class="btn btn-primary w-100">예약하기</button>
			            </form>
			          </div>
			        </div>
			      </div>
			    </div>
			
			    <div class="row g-4 mt-3">
			      <div class="col-md-8">
			        <div class="card">
			          <div class="card-header bg-primary text-white text-center">시간 정보</div>
			          <div class="card-body text-center">
			            <div class="d-flex justify-content-center gap-2 flex-wrap" id="reserve_time2">
			              
			            </div>
			          </div>
			        </div>
			      </div>
			
			      <div class="col-md-4">
			        <div class="card">
			          <div class="card-header bg-info text-white text-center">인원 정보</div>
			          <div class="card-body text-center">
			            <div class="d-flex justify-content-center gap-2 flex-wrap" id="reserve_inwon">
			              
			            </div>
			          </div>
			        </div>
			      </div>
			    </div>
			  </div>
			</div>
		</div>
	</section>

</body>
</html>