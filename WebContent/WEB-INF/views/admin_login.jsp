<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info=""
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/zz_prj3/common/css/main.css">
<style type="text/css">
	#class4wrap{width: 1400px; height: 1100px; margin: 0px auto;}
	/* 헤더 시작 */
	#header{width: 1400px; height: 100px; position: relative; background: #000000;  }
	#hLogo{width: 84px; height: 84px; position: absolute; top: 10px; left: 40px}
	#hContent{width: 1400px; height: 90px; padding-top:10px;  position: absolute; top: 0px; left: 200px;}
	/* 헤더 끝 */

	/* 컨테이너 시작 */
	#container{width: 300px; height: 600px; margin: 0px auto; padding-top: 100px;}
	/* 컨테이너 끝 */
	
	/* 푸터 시작 */
	#footer{clear:both; width: 1400px; height: 150px; background-color: #fafafa; }
	#fLogo{width: 180px; height: 130px; padding-top: 20px; padding-left:20px;  float: left;}
	#fContent{width: 670px; height: 110px; padding-top: 40px; padding-left:30px; float: right;}
	/* 푸터 끝 */

	#hTitle{font-family: '고딕'; font-size: 30px; font-weight: bold; color: #F5F5F5;}
	
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script type="text/javascript">
$(function () {
	$("#admin_id").focus();
	$("#loginBtn").click(function(){
		
		if($("#admin_id").val().trim() == ""){
			alert("아이디를 입력해주세요.");
			$("#admin_id").focus();
			return;
		}//end if
		
		if($("#pass").val().trim() == ""){
			alert("비밀번호를 입력해주세요.");
			$("#pass").focus();
			return;
		}//end if
		
    var str = $("#admin_id").val();
    var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
    if(regExp.test(str)){
        alert("아이디에 특수문자를 입력할 수 없습니다.");
        $("#admin_id").val("");
        $("#admin_id").focus();
        return;
    }//end if

		$.ajax({
			url:"admin_login_process.do",
			type:"post",
			data:"admin_id="+$("#admin_id").val()+"&pass="+$("#pass").val(),
			dataType:"json",
			error:function(xhr){
				alert("문제발생\n" + xhr.status + "\n" + xhr.statusText);
			},
			success:function(json){
				
				if(json.result == true){
					location.href="admin_main.do";
				}else{
					alert("아이디 또는 비밀번호를 확인해주세요.");
					$("#admin_id").val("");
					$("#pass").val("");
					$("#admin_id").focus();
				}//end if
			}
		});//ajax
	});//click
});//ready
</script>
</head>
<body>
<div id="class4wrap">
	<!-- 900(w) x 1100(h) -->
	
	<div id="header">
	
		<div id="hLogo">
			<a href="#"><img src="http://localhost:8080/zz_prj3/images/header_logo.png" title="Home"/></a>
		</div>
		<div id="hContent">
			<div id="hTitle">관리자 페이지 </div><div style="font-size: 20px; color: #4799CF; font-weight: bold;">사이트이름</div>
		</div>
		<div id="naviBar"><!-- 900(w) x 50(h)네비게이션 --></div>
	</div>
	
	<div id="container">
		
		<div class="text-center">
			<div id="frm" class="form-signin" >
			  <img class="mb-4" src="/docs/4.3/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
			  <h1 class="h3 mb-3 font-weight-normal">관리자 로그인</h1>
			  <input type="text" id="admin_id" name="admin_id" class="form-control" placeholder="Id" autofocus="autofocus"/>
			  <input type="password" id="pass" name="pass" class="form-control" placeholder="Password"/><br/>
			  <button class="btn btn-dark" type="button" id="loginBtn">로그인</button>
			  <p class="mt-5 mb-3 text-muted"><%=new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %></p>
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