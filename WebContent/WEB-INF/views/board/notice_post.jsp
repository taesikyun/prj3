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
<link rel="stylesheet" type="text/css" href="http://211.63.89.153:8080/zz_prj3/common/css/main.css"/>
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
	#container{width: 1000px; min-height: 800px; float:left; margin-left: 30px; margin-top: 50px;}
	.btn{width: 100px;height: 40px;}
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
<script type="text/javascript">
$(function(){
	var obj = document.n_frm;
	$("#modifyPost").click(function(){
	obj.action="n_modify_form.do" ;
		$("#n_frm").submit();
	});//click
	$("#deletePost").click(function(){
	
	if(confirm("정말 삭제하시겠습니까?")){
	
	var formData = new FormData(document.getElementById('n_frm'));
	$.ajax({
		url:"n_delete_post.do",
		processData: false,
		contentType: false,
		data:formData,
		type:"post",
		dataType:"json",
		error:function(xhr){
			alert("문제발생\n" + xhr.status + "\n" + xhr.statusText);
		},
		success:function(json){
		location.href="notice_list.do";	
		}//success
	});//ajax 	
	
	}//end if
	
	});//click
});//ready
</script>
</head>
<body>
<div id="class4Wrap">
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
		<c:import url="../../../common/jsp/leftSide.jsp"/>
	<!-- leftSide.jsp -->
	</div>
<div id="container">
<h2 style="margin-bottom: 40px; text-align: center; font-weight: bold;"> notice </h2>
<form method="post" name="n_frm" id="n_frm">
<table class="table" >
  <tbody>
    <tr>
      <th scope="row" id="th">subject</th><td align="center"><c:out value="${nbdd.n_subject}"/></td>
    </tr>
    <tr>
      <th scope="row" id="th">Date</th><td><c:out value="${nbdd.n_input_Date}"/></td>
    </tr>
    <tr>
      <td colspan="2" style="height: 600px;">
      <div style="margin: 50px; text-align: left;">
  		<c:out value="${nbdd.n_content}" escapeXml="false"/>
     	
      </div>
      </td>
    </tr>
    <tr>
    </tr>
  </tbody>
</table>


<div style="float: left;">
	<input type="button" value="목록" class="btn btn-secondary alert-danger btn-sm" id="golist" onclick="location.href='notice_list.do'">
</div>
 <c:if test="${admin_id ne null }">
 <input type="hidden" name="n_subject" value="${nbdd.n_subject}"/>
 <input type="hidden" name="n_content" value="${nbdd.n_content}"/>
 <input type="hidden" name="n_num" value="${nbdd.n_num }"/>
<div style="float:right;">
	<input type="button" value="수정" class="btn btn-secondary alert-secondary" id="modifyPost">
	<input type="button" value="삭제" class="btn btn-secondary alert-secondary" id="deletePost" >
</div>
</c:if>
</form>

</div>

</div>
<div id="footer">
	<div id="fLogo">
		
	</div>
	<div id="fContent">
	
		
	</div>
</div>

</body>
</html>