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
<link rel="stylesheet" type="text/css" href="http://localhost:8080/zz_prj3/common/css/main.css">
<style type="text/css">

	#hTitle{font-family: '고딕'; font-size: 30px; font-weight: bold; color: #F5F5F5;}
	.lList{font-family: '고딕'; font-size: 20px; font-weight: bold; color: #000000}

</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script type="text/javascript">
$(function () {
	$("#searchBtn").click(function(){
		if($("#keyword").val().trim() == ""){
			alert("검색어를 입력해주세요.");
			$("#keyword").focus();
			return;
		}//end if	
		
		$("#search").submit();
		
	});//click
	
	$("#popupClose").click(function(){
		
		self.close();
		
	});//click
	
	
	
});//ready

function popupDetail(reservation_num, r_realtime){
	var url="move_past_reservation_detail_form.do?reservation_num="+reservation_num+"&r_realtime="+r_realtime;
	window.open(url, "_self", "width=1000,height=700,top=300,left=500");
}

</script>
</head>
<body>
		
		<br/>
		<hr/>
		<div style="text-align: center; font-size: 23px; font-weight: bold;">	지난 예약 관리	</div>
		<hr/>
		<br/>
		
		<div style="margin: 0px auto; width: 800px">
			<table class="table table-hover" id="memberTab">
				<thead>
					<tr>
						<th>예약번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>예약한 룸</th>
						<th>이용 날짜</th>
						<th>이용 시간대</th>
						<th>결재 여부</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="data" items="${pastReservation }">
					<c:set var="reservation_num" value="${data.reservation_num }"/>
					<c:set var="r_realtime" value="${data.r_realtime }"/>
					<tr onclick="javascript:popupDetail('${reservation_num}','${r_realtime}')">
						<td><c:out value="${data.reservation_num }"/></td>
						<td><c:out value="${data.user_id }"/></td>
						<td><c:out value="${data.name }"/></td>
						<td><c:out value="${data.room_name }"/></td>
						<td><c:out value="${data.year }-${data.month }-${data.day }"/></td>
						<td><c:out value="${data.r_realtime }"/></td>
						<td><c:out value="${data.pay_status == 'N'?'결제미완료':'결제완료'}"/></td>
					</tr>
				</c:forEach>
				<c:if test="${ empty pastReservation }">
					<tr>
						<td colspan="7">지난 예약이 존재하지 않습니다.</td>
					</tr>
				</c:if>
				</tbody>
			</table>
			<div style="width: 800px;text-align: center;">
				<form class="form-inline" id="search" name="search" action="admin_reservation_past.do" method="get">
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
			<div id="boardIndexList">
				<div style="margin:0px auto; width:100px;">
					<nav aria-label="Page navigation example">
					  <ul class="pagination">
							<c:out value="${indexList}" escapeXml="false"/>
					  </ul>
					</nav>
				</div>
			</div>
			<div style="margin: 0px auto; width: 800px; text-align: center;">
				<input type="button" value="닫기" class="btn btn-primary" id="popupClose"/>
			</div>
		</div>
</body>
</html>

