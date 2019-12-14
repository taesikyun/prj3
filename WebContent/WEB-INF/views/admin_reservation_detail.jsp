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
	
	label{height: 20px;}
	
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
	
	$("#closeBtn").click(function(){
		history.back();
	});//click
	
	
	$("#confirm").click(function(){
		if($("#pay_method").val() != 'B'){
			alert("결제 방법이 무통장 입금일 경우에만 사용할 수 있습니다.")
			return;
		}//end if
		
		if($("#pay_status").val() == 'N'){
			alert("아직 예약만 한상태입니다.")
			return;
		}//end if
		
		if($("#pay_status").val() == 'Y'){
			alert("결제가 이미 완료되었습니다.")
			return;
		}//end if
		
		if(confirm("입금이 확인되었습니까?\n확인을 누르시면 결제상태가 변경됩니다.")){
			var data="reservation_num=" + $("#reservation_num").val();
			var href="move_reservation_detail_form.do?reservation_num=" + $("#reservation_num").val();
			/* alert(data)
			alert(href) */
			
			$.ajax({
				url: "pay_status_modify_process.do",
				type: "post",
				data: data,
				
				dataType: "json",
				error:function(xhr){
					alert("문제발생\n" + xhr.status + "\n" + xhr.statusText);
				},
				success: function(json){
					if(json.result){
						alert("변경되었습니다.");
						location.href=href;
					}else{
						alert("죄송합니다.");
					}//end if
				}
				
			})//ajax
		}//confirm
		
	});//click
	
	
	
	
});//ready
</script>
</head>
<body>
<div id="class4wrap">
	<!-- 900(w) x 1100(h) -->
	
	<div id="header">
	
		<div id="hLogo">
			<a href="admin_main.do"><img src="http://localhost:8080/zz_prj3/images/header_logo.png" title="Home"/></a>
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
		
		<div style="text-align: center; font-size: 23px; font-weight: bold;"> 상세 예약 현황 </div>
		<br/>
		<br/>
		
		<div style="float: left; width: 150px; min-height: 1000px; margin-left: 350px;">
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
		
		<div style="width:700px; float: right; margin-right: 50px;">
			<input type="text" id="reservation_num" value="${reservationDetail.reservation_num}" readonly="readonly"/>
				<br/>
				<br/>
			<input type="text" value="${reservationDetail.user_id}" readonly="readonly"/>
				<br/>
				<br/>
			<input type="text" value="${reservationDetail.name}" readonly="readonly"/>
				<br/>
				<br/>
			<input type="text" value="${reservationDetail.phone}" readonly="readonly"/>
				<br/>
				<br/>
			<input type="text" value="${reservationDetail.email}" readonly="readonly"/>
				<br/>
				<br/>
				<hr/>
			<input type="text" value="${reservationDetail.room_name}" readonly="readonly"/>
				<br/>
				<br/>
			<input type="text" value="${reservationDetail.year}-${reservationDetail.month}-${reservationDetail.day}" readonly="readonly"/>
				<br/>
				<br/>
			<select style="height: 28px;">
				<c:forEach var="realtime" items="${realtimeList}">		
					<option><c:out value="${realtime.r_realtime}"/></option>
				</c:forEach>
			</select>
				<br/>
				<br/>
			<input type="text" value="${reservationDetail.charge}" readonly="readonly"/>
				<br/>
				<br/>
				<hr/>
			<input type="text" value="${reservationDetail.reservation_date}" readonly="readonly"/>
				<br/>
				<br/>
				<input type="text"  value=
				<c:choose>
				<c:when test="${reservationDetail.pay_status == 'N'}">
					"예약 상태"
				</c:when>
				<c:when test="${reservationDetail.pay_status == 'D'}">
					"무통장입금 대기"
				</c:when>
				<c:when test="${reservationDetail.pay_status == 'Y'}">
					"입금 완료"
				</c:when>
			</c:choose>
				 readonly="readonly"/><input type="hidden" id="pay_status" value="${reservationDetail.pay_status}"/>
			<input type="button" class="btn btn-secondary btn-sm"  id="confirm" value="입금확인"/>
				<br/>
				<br/>
			<input type="text" value=
			<c:choose>
				<c:when test="${reservationDetail.pay_method eq null}">
					"지불수단 선택 대기"
				</c:when>
				<c:when test="${reservationDetail.pay_method == 'C'}">
					"카드 선택"
				</c:when>
				<c:when test="${reservationDetail.pay_method == 'B'}">
					"무통장입금 선택"
				</c:when>
			</c:choose>
			 readonly="readonly"/><input type="hidden" id="pay_method" value="${reservationDetail.pay_method}"/>
				<br/>
				<br/>
			<input type="text" value="${reservationDetail.pay_date}" readonly="readonly"/>
				<br/>
				<br/>
				<hr/>
			<textarea rows="10" cols="60" readonly="readonly"><c:out value="${reservationDetail.require}"/></textarea>
				<br/>
				<br/>
	  </div>
	  
  	<div style="width: 1250px; margin: 0px auto; clear: both; text-align: center;">
	  	<input class="btn btn-secondary" type="button" value="닫기" id="closeBtn">
  	</div>
	</div>
	<div id="footer">
	<!-- 900(w) x 150(h) -->
		<div id="fLogo">
			<img src="http://localhost:8080/zz_prj3/common/images/f_logo.png"/>
		</div>
		<div id="fContent">
			&copy;CopyRight. AllRight Reserved. Class 4<br/>
			서울시 강남구 역삼동 한독빌딩 8층 4강의실.
		</div>
	</div>
</div>
</body>
</html>

















