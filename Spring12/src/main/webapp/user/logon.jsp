<%@ page contentType="text/html; charset=EUC-KR" %>

<!-- ///////////////////////// ����� �κ� ///////////////////////////
	- Model2 Web Arch. ���� �� :: JSP�� View ����
	- Work Flow Control�� Controller ���
	- �Ʒ��� �ּ� : Controller ����ϴ� ControlServlet ���� ó��
	////////////////////////////////////////////////////////////////-->

<%
	String userId = "���̵��Է�";
	String password = "�н������Է�";
	//UserVO userVO = (UserVO)session.getAttribute("userVO");
%>

<html>
	<head><title>Logon Page</title></head>	
	<body>
	
		
		
		<!-- /////////////////////////////////////////////////////////////////////////// -->
		<!-- ///    �߰��� �κ� (Controller�� ModelAndView�� ������ Message���)     /// -->
		[�� info ] :: ${requestScope.message} <br/><br/>
		<!--  //////////////////////////////////////////////////////////////////////// -->
		
		<% //if(userVO == null || userVO.isActive() != true) {%>
		
	   <!-- <form method="post" action="logonAction.do">  -->
			<form method="post" action="logonAction">	
				�� �� �� : <input id="userId" type="text" name="userId" value=<%=userId %>><br/><br/>
				�н����� : <input id="password" type="text" name="userPasswd" value=<%=password %>><br/><br/>
				<input id="submit" type="submit" name="submit" value="Enter"/>
				
			</form>

		<%//else{ %>
			<%-- <%= userVO.getUserId() ���� �̹� �α��� �ϼ̽��ϴ�.--%>
		<%//} %>

			<br/><br/>
			<hr/>
			::info <%=request.getAttribute("info")%>
			<hr/>
	
	</body>
</html>