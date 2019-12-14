<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info=""
 %>
 <%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	label{height: 19px;}

</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script type="text/javascript">
var sel_file;

$(function () {
	$("#logout").click(function(){//로그아웃
		if(confirm("로그아웃 하시겠습니까?")){
			location.replace('admin_logout.do')
		}//end if
		
	});//click
	
	
	$("#cancelBtn").click(function(){//닫기 버튼 클릭시 
			location.href='admin_room.do';
		
	});//click

	
	$("#modifyBtn").click(function(){//수정 버튼 클릭 시
		//유효성검증 작성
		if($("#concept_name").val().trim() == ""){
			alert("컨셉이름을 입력해주세요");
			return;
		}//end if
		
		if($("#concept_name2").val().trim() == ""){
			alert("컨셉이름2을 입력해주세요");
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
		
		if($("#image").val() == ""){
			alert("이미지 파일을 추가해주세요.");
			return;
		}//end if 
		
		var formData = new FormData(document.getElementById('conceptFrm'));
			
		if(confirm($("#concept_name").val() + "의 내용을 수정하시겠습니까?")){
			$.ajax({
				url:"update_concept_process.do",
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
		
		if(confirm($("#concept_name").val() + "의 내용을 삭제하시겠습니까?")){
			var query = "concept_name=" + $("#concept_name").val();
			$.ajax({
				url:"delete_concept_process.do",
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
	
	$("#image").on("change", handleImgFileSelect);
	
});//ready

function handleImgFileSelect(e){
	var files = e.target.files;
	var fileArr = Array.prototype.slice.call(files);
	
	fileArr.forEach(function(f){
		if(!f.type.match("image.*")){
			alert("확장자는 이미지 확장자만 가능합니다.");
			return;
		}//end if
		
		sel_file = f;
		
		var reader = new FileReader();
		reader.onload = function(e){
			$("#img").attr("src", e.target.result);
		}
		reader.readAsDataURL(f);
	});
}//handleImgFileSelect

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
		
		<div style="text-align: center; font-size: 23px; font-weight: bold;"> 공간(컨셉) 상세 설명 </div>
		<br/>
		<br/>
		
		<div style="float: left; width: 150px; min-height: 600px; margin-left: 250px;">
			<label>컨셉 이름</label>
				<br/>
				<br/>
			<label>컨셉 이름2</label>
				<br/>
				<br/>
			<label>간략설명</label>
				<br/>
				<br/>
			<label style="height: 250px;">상세설명</label>
				<br/>
				<br/>
			<label>이미지</label>
				<br/>
				<br/>
		</div>
		
		<form id="conceptFrm" enctype="multipart/form-data">
			<div style="width:700px;  min-height: 500px;float: right; margin-right: 150px;">
				<input type="text" id="concept_name" name="concept_name" value="<c:out value="${param.concept_name }"/>" readonly="readonly">
					<br/>
					<br/>
				<input type="text" id="concept_name2" name="concept_name2" value="<c:out value="${concept_detail.concept_name2 }"/>">
					<br/>
					<br/>
				<input type="text" style="width: 445px" id="brief_info" name="brief_info" value="<c:out value="${concept_detail.brief_info}"/>">
					<br/>
					<br/>
				<textarea rows="10" cols="60" id="info" name="info"><c:out value="${concept_detail.info}"/></textarea>
					<br/>
					<br/>
				<input type="file" id="image" name="image"/>
					<br/>
					<br/>
				<img id="img" name="img"  src="http://localhost:8080/zz_prj3/common/images/${concept_detail.image}"/><c:out value="${concept_detail.image}"/>
					<br/>
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
			<img src="http://localhost:8080/jsp_prj/common/images/f_logo.png"/>
		</div>
		<div id="fContent">
			&copy;CopyRight. AllRight Reserved. Class 4<br/>
			서울시 강남구 역삼동 한독빌딩 8층 4강의실.
		</div>
	</div>
</div>
</body>
</html>





