<%-- 
    Document   : mensagem
    Created on : 17 de out. de 2024, 07:56:31
    Author     : Felipe Campos
--%>

<%@page import="java.io.File"%>
<%@page import="java.io.FileWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if(request.getParameter("btnenviar") != null){
                String msg = "";
                msg += "<img src=\"" + session.getAttribute("avatar") + "\" width=\"40\" height=\"40\">";
                msg += "<font size=\"+1\" color=\"" + session.getAttribute("cor") + "\">" + session.getAttribute("nickname") + "</font>";
                
                if(request.getParameter("cbmodo").toString().equals("falar")){
                    msg += " diz: " + request.getParameter("txtmsg");
                }
                else if(request.getParameter("cbmodo").toString().equals("gritar")){
                    msg += " grita: <font size=\"+2\">" + request.getParameter("txtmsg") + "</font>";
                }
                else if(request.getParameter("cbmodo").toString().equals("sussurrar")){
                    msg += " sussurra <font size=\"-2\">" + request.getParameter("txtmsg") + "</font>";
                }
                
                if(!request.getParameter("cbemoji").toString().equals("")){
                    msg += "<img src=\"" + request.getParameter("cbemoji") + "\" height=\"20\" width=\"20\">";
                }
                
                msg += "<br>";
            }
        %>
        <form name="frmmsg" method="post" action="mensagem.jsp">
            <b>Mensagem: </b><input type="text" name="txtmsg" maxlength="200" size="250"/>
            <br>
            <b>Modo: </b>
            <select name="cbmodo">
                <option value="gritar">Gritar</option>
                <option value="falar">Falar</option>
                <option value="sussurrar">Sussurrar</option>
            </select>
            <br>
            <select name="cbemoji">
                <option>Selecione</option>
                <option value="images/nerdola.jpg">Nerdola</option>
                <option value="images/oclin.jpg">Oclin</option>
                <option value="images/rindo.jpg">Rindo</option>
                <option value="images/tedio.jpg">Tédio</option>
            </select>
            <input type="submit" value="enviar" name="btnenviar"/>
        </form>
    </body>
</html>
