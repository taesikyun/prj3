<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info=""
    %>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/zz_prj3/common/css/main.css"/>
<style type="text/css">
	#class4Wrap{width: 1400px; height: 1100px; margin: 0px auto;}
	/* 헤더 시작*/
	#header{width: 1400px; height: 100px; position: relative; background: #000000;  }
	#hLogo{width: 84px; height: 84px; position: absolute; top: 10px; left: 40px}
	#hContent{width: 1400px; height: 90px; padding-top:10px;  position: absolute; top: 0px; left: 200px;}
	/* 헤더 끝 */
	/* 사이드 시작 */
	#leftSide{width: 150px; min-height: 800px; background-color: #F5F5F5 ; float: left;}
	/* 사이드 끝 */
	/* 컨테이너 시작  */
	#container{width: 1150px; min-height: 800px; float:left; margin-left: 30px; margin-top: 50px;}
	.btn{width: 100px;height: 40px;}
	#ex{padding-bottom: 20px; width: 150px; font-size: 15px;}
	/* 컨테이너 끝  */
	/* 푸터 시작  */
	#footer{clear:both; width: 1400px; height: 150px; background-color: #fafafa; }
	#fLogo{width: 180px; height: 130px; padding-top: 20px; padding-left:20px;  float: left;}
	#fContent{width: 670px; height: 110px; padding-top: 40px; padding-left:30px; float: right;}
	/* 푸터 끝  */
	#hTitle{font-family: '고딕'; font-size: 30px; font-weight: bold; color: #F5F5F5;}
	.lList{font-family: '고딕'; font-size: 20px; font-weight: bold; color: #000000}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script> 
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Amaranth&display=swap" rel="stylesheet">
<style type="text/css">
.alert-danger{color: #000000; background-color: #E3C6C2; }
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script> 
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Amaranth&display=swap" rel="stylesheet">
<script type="text/javascript">
$(function(){
	$("#image").on("change", handleImgFileSelect);	
	
	$("#n_subject").keydown(function() {
		if($("#n_subject").val().length>30){
			alert("제목은 30자 이하만 작성 가능합니다.");
			$("#n_subject").val($("#n_subject").val().substr(0, 30));
			return;
		}//end if
	})//keydown
	
	
	var str = $("#n_content").val();
	
	str = str.split('<br/>').join("\r\n");

	$("#n_content").val(str);
	
	$("#goBtn").click(function() {
		
		
		
		if($("#n_subject").val()==""){
			alert("게시글 제목을 입력해주세요.");
			return;
		}//end if
	
		if($("#n_content").val().trim()==""){
			alert("내용을 작성해주세요.");
			return
		}//end if
	 if(confirm("변경사항을 수정하시겠습니까?")){
		 
		 
			var str = $('#n_content').val();

			str = str.replace(/(?:\r\n|\r|\n)/g, '<br/>');


			$('#n_content').val(str);
		 
		 
		 var formData = new FormData(document.getElementById('modify_process'));
		 $.ajax({
				url:"n_modify_process.do",
				processData: false,
				contentType: false,
				data:formData,
				type:"post",
				dataType:"json",
				error:function(xhr){
					alert("문제발생\n" + xhr.status + "\n" + xhr.statusText);
				},
				success:function(json){
					if(json.result == true){
						location.href="notice_post.do?n_num="+$("#n_num").val();
					}else{
						alert("게시글이 변경되지 않았습니다.");
					}//end if
				}//success
			});//ajax 	
		 
	 }
	});//click
	$("#backBtn").click(function(){
		if(confirm('작성하지 않고 돌아가시겠습니까?')){
		history.back();
		}//end if
	});//click
});//ready


var sel_file;
function handleImgFileSelect(e){
	var files = e.target.files;
	var fileArr = Array.prototype.slice.call(files);
	var fileName = $("#image").val();
	fileName=fileName.substr(fileName.lastIndexOf('\\')+1);
	alert(fileName);
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
		$("#n_content").val($("#n_content").val()+"<img src='http://localhost:8080/zz_prj/common/images/"+fileName+"'/>");
		
	});
		var formData = new FormData(document.getElementById('addFile'));
		$.ajax({
			url:"addFile.do",
			processData: false,
			contentType: false,
			data:formData,
			type:"post",
			dataType:"json",
			error:function(xhr){
				/* alert("문제발생\n" + xhr.status + "\n" + xhr.statusText); */
			},
			success:function(json){
				if(json.result == true){
					alert("추가하였습니다.");
					
				}else{
					alert("죄송합니다.");
					
				}//end if
				
			}//success
			
		});//ajax 	
		
}//handleImgFileSelect
</script>
</head>
<body>
<div id="class4Wrap">
<div id="header">
	
		<div id="hLogo">
			<a href="admin_main.do"><img src="http://localhost:8080/zz_prj3/images/header_logo.png" title="Home"/></a>
		</div>
		<div id="hContent">
			<div id="hTitle">관리자 페이지 </div><div style="font-size: 20px; color: #4799CF; font-weight: bold;">[:P]</div>
		</div>
		
	</div>
	<div id="leftSide">
	<!-- leftSide.jsp -->
		<c:import url="../../../common/jsp/leftSide.jsp"/>
	<!-- leftSide.jsp -->
	</div>
<div id="container">   
   <form id="modify_process" >
   <div style="margin-left: 50px;">
         <table>
           
            <tr>
                 <td id="ex" style="text-align: right;">제목 &nbsp;</td>
               <td>
         			<input class="form-control form-control-sm" type="text" name="n_subject" id="n_subject" autofocus="autofocus" value='${param.n_subject}' style="width: 734px;"/>
        			<br/>
        		</td>
            </tr>
            <tr>
               <td colspan="2" style="padding-left:150px;">
                  <textarea class="form-control form-control-sm" name="n_content" id="n_content" rows="15" cols="100" ><c:out value="${param.n_content}"/></textarea>
               </td>
            </tr>         
            </table>
            </div>
			<input type="hidden" id="n_num" name="n_num" value="<c:out value="${param.n_num}"/>"/>
			</form>				
           <div id="btnClass"style="position: relative; float:left; margin-left:400px; margin-top: 50px;" align="center">
				<input type="button" value="수정" class="btn btn-outline-secondary alert-danger" id="goBtn" style="margin-right: 25px;" >
				<input type="button" value="돌아가기" class="btn btn-outline-secondary alert-secondary" id="backBtn">
		<div style=" position: relative; float:right; margin-left:80px; margin-top: 10px; ">       
		   <form id="addFile" enctype="multipart/form-data">
         <input type="file" id="image" name="image" ><br/>
         <img style="margin-top: 10px;" id="img" width="150" height="150"/>
         </form>
            </div>
            </div>
			</div>
</div>
<div id="footer">
	<a href="#"><img src="http://localhost:8080/zz_prj/common/images/arrow.png" width="50" height="50" style="position:fixed; left: 93%; top:85%; "/></a> 
	<div id="fLogo">
		
	</div>
	<div id="fContent">
	
	
		
</div>
</div>

</body>
</html>