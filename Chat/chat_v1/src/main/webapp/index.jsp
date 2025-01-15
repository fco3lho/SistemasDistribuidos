<%-- 
    Document   : index
    Created on : 16 de out. de 2024, 07:28:58
    Author     : Felipe Campos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chat Versão 1.0</title>
    </head>
    <body>
        <form name='frmlogin' action="sala.jsp" method='GET'>
            <center>
                <table border="1">
                    <tr>
                        <td colspan="2" bgcolor="tomato">
                            <font color="white" size="+4">Login</font>
                        </td>
                    </tr>
                    <tr>
                        <td align='right'>
                            <b>Nickname:</b>
                        </td>
                        <td>
                            <input type="text" name="txtnick" size='100' maxlength="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td align='right'>
                            <b>Avatar:</b>
                        </td>
                        <td>
                            <input type="radio" name="radavatar" checked value="images/avatar1.png"/>
                            <img src='images/avatar1.png' width='60' height='60'/>
                       
                            <input type="radio" name="radavatar" value="images/avatar2.png"/>
                            <img src='images/avatar2.png' width='60' height='60'/>
                        
                            <input type="radio" name="radavatar" value="images/avatar3.png"/>
                            <img src='images/avatar3.png' width='60' height='60'/>
                       
                            <input type="radio" name="radavatar" value="images/avatar4.png"/>
                            <img src='images/avatar4.png' width='60' height='60'/>
                        </td>
                    </tr>
                    <tr>
                        <td align='right'><b>Cor:</b></td>
                        <td>
                            <input type="radio" name='radcor' value="navy" checked/><font color='navy'>Azul</font>
                            <input type="radio" name='radcor' value="purple"/><font color='purple'>Roxo</font>
                            <input type="radio" name='radcor' value="yellow"/><font color='yellow'>Amarelo</font>
                            <input type="radio" name='radcor' value="green"/><font color='green'>Verde</font>
                        </td>
                    </tr>
                    <tr>
                        <td colspan='2' align='center'>
                            <input type="submit" name="btncmd" value="Entrar"/>
                        </td>
                    </tr>
                </table>
            </center>
        </form>
    </body>
</html>
