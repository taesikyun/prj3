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
			location.replace('admin_logout.do');
		}//end if
		
	});//click
	
	$("#warning_code").change(function(){
		if($(this).val() != "선택"){
			$("#warning_type").val($(this).val());
		}else{
			$("#warning_type").val("");
		}//end else
	});//change
	
/* 	$("#category").change(function(){
		if($(this).val() != "선택"){
			if($(this).val()!="기타"){
				$("#warning_type").val($(this).val());
				$("#warning_type").attr('readonly', 'readonly');
			}else{
				$("#warning_type").removeAttr('readonly');
				$("#warning_type").val("");
				$("#warning_type").focus();
			}//end else
		}//end if
	});//change
	 */
	$("#insertBtn").click(function(){
		
		if($("#warning_type").val().trim() == ""){
			alert("카테고리를 선택해주세요.");
			$("#warning_type").focus();
			return;
		}//end if
		
		if($("#warning_content").val().trim() == ""){
			alert("내용을 입력해주세요.");
			$("#warning_type").focus();
			return;
		}//end if
		
		if(confirm("경고를 추가하시겠습니까?")){
			var formData = new FormData(document.getElementById('addFrm'));
			
			$.ajax({
				url: "add_blacklist_process.do",
				processData: false,
				contentType: false,
				data: formData,
				type: "post",
				
				dataType: "json",
				error: function(xhr){
					alert("문제발생\n" + xhr.status + "\n" + xhr.statusText);
				},
				success: function(json){
					if(json.result == true){
						alert("추가하였습니다.");
						location.href="admin_member.do";
					}else{
						alert("죄송합니다.");
					}//end if
				}
				
			});//ajax
			
		}//end if
		
	});//click
	
	$("#cancelBtn").click(function(){
		if(confirm("작성을 취소하시겠습니까?")){
			location.replace('admin_member.do');
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
		
		<div style="text-align: center; font-size: 23px; font-weight: bold;"> 블랙리스트 추가 </div>
		<br/>
		<br/>
		
		<div style="float: left; width: 150px; min-height: 500px; margin-left: 250px;">
			<label>아이디</label>
				<br/>
				<br/>
			<label>카테고리</label>
				<br/>
				<br/>
			<label>내용</label>
				<br/>
				<br/>
		</div>
		
		<form id="addFrm" enctype="multipart/form-data">
			<div style="width:700px; float: right; margin-right: 150px;">
				<input type="text" id="user_id" name="user_id" value="${param.user_id}" readonly="readonly"/>
					<br/>
					<br/>
				<select id="warning_code">
					<option value="선택">선택</option>
				<c:forEach var="list" items="${warningList}">
					<option value="${list.warning_type}"><c:out value="${list.warning_type}"/></option>
				</c:forEach>
				</select>
				<input type="text" id="warning_type" name="warning_type" readonly="readonly"/>
					<br/>
					<br/>
				<textarea rows="10" cols="60" id="warning_content" name="warning_content"></textarea>
					<br/>
					<br/>
		  </div>
		</form>
  	<div style="width: 850px; margin: 0px auto;">
	  	<input class="btn btn-secondary" type="button" value="추가" style="margin-left: 110px;" id="insertBtn">
	  	<input class="btn btn-secondary" type="button" value="취소" style="margin-left: 100px;" id="cancelBtn">
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

















