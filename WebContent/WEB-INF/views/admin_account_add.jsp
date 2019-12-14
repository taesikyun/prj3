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
	#wrap{ position:relative ;margin: 0px auto; width: 350px; height: 230px;}
	#frm{ position:absolute ;top: 50px;left:30px ; }

</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script type="text/javascript">
$(function () {
	$("#bank").focus();

	$("#logout").click(function(){
		if(confirm("로그아웃 하시겠습니까?")){
			location.replace('admin_logout.do')
		}//end if
		
	});//click
	
	$("#insertBtn").click(function(){
		
		if($("#bank").val().trim() == ""){
			alert("은행명을 입력해주세요.");
			$("#bank").focus();
			return;
		}//end if
			
		if($("#account_number").val().trim() == ""){
			alert("계좌번호를 입력해주세요.");
			$("#account").focus();
			return;
		}//end if.
		
		var regexp = /^[0-9]*$/
		v = $("#account_number").val();
		if( !regexp.test(v) ) {
			alert("숫자만 입력하세요");
			return;
		}
		
		var formData = new FormData(document.getElementById('accountFrm'));
		$.ajax({
			url: "add_account_process.do",
			data: formData,
			type: "post",
			processData: false,
			contentType: false,
			
			dataType: "json",
			error: function(xhr){
				alert("문제발생\n" + xhr.status + "\n" + xhr.statusText);
			},
			success: function(json){
				if(json.result == true){
					alert("추가되었습니다.");
					location.href = "admin_account.do";
				}else{
					alert("죄송합니다");
				}//end else
			}
		});//ajax
			
	});//click
	
	$("#cancelBtn").click(function(){
		if(confirm("목록으로 돌아가시겠습니까?\n작성하신 내용은 사라집니다.")){
			location.replace('admin_account.do');
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
		
		<div style="text-align: center; font-size: 23px; font-weight: bold;">	계좌 추가	</div>
		<br/>
		<br/>
		
		<div id="wrap"> 
			<div id="frm"> 
				<form id="accountFrm" name="accountFrm">
					<span style="width: 70px; display: inline-block; text-align: center;">은행명</span>
					<input type="text" id="bank" name="bank" class="inputBox"/>
						<br/>
						<br/>
					<span style="width: 70px; display: inline-block; text-align: center;">계좌번호</span>
					<input type="text" id="account_number" name="account_number" class="inputBox"/>
						<br/>
						<br/>
					<input type="button" value="추가"  class="btn btn-dark"  id="insertBtn" style="margin-left: 80px"/>
					<input type="button" value="취소"  class="btn btn-dark"  id="cancelBtn" style="margin-left: 10px"/>
				</form>
			</div>
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

















