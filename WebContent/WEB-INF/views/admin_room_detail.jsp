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
	
	label{height: 19px;}

</style>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<script type="text/javascript">
var sel_file1;
var sel_file2;
var sel_file3;

$(function () {
	$("#logout").click(function(){
		if(confirm("로그아웃 하시겠습니까?")){
			location.replace('admin_logout.do')
		}//end if
		
	});//click
	
	$("#modifyBtn").click(function(){
		//유효성검증 작성
		if($("#room_name").val().trim() == ""){
			alert("방 이름을 입력해주세요");
			return;
		}//end if
		
		if($("#room_name2").val().trim() == ""){
			alert("방 이름2를 입력해주세요");
			return;
		}//end if
		
		if($("#charge").val().trim() == ""){
			alert("요금을 입력해주세요");
			return;
		}//end if
		
		if($("#charge").val().replace(/[^0-9]/g,"") == ""){
			alert("요금은 숫자만 입력해주세요");
			return;
		}//end if
		
		if($("#brief_info").val().trim() == ""){
			alert("간략 설명을 입력해주세요");
			return;
		}//end if
		
		if($("#info").val().trim() == ""){
			alert("상세 설명을 입력해주세요");
			return;
		}//end if
		
/* 		if($("#image1").val() == ""){
			alert("첫번째 이미지 파일을 추가해주세요.");
			return;
		}//end if
		
		if($("#image2").val() == ""){
			alert("두번째 이미지 파일을 추가해주세요.");
			return;
		}//end if
		
		if($("#image3").val() == ""){
			alert("세번째 이미지 파일을 추가해주세요.");
			return;
		}//end if */
		
		var formData = new FormData(document.getElementById('roomFrm'));
		
		if(confirm($("#room_name").val() + "의 내용을 수정하시겠습니까?")){
			$.ajax({
				url:"update_room_process.do",
				processData: false,
				contentType: false,
				data: formData,
				type: "post",
				
				dataType:"json",
				error:function(xhr){
					alert("문제발생\n" + xhr.status + "\n" + xhr.statusText);
				},
				success:function(json){
					if(json.result == true){
						alert("수정되었습니다.");
						location.href="admin_room.do";
					}else{
						alert("죄송합니다.");
						
					}//end if
					
				}
				
			});//ajax 
			
		}//end if
			
	});//click
	
	$("#removeBtn").click(function(){
		
		if(confirm($("#room_name").val() + "의 내용을 삭제하시겠습니까?")){
			var query = "room_name=" + $("#room_name").val();
			$.ajax({
				url:"delete_room_process.do",
				data: query,
				type: "post",
				
				dataType:"json",
				error:function(xhr){
					alert("문제발생\n" + xhr.status + "\n" + xhr.statusText);
				},
				success:function(json){
					if(json.result == true){
						alert("삭제되었습니다.");
						location.href="admin_room.do";
					}else{
						alert("죄송합니다.");
						
					}//end if
					
				}
				
			});//ajax 
			
		}//end if
		
	});//click
	
	$("#cancelBtn").click(function(){
			location.href='admin_room.do';
		
	});//click
	
	$("#image1").on("change", handleImgFileSelect1);
	$("#image2").on("change", handleImgFileSelect2);
	$("#image3").on("change", handleImgFileSelect3);
	
});//ready

function handleImgFileSelect1(e){
	var files = e.target.files;
	var fileArr = Array.prototype.slice.call(files);
	
	fileArr.forEach(function(f){
		if(!f.type.match("image.*")){
			alert("확장자는 이미지 확장자만 가능합니다.");
			return;
		}//end if
		
		sel_file1 = f;
		
		var reader = new FileReader();
		reader.onload = function(e){
			$("#img1").attr("src", e.target.result);
		}
		reader.readAsDataURL(f);
	});
}//handleImgFileSelect1

function handleImgFileSelect2(e){
	var files = e.target.files;
	var fileArr = Array.prototype.slice.call(files);
	
	fileArr.forEach(function(f){
		if(!f.type.match("image.*")){
			alert("확장자는 이미지 확장자만 가능합니다.");
			return;
		}//end if
		
		sel_file2 = f;
		
		var reader = new FileReader();
		reader.onload = function(e){
			$("#img2").attr("src", e.target.result);
		}
		reader.readAsDataURL(f);
	});
}//handleImgFileSelect2

