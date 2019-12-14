<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
	
	$("#addRp").click(function(){
		var q_answer_flag = $("#q_answer_flag").val();
		
		if(q_answer_flag =="N"){
			var str = $("#q_answer").val();
			str = str.replace(/(?:\r\n|\r|\n)/g, '<br/>');
			$("#q_answer").val(str);
			
		var formData = new FormData(document.getElementById('rpFrm'));
			$.ajax({
				url:"addRp.do",
				processData: false,
				contentType: false,
				data:formData,
				type:"post",
				dataType:"json",
				error:function(xhr){
					alert("문제발생\n" + xhr.status + "\n" + xhr.statusText);
				},
				success:function(json){
					location.href="qna_post.do?q_num="+$("#q_num").val();	
				}//success
			});//ajax 	
			
		}else{
			alert("댓글을 이미 작성하셨습니다.");
			
		}//end else
		
	});//click
	
	$("#modifyPost").click(function(){
		
	$("#modify_frm").submit();
	
	});//click
	
	$("#modifyRp").click(function(){
		var obj = document.repFrm;
		$("#replyPre").toggle();
		$("#replyNext").toggle();
		
		var q_answer = $("#c_answer").val().trim();
		var n_answer = $("#n_answer").val().trim();
		
		var str = $("#n_answer").val();

		str = str.split('<br/>').join("\r\n");

		$("#n_answer").val(str);
				
		if(q_answer != n_answer){
			if(confirm("변경내용을 저장하시겠습니까??")){		
				var str = $("#n_answer").val();
				str = str.replace(/(?:\r\n|\r|\n)/g, '<br/>');
				$("#n_answer").val(str);	
				
	var formData = new FormData(document.getElementById('repFrm'));
			$.ajax({
				url:"rp_modify.do",
				processData: false,
				contentType: false,
				data:formData,
				type:"post",
				dataType:"json",
				error:function(xhr){
					alert("문제발생\n" + xhr.status + "\n" + xhr.statusText);
				},
				success:function(json){
				location.href="qna_post.do?q_num="+$("#q_num").val();	
				}//success
			});//ajax 	
			
			
			
			
			}//end if
		}//end if
	});//click
});//ready
function del_process(q_num){
	if(confirm("정말 삭제하시겠습니까?")){
		var q_num = q_num;
		$.ajax({
			url:"delete_post.do",
			processData: false,
			contentType: false,
			data:"q_num="+q_num,
			type:"get",
			dataType:"json",
			error:function(xhr){
				alert("문제발생\n" + xhr.status + "\n" + xhr.statusText);
			},
			success:function(json){
				if(json.result == true){
					$("#answer").html();
					location.href="qna_list.do"
				}else{
					alert("게시글이 삭제되지 않았습니다.");
				}//end if
			}//success
		});//ajax 	
		
			}//end if
	
}//del_process
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
<h2 style="margin-bottom: 40px; text-align: center; font-weight: bold;"> Q & A </h2>
<table class="table" >
  <tbody>

    <tr>
      <th scope="row" id="th">subject</th><td><c:out value="${qbdd.q_subject}"/></td>
    </tr>
    <tr>
      <th scope="row" id="th">name</th><td><c:out value="${qbdd.user_id}"/></td>
    </tr>
    <tr>
      <th scope="row" id="th">Date</th><td><c:out value="${qbdd.q_input_Date}"/></td>
    </tr>
    <tr>
      <td colspan="2" style="height: 600px;">
      <div style="margin: 50px; text-align: left;">
 	 <c:out value="${qbdd.q_content}" escapeXml="false"/>
      </div>
      </td>
   	</tr>
     <c:if test="${qbdd.q_answer_Flag eq 'Y'}">
     <tr id="answer">

   	<th id="th"  style="vertical-align: middle; height:150px; text-align: center;">관리자   </th>
      <td style="background-color: #FFFAF0;">
        <form id="repFrm" name="repFrm" >
      <div style="float:left;" id="reply">
      <div id="replyPre" >
      <c:out value="${qbdd.q_answer}" escapeXml="false"/><span style="font-size: 12px; color: gray; margin-left: 50px;" ><c:out value="${qbdd.q_answer_date}"/></span>
      </div>
      <div id="replyNext" style="display: none">
      <textarea rows="5" style="width: 810px;"   class="form-control" id="n_answer" name="q_answer"> <c:out value="${qbdd.q_answer}" escapeXml="false"/></textarea>
      </div>
      
       </div>
        <c:if test="${admin_id ne null }">
         <div style="float:right;">
         <input type="hidden" name="q_num" value="${qbdd.q_num }"/>
         <input type="hidden" name="c_answer" id="c_answer" value="${qbdd.q_answer}"/>
   		 <input type="button" value="수정" class="btn btn-secondary alert-secondary" id="modifyRp" />
    	</div> 	
    	</c:if>
    	 </form>
      </td>
     
      </tr>
      </c:if>
    <tr>
     <c:if test="${admin_id ne null }">
      <th scope="row" id="th" style="vertical-align: middle;">댓글</th>
      <td>
      <div>
     <form  id="rpFrm" name="rpFrm">
      <div style="float:left;">
      <textarea name="q_answer" id="q_answer" class="form-control" style="width: 810px;" rows="5" 
      <c:if test="${qbdd.q_answer_Flag eq 'Y'}">readonly="readonly" placeholder="답글을 이미 작성 하셨습니다."</c:if>></textarea>
      </div>
      <div style="float:right;">
	<input type="hidden" name="q_num" id="q_num" value="<c:out value="${qbdd.q_num}"/>"/>
	<input type="hidden" id="q_answer_flag" name="q_answer_flag" value="<c:out value="${qbdd.q_answer_Flag}"/>"/>
    <input type="button" value="입력" class="btn btn-secondary alert-danger" id="addRp" name="addRp" style="width: 110px; height: 135px;"/>
    	</div>
    </form>
    	</div>
    </td>
    </c:if>
    </tr>
  </tbody>
</table>
<div >
<div style="float: left;">
	<input type="button" value="목록" class="btn btn-secondary alert-danger btn-sm" id="golist" onclick="location.href='qna_list.do'"/>
</div>
<form action="modify_form.do" method="post" name="modify_frm" id="modify_frm">
<input type="hidden" name="q_num" value="<c:out value="${qbdd.q_num}"/>"/>
<div style="float:right;">
	<input type="button" value="삭제" class="btn btn-secondary alert-secondary" id="deletePost" onclick="del_process('${qbdd.q_num}')" />
</div>
</form>
</div>

</div>
<div id="footer">
	<a href="#"><img src="http://localhost:8080/zz_prj/common/images/arrow.png" width="50" height="50" style="position:fixed; left: 93%; top:85%;  "/></a> 
	<div id="fLogo">
		
	</div>
	<div id="fContent">
		
	</div>
</div>
</div>

</body>
</html>