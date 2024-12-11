<%-- 
    Document   : topo
    Created on : 17 de out. de 2024, 07:56:47
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
        <img src='<%=session.getAttribute("avatar")%>' height="60" width="60"/>
        <font size="+3" color="<%=session.getAttribute("cor")%>">
            <%=session.getAttribute("nickname")%>
        </font>
    </body>
</html>
