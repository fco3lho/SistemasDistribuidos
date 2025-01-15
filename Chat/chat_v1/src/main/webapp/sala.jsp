<%-- 
    Document   : sala
    Created on : 16 de out. de 2024, 08:25:26
    Author     : Felipe Campos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!--<h1>Variavéis</h1>-->
        <%
            //out.println("Variável <b>txtnick:</b>" + request.getParameter("txtnick") + "<br>");
            //out.println("Variável <b>radavatar:</b><font color='red'>" + request.getParameter("radavatar") + "</font><br>");
        %>
        <!--<b>Variável Radcor: </b><font size='+2'><%=request.getParameter("radcor")%></font>-->
        
        <%
            session.setAttribute("nickname", request.getParameter("txtnick"));
            session.setAttribute("avatar", request.getParameter("radavatar"));
            session.setAttribute("cor", request.getParameter("radcor"));
        %>
        
        <iframe src="topo.jsp" width="100%" height="130"></iframe>
        <iframe src="conversa.jsp" width="100%" height="630"></iframe>
        <iframe src="mensagem.jsp" width="100%" height="120"></iframe>
        
    </body>
</html>
