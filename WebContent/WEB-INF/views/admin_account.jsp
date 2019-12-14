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
			location.replace('admin_logout.do')
		}//end if
		
	});//click
	
	/* $("#chkAll").click(function(){
		if($("#chkAll").prop("checked")){
   		$("input[type=checkbox]").prop("checked",true); 
    }else{
			$("input[type=checkbox]").prop("checked",false); 
    }//end if
    
	});//click */
	
});//ready

function chk(){
	if($('input:radio[name=chk]').is(':checked')){
		if(confirm("선택한 계좌를 삭제하시겠습니까?")){
			var param = "bank="+$("input[type=radio][name=chk]:checked").val();
			$.ajax({
				url: "remove_account_process.do",
				data: param,
				type: "post",
				
				dataType: "json",
				error: function(xhr){
					alert("문제발생\n" + xhr.status + "\n" + xhr.statusText);
				},
				success: function(json){
					if(json.result == true){
						alert("삭제되었습니다.");
						location.href = "admin_account.do";
					}else{
						alert("죄송합니다");
					}//end else
				}
			});//ajax
		}
	}else{
		alert("삭제하려는 계좌를 선택해주세요.");
	}//end if
}//chk

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
		
		<div style="text-align: center; font-size: 23px; font-weight: bold;">	계좌 관리	</div>
		<br/>
		
		<div style="margin: 0px auto; width: 600px" class="d-flex justify-content-end">
			<a href="move_add_account_form.do"><img src="http://211.63.89.153:8080/zz_prj3/images/plus.png" title="계좌 추가"/></a>&nbsp;
			<a href="javascript:chk()"><img src="http://211.63.89.153:8080/zz_prj3/images/minus.png" title="계좌 삭제"/></a>
		</div>
		<br/>
		<div style="margin: 0px auto; width: 600px">
		<table class="table table-hover" id="memberTab">
			<thead>
				<tr>
					<th style="width: 40px; "></th>
					<th style="width: 200px; ">은행</th>
					<th style="width: 300px; ">계좌번호</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="accountList" items="${account_list}">
				<tr>
					<td><input type="radio" id="chk" name="chk" value="${accountList.bank }"></td>
					<td id="bank"><c:out value="${accountList.bank }"/></td>
					<td><c:out value="${accountList.account_number }"/></td>
				</tr>
			</c:forEach>
			<c:if test="${empty account_list }">
				<tr>
					<td colspan="3">조회할 계좌가 없습니다. 계좌를 추가해주세요</td>
				</tr>
			</c:if>
			</tbody>
		</table>
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

