function handleImgFileSelect3(e){
	var files = e.target.files;
	var fileArr = Array.prototype.slice.call(files);
	
	fileArr.forEach(function(f){
		if(!f.type.match("image.*")){
			alert("확장자는 이미지 확장자만 가능합니다.");
			return;
		}//end if
		
		sel_file3 = f;
		
		var reader = new FileReader();
		reader.onload = function(e){
			$("#img3").attr("src", e.target.result);
		}
		reader.readAsDataURL(f);
	});
}//handleImgFileSelect3

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
		
		<div style="text-align: center; font-size: 23px; font-weight: bold;"> 공간(룸) 상세 설명 </div>
		<br/>
		<br/>
		
		<div style="float: left; width: 150px; min-height: 600px; margin-left: 250px;">
			<label>컨셉 카테고리</label>
				<br/>
				<br/>
			<label>방 이름</label>
				<br/>
				<br/>
			<label>방 이름2</label>
				<br/>
				<br/>
			<label>수용인원</label>
				<br/>
				<br/>
			<label>요금</label>
				<br/>
				<br/>
			<label>간략설명</label>
				<br/>
				<br/>
			<label style="height: 250px;">상세설명</label>
				<br/>
				<br/>
			<label>이미지1</label>
				<br/>
				<br/>
			<label>이미지2</label>
				<br/>
				<br/>
			<label>이미지3</label>
				<br/>
				<br/>
			<label>이미지1/2/3</label>
				<br/>
				<br/>
		</div>
		
		<form id="roomFrm" enctype="multipart/form-data">
			<div style="width:700px;  min-height: 500px;float: right; margin-right: 150px;">
				<select>
					<c:forEach var="cnd" items="${conceptName}">
						<option value="${cnd.concept_name }" ${cnd.concept_name eq room_detail.concept_name?"selected":""}>
							<c:out value="${cnd.concept_name}(${cnd.concept_name2 })"/>
						</option>
					</c:forEach>
				</select>
					<br/>
					<br/>
				<input type="text" id="room_name" name="room_name" value="<c:out value="${param.room_name}"/>" readonly="readonly">
					<br/>
					<br/>
				<input type="text" id="room_name2" name="room_name2" value="<c:out value="${room_detail.room_name2}"/>">
					<br/>
					<br/>
				<input type="text" id="person_num" name="person_num" value="<c:out value="${room_detail.person_num}"/>">
					<br/>
					<br/>
				<input type="text" id="charge" name="charge" value="<c:out value="${room_detail.charge}"/>">
					<br/>
					<br/>
				<input type="text" style="width: 445px" id="brief_info" name="brief_info" value="<c:out value="${room_detail.brief_info}"/>">
					<br/>
					<br/>
				<textarea rows="10" cols="60" id="info" name="info"><c:out value="${room_detail.info}"/></textarea>
					<br/>
					<br/>
				<input type="file" id="image1" name="image1"/>
					<br/>
					<br/>
				<input type="file" id="image2" name="image2"/>
					<br/>
					<br/>
				<input type="file" id="image3" name="image3"/>
					<br/>
					<br/>
				<img id="img1" name="img1" src="http://211.63.89.153:8080/zz_prj3/common/images/${room_detail.image1}"/><c:out value="${room_detail.image1}"/>
				<br/>
				<img id="img2" name="img2" src="http://211.63.89.153:8080/zz_prj3/common/images/${room_detail.image2}"/><c:out value="${room_detail.image2}"/>
				<br/>
				<img id="img3" name="img3" src="http://211.63.89.153:8080/zz_prj3/common/images/${room_detail.image3}"/><c:out value="${room_detail.image3}"/>
				<br/>
			 	<div>
			  	<input class="btn btn-secondary" type="button" value="수정" id="modifyBtn">
			  	<input class="btn btn-secondary" type="button" value="삭제" style="margin-left: 100px;" id="removeBtn">
			  	<input class="btn btn-secondary" type="button" value="닫기" style="margin-left: 100px;" id="cancelBtn">
			 	</div>
					<br/>
					<br/>
		  </div>
		</form>
	</div>
	<div id="footer">
	<!-- 900(w) x 150(h) -->
		<div id="fLogo">
			<img src="http://211.63.89.153:8080/jsp_prj/common/images/f_logo.png"/>
		</div>
		<div id="fContent">
			&copy;CopyRight. AllRight Reserved. Class 4<br/>
			서울시 강남구 역삼동 한독빌딩 8층 4강의실.
		</div>
	</div>
</div>
</body>
</html>





