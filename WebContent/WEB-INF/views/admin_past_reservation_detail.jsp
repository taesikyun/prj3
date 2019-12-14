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
	
	#hTitle{font-family: '고딕'; font-size: 30px; font-weight: bold; color: #F5F5F5;}
	.lList{font-family: '고딕'; font-size: 20px; font-weight: bold; color: #000000}
	
	label{height: 20px;}
	
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script type="text/javascript">
$(function () {
	
	$("#closeBtn").click(function(){
		history.back();
	});//click
	
});//ready
</script>
</head>
<body>
		<br/>
		<hr/>
		<div style="text-align: center; font-size: 23px; font-weight: bold;"> 이전 예약 상세창 </div>
		<hr/>
		<br/>
		
		<div style="float: left; width: 150px; min-height: 1000px; margin-left: 300px;">
			<label>예약번호</label>
				<br/>
				<br/>
			<label>아이디</label>
				<br/>
				<br/>
			<label>이름</label>
				<br/>
				<br/>
			<label>연락처</label>
				<br/>
				<br/>
			<label>이메일</label>
				<br/>
				<br/>
				<hr/>
			<label>방이름</label>
				<br/>
				<br/>
			<label>이용날짜</label>
				<br/>
				<br/>
			<label>이용시간</label>
				<br/>
				<br/>
			<label>요금</label>
				<br/>
				<br/>
				<hr/>
			<label>예약날짜</label>
				<br/>
				<br/>
			<label>결제상태</label>
				<br/>
				<br/>
			<label>결제수단</label>
				<br/>
				<br/>
			<label>결제날짜</label>
				<br/>
				<br/>
				<hr/>
			<label>요구사항</label>
				<br/>
				<br/>
		</div>
		
		<div style="width:350px; float: right; margin-right: 180px;">  
			<input type="text" value="${pastReservationDetail.reservation_num}" readonly="readonly"/>
				<br/>
				<br/>
			<input type="text" value="${pastReservationDetail.user_id}" readonly="readonly"/>
				<br/>
				<br/>
			<input type="text" value="${pastReservationDetail.name}" readonly="readonly"/>
				<br/>
				<br/>
			<input type="text" value="${pastReservationDetail.phone}" readonly="readonly"/>
				<br/>
				<br/>
			<input type="text" value="${pastReservationDetail.email}" readonly="readonly"/>
				<br/>
				<br/>
				<hr/>
			<input type="text" value="${pastReservationDetail.room_name}" readonly="readonly"/>
				<br/>
				<br/>
			<input type="text" value="${pastReservationDetail.year}-${pastReservationDetail.month}-${pastReservationDetail.day}" readonly="readonly"/>
				<br/>
				<br/>
			<input type="text" value="${pastReservationDetail.r_realtime }" readonly="readonly"/>
				<br/>
				<br/>
			<input type="text" value="${pastReservationDetail.charge}" readonly="readonly"/>
				<br/>
				<br/>
				<hr/>
			<input type="text" value="${pastReservationDetail.reservation_date}" readonly="readonly"/>
				<br/>
				<br/>
			<input type="text" value=
				<c:choose>
					<c:when test="${pastReservationDetail.pay_status == 'N'}">"예약 상태"</c:when>
					<c:when test="${pastReservationDetail.pay_status == 'D'}">"무통장입금 대기"</c:when>
					<c:when test="${pastReservationDetail.pay_status == 'Y'}">"입금 완료"</c:when>
				</c:choose>
		  readonly="readonly"/>
				<br/>
				<br/>
			<input type="text" value=
				<c:choose>
					<c:when test="${reservationDetail.pay_method eq null}">"지불수단 선택 대기"</c:when>
					<c:when test="${reservationDetail.pay_method == 'C'}">"카드 선택"	</c:when>
					<c:when test="${reservationDetail.pay_method == 'B'}">"무통장입금 선택"	</c:when>
				</c:choose>
		  readonly="readonly"/>
				<br/>
				<br/>
			<input type="text" value="${pastReservationDetail.pay_date}" readonly="readonly"/>
				<br/>
				<br/>
				<hr/>
			<textarea rows="10" cols="30" readonly="readonly"><c:out value="${pastReservationDetail.require}"/></textarea>
				<br/>
				<br/>
	  </div>
	  
  	<div style="width: 500px; margin: 0px auto; clear: both; text-align: center;">
	  	<input class="btn btn-secondary" type="button" value="목록으로" id="closeBtn">
  	</div>
				<br/>
				<br/>
</body>
</html>

















