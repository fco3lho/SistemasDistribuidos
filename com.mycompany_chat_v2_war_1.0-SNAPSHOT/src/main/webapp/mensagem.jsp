<%-- 
    Document   : mensagem
    Created on : 17 de out. de 2024, 07:56:31
    Author     : Felipe Campos
--%>

<%@page import="java.rmi.Naming"%>
<%@page import="pacote.rmiWebInterface"%>
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
        <form name="frmmsg" method="post" action="mensagem.jsp">
            <b> Mensagem: </b>
            <input type="text" name="txtmsg" maxlength="200" size="150">
            <br>
            <b>Modo: </b>
            <select name="cbModo">
                <option value="gritar">Gritar </option>
                <option value="falar" selected> Falar </option>
                <option value="sussurrar">Sussurrar </option>
            </select>
            <b>Emoji:</b>
            <select name="cbEmoji">
                <option value=""> Selecione </option>
                <option value="images/coracao.png"> ❤️ </option>
                <option value="images/sorrindo.png"> 😀 </option>
                <option value="images/ok.png"> 👍🏻 </option>
            </select>
            <input type="submit" name="btnEnviar" value="enviar">
        </form>
        
        <%
        if(request.getParameter("btnEnviar")!=null){
            String msg="";
            try {
                rmiWebInterface objRmi = (rmiWebInterface)Naming.lookup("rmi://127.0.0.1:6666/servidorWebChat");
                
                if(!objRmi.gravaMsg(msg)){
                    out.print("Erro salvando a mensagem");
                }
            } catch (Exception e) {
                out.print("erro no servidor ao salvar: " + e.getMessage());
                e.printStackTrace();
            }
            msg+="<img src=\"" + session.getAttribute("avatar") + "\" width=\"40\" height=\"40\">";
            msg+="<font size=\"+1\" color=\"" + session.getAttribute("cor")+"\">" + session.getAttribute("nickname") + "</font>";
        
            if(request.getParameter("cbModo").toString().equals("falar")){
                msg += " Diz: " + request.getParameter("txtmsg");
            } else if(request.getParameter("cbModo").toString().equals("gritar")){
                msg += "  Grita: <font size=\"+2\">" + request.getParameter("txtmsg") + "</font>";
            } else if(request.getParameter("cbModo").toString().equals("sussurrar")){
                msg += " Sussurra: <font size=\"-2\">" + request.getParameter("txtmsg") + "</font>";
            }
            
            if(!request.getParameter("cbEmoji").toString().equals("")){
                msg += "<img src=\""+request.getParameter("cbEmoji")+"\" height=\"20\" width=\"20\">";
            }
            msg += "<br>";
            
            try{
                rmiWebInterface objRmi = (rmiWebInterface) Naming.lookup("rmi://127.0.0.1:6666/servidorWebChat");
                if(!objRmi.gravaMsg(msg)){
                    out.print("Erro salvando a mensagem.");
                }
            } catch (Exception e){
                out.print("Erro no servidor ao salvar: " + e.getMessage());
                e.printStackTrace();
            }
        }
        %>
    </body>
</html>
