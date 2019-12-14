<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info=""
 %>
 
 <%
 	if(session.getAttribute("admin_id") == null){
 		response.sendRedirect("admin_login_form.do");
 		
 	}//end if
 %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	/* 컨테이너 내부 시작 */
	#cont_left{width: 625px; min-height: 800px; float: left; padding: 20px;}
	#cont_right{width: 623px; min-height: 800px; float: right; padding: 20px;}
	/* 컨테이너 내부 끝 */
	
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
			location.replace("admin_logout.do");
		}//end if
		
	});//click
	
});//ready
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
			<div id="hTitle">관리자 페이지 </div><div style="font-size: 20px; color: #4799CF; font-weight: bold;">[:P]</div>
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
		
		<div id="cont_left">
				<div style="float: left; font-size: 23px; font-weight: bold;">
					예약현황
				</div>
				<div style="width: 100px; text-align: center; float: right;">
					<a href="admin_reservation.do">more</a>
				</div>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>예약번호</th>
							<th>룸 종류</th>
							<th>예약자</th>
							<th>예약시간</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="reservationData" items="${reservationView}">
							<tr onclick="location.href='move_reservation_detail_form.do?reservation_num=${reservationData.reservation_num}'">
								<td><c:out value="${reservationData.reservation_num}"/></td>
								<td><c:out value="${reservationData.room_name}"/></td>
								<td><c:out value="${reservationData.name}"/></td>
								<td><c:out value="${reservationData.r_realtime}"/></td>
							</tr>
						</c:forEach>
						<c:if test="${empty reservationView}">
							<tr>
								<td colspan="4">금일 예약된 룸이 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
		</div>
		
		<div id="cont_right">
			<div style="margin-top: 33px;">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>문의게시판</th>
							<th>ID</th>
							<th>답변여부</th>
							<th style="text-align: center;"><a href="qna_list.do">more</a></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="qnaData" items="${qnaView}">
							<tr onclick="location.href='qna_post.do?q_num=${qnaData.q_num}'">
								<td width="300px"><c:out value="${ qnaData.q_subject}"/></td>
								<td><c:out value="${ qnaData.user_id}"/></td>
								<td><c:out value="${ qnaData.q_answer_flag == 'N'?'미완료':'완료'}"/></td>
								<td><c:out value="${ qnaData.q_input_date}"/></td>
							</tr>
						</c:forEach>
						<c:if test="${empty qnaView}">
							<tr>
								<td colspan="4">공지글이 없습니다.</td>
							</tr>
						</c:if>	
					</tbody>
				</table>
			</div>
			
			
			<div style="margin-top: 30px;">
				<table class="table table-hover">
					<thead>
						<tr>
							<th colspan="2">공지사항</th>
							<th style="text-align: center;"><a href="notice_list.do">more</a></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="noticeData" items="${noticeView}">
						<tr onclick="location.href='notice_post.do?n_num=${noticeData.n_num}'">
							<td width="350px"><c:out value="${noticeData.n_subject}"/></td>
							<td>관리자</td>
							<td><c:out value="${noticeData.n_input_date}"/></td>
						</tr>
						</c:forEach>
						<c:if test="${empty noticeView}">
							<tr>
								<td colspan="3">문의글이 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
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

















