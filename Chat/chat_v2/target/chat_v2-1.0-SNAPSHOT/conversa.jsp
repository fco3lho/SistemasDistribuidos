<%-- 
    Document   : conversa
    Created on : 17 de out. de 2024, 07:55:57
    Author     : Felipe Campos
--%>
<%@page import="java.rmi.Naming"%>
<%@page import="pacote.rmiWebInterface"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="3;conversa.jsp">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            try {
                rmiWebInterface objRmi = (rmiWebInterface)Naming.lookup("rmi://localhost:6666/servidorWebChat");
                out.print(objRmi.recuperaMsgs());
            } catch (Exception e) {
                out.print("Erro no servidor ao recuperar mesangens: " + e.getMessage());
                e.printStackTrace();
            }
        
        %>
    </body>
</html>
