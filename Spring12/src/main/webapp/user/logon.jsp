<%@ page contentType="text/html; charset=EUC-KR" %>

<!-- ///////////////////////// 변경된 부분 ///////////////////////////
	- Model2 Web Arch. 적용 시 :: JSP는 View 역할
	- Work Flow Control은 Controller 담당
	- 아래의 주석 : Controller 담당하는 ControlServlet 에서 처리
	////////////////////////////////////////////////////////////////-->

<%
	String userId = "아이디입력";
	String password = "패스워드입력";
	//UserVO userVO = (UserVO)session.getAttribute("userVO");
%>

<html>
	<head><title>Logon Page</title></head>	
	<body>
	
		
		
		<!-- /////////////////////////////////////////////////////////////////////////// -->
		<!-- ///    추가된 부분 (Controller가 ModelAndView에 저장한 Message출력)     /// -->
		[■ info ] :: ${requestScope.message} <br/><br/>
		<!--  //////////////////////////////////////////////////////////////////////// -->
		
		<% //if(userVO == null || userVO.isActive() != true) {%>
		
	   <!-- <form method="post" action="logonAction.do">  -->
			<form method="post" action="logonAction">	
				아 이 디 : <input id="userId" type="text" name="userId" value=<%=userId %>><br/><br/>
				패스워드 : <input id="password" type="text" name="userPasswd" value=<%=password %>><br/><br/>
				<input id="submit" type="submit" name="submit" value="Enter"/>
				
			</form>

		<%//else{ %>
			<%-- <%= userVO.getUserId() 님은 이미 로그인 하셨습니다.--%>
		<%//} %>

			<br/><br/>
			<hr/>
			::info <%=request.getAttribute("info")%>
			<hr/>
	
	</body>
</html>