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
        <%
//            out.println("Variável txtNick: " + request.getParameter("txtNick"));
//            out.println("Variável <b>radAvatar: </b><font color='red'>" + request.getParameter("radAvatar") + "</font>");
        %>
        
        <%
            session.setAttribute("nickname", request.getParameter("txtnick"));
            session.setAttribute("avatar", request.getParameter("radavatar"));
            session.setAttribute("cor", request.getParameter("radcor"));
        %>
        
<!--        <b>Variável radColor: </b><font size="+2"> <//%=request.getParameter("radColor")%></font>-->
        
        <iframe src='topo.jsp' width="100%" height="130"></iframe>
        <iframe src='conversa.jsp' width="100%" height="600"></iframe>
        <iframe src='mensagem.jsp' width="100%" height="120"></iframe>
    </body>
</html>
