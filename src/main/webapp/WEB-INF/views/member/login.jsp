<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
<!-- ****** Breadcumb Area Start ****** -->
  <div class="breadcumb-area" style="background-image: url(/img/bg-img/breadcumb.jpg);">
    <div class="container h-100">
      <div class="row h-100 align-items-center">
        <div class="col-12">
          <div class="bradcumb-title text-center">
            <h2>로그인</h2>
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
              <%-- 검색기 --%>
            </ol>
          </nav>
        </div>
      </div>
    </div>
  </div>
  <!-- ****** Breadcumb Area End ****** -->

  <!-- ****** Archive Area Start ****** -->
  <section class="archive-area section_padding_80" id="join_section">
    <div class="container" style="max-width: 800px; margin-top: 40px;">
    	<div class="row justify-content-center">
    		<form action="/member/login_process" method="post">
	    		<table class="table">
	    			<tbody>
	    				<tr>
	    					<th class="text-center" width="20%">ID</th>
	    					<td width="80%">
	    						<input type="text" size="20" class="input-sm" required name="userid">
	    					</td>
	    				</tr>
	    				<tr>
	    					<th class="text-center" width="20%">Password</th>
	    					<td width="80%">
	    						<input type="password" size="20" class="input-sm" required name="userpwd">
	    					</td>
	    				</tr>
	    				<tr>
	    					<td colspan="2">
	    						<input type="checkbox" name="remember-me"> 자동로그인
	    					</td>
	    				</tr>
	    				<tr>
	    					<td colspan="2" style="color: red">
	    						${message}
	    					</td>
	    				</tr>
	    				<tr>
	    					<td colspan="2" class="text-center">
	    						<button class="btn-sm btn-warning" type="submit">로그인</button>
	    						<button class="btn-sm btn-warning" type="button" onclick="javascript:history.back()">취소</button>
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