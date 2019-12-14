<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info=""
 %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
 if(session.getAttribute("admin_id") == null){
		response.sendRedirect("admin_login_form.do");
		
	}//end if
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자</title>
<link rel="stylesheet" type="text/css" href="http://211.63.89.153:8080/zz_prj3/common/css/main.css">
<style type="text/css">

	#class4wrap{width: 1400px; height: 1100px; margin: 0px auto;}
	/* 헤더 시작 */
	#header{width: 1400px; height: 100px; position: relative; background: #000000;  }
	#hLogo{width: 84px; height: 84px; position: absolute; top: 10px; left: 40px}
	#hContent{width: 1400px; height: 90px; padding-top:10px;  position: absolute; top: 0px; left: 200px;}
	/* 헤더 끝 */

	/* 사이드 시작 */
	#leftSide{width: 150px; min-height: 800px; background-color: #F5F5F5 ; float: left;}
	/* 사이드 끝 */
	
	/* 컨테이너 시작 */
	#container{width: 1250px; min-height: 800px; float: right;}
	/* 컨테이너 끝 */
	
	/* 푸터 시작 */
	#footer{clear:both; width: 1400px; height: 150px; background-color: #fafafa; }
	#fLogo{width: 180px; height: 130px; padding-top: 20px; padding-left:20px;  float: left;}
	#fContent{width: 670px; height: 110px; padding-top: 40px; padding-left:30px; float: right;}
	/* 푸터 끝 */
	
	#hTitle{font-family: '고딕'; font-size: 30px; font-weight: bold; color: #F5F5F5;}
	.lList{font-family: '고딕'; font-size: 20px; font-weight: bold; color: #000000}
	
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script type="text/javascript">
$(function () {
	$("#logout").click(function(){
		if(confirm("로그아웃 하시겠습니까?")){
			location.replace('admin_logout.do')
		}//end if
		
	});//click
	
	$(".id").click(function(){
		location.href="admin_reservation_detail.jsp";
		
	});//click
	
	$("#searchBtn").click(function(){
		if($("#keyword").val().trim() == ""){
			alert("검색어를 입력해주세요.");
			$("#keyword").focus();
			return;
		}//end if	
		
		$("#search").submit();
		
	});//click
	
});//ready
function pastReservation(){
	window.open("admin_reservation_past.do", "pastReservation", "width=1000,height=700,top=150,left=500");
}

</script>
</head>
<body>
<div id="class4wrap">
	<!-- 900(w) x 1100(h) -->
	
	<div id="header">
	
		<div id="hLogo">
			<a href="admin_main.do"><img src="http://211.63.89.153:8080/zz_prj3/images/header_logo.png" title="Home"/></a>
		</div>
		<div id="hContent">
			<div id="hTitle">관리자 페이지 </div><div style="font-size: 20px; color: #4799CF; font-weight: bold;">사이트이름</div>
		</div>
		
	</div>
	
	<div id="leftSide">
	<!-- leftSide.jsp -->
		<c:import url="../../common/jsp/leftSide.jsp"/>
	<!-- leftSide.jsp -->
	</div>
	
	<div id="container">
		
		<div style="text-align: right; padding-top: 20px;">
			<input class="btn btn-secondary btn-sm" type="button" id="logout" name="logout" value="로그아웃"/>
		</div>
		
		<div style="text-align: center; font-size: 23px; font-weight: bold;">	예약 관리	</div>
		<br/>
		<br/>
		
		<div style="margin: 0px auto; width: 800px">
			<a href="javascript:pastReservation()">이전 예약 확인</a>
			<table class="table table-hover" id="memberTab">
				<thead>
					<tr>
						<th>예약번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>예약한 룸</th>
						<th>이용 날짜</th>
						<th>결재 여부</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="reservationData" items="${reservation_list }">
					<tr onclick="location.href='move_reservation_detail_form.do?reservation_num=${reservationData.reservation_num}'">
						<td><c:out value="${reservationData.reservation_num }"/></td>
						<td><c:out value="${reservationData.user_id }"/></td>
						<td><c:out value="${reservationData.name }"/></td>
						<td><c:out value="${reservationData.room_name }"/></td>
						<td><c:out value="${reservationData.year }-${reservationData.month }-${reservationData.day }"/></td>
						<td>
							<c:choose>
								<c:when test="${reservationData.pay_status == 'N'}">
									<c:out value="예약 상태"/>
								</c:when>
								<c:when test="${reservationData.pay_status == 'D'}">
									<c:out value="무통장입금 대기"/>
								</c:when>
								<c:when test="${reservationData.pay_status == 'Y'}">
									<c:out value="입금 완료"/>
								</c:when>
							</c:choose>
				 		</td>
					</tr>
				</c:forEach>
				<c:if test="${ empty reservation_list }">
					<tr>
						<td colspan="6">예약이 존재하지 않습니다.</td>
					</tr>
				</c:if>
				</tbody>
			</table>
			<div style="width: 800px;text-align: center;">
				<form class="form-inline" id="search" name="search" action="admin_reservation.do" method="get">
					<div style="margin: 0px auto; width: 800px; text-align: center;">
						<select class="form-control" id="field" name="field">
							<option value="user_id" ${param.field eq 'user_id'?" selected":""}>아이디</option>
							<option value="name" ${param.field eq 'name'?" selected":""}>이름</option>
						</select>
						<input type="text" class="form-control" id="keyword" name="keyword" value="${param.keyword}"/>
						<input type="button" class="btn btn-primary" value="검색" id="searchBtn"/>
					</div>
				</form>
			</div>
			<br/>
			<div class="indexList">
				<div class="in">
					<nav aria-label="Page navigation example">
					  <ul class="pagination">
							<c:out value="${indexList}" escapeXml="false"/>
					  </ul>
					</nav>
				</div>
			</div>
		</div>
		
	</div>
	<div id="footer">
	<!-- 900(w) x 150(h) -->
		<div id="fLogo">
			<img src="http://211.63.89.153:8080/zz_prj3/common/images/f_logo.png"/>
		</div>
		<div id="fContent">
			&copy;CopyRight. AllRight Reserved. Class 4<br/>
			서울시 강남구 역삼동 한독빌딩 8층 4강의실.
		</div>
	</div>	
</div>
</body>
</html>






