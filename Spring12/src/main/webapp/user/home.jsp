<%@ page contentType="text/html; charset=EUC-KR" %>

<%@ page import="spring.domain.User" %>

<!-- ///////////////////////// ����� �κ� ///////////////////////////
   - Model2 Web Arch. ���� �� :: JSP�� View ����
   - Work Flow Control�� Controller ���
   - �Ʒ��� �ּ� : Controller ����ϴ� ControlServlet ���� ó��
   ////////////////////////////////////////////////////////////////-->
 
 <% User sessionUser = (User)session.getAttribute("sessionUser"); %>
 
 <% //if(user == null || !user.isActive()){ %>
       <%--<jsp:forward page="logon.jsp"/>--%>
<%//} %>
 
<html>
   <head><title>Home Page</title></head>
   <body>
   [info] ::${requestScope.message}<br/><br/>
   		
      <p>Simple Model2 Examples</p>
      <p>ȯ���մϴ�. : <%= sessionUser.getUserId()%>��</p>
      <br/><br/>
      <a href = "logout">logout</a>
   </body>
</html>