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
        <style>
            .class {
                display: flex;
                text-align: center;
                align-items: center;
                justify-content: space-between;
            }
        </style>
        <script>
            function updateMulticastMessage() {
                fetch("multicastReceiver")
                    .then(response => response.text())
                    .then(data => {
                        document.getElementById("anuncio").innerText = data;
                    })
                    .catch(error => console.error("Erro ao buscar mensagem multicast:", error));
            }

            setInterval(updateMulticastMessage, 2000);
        </script>
    </head>
    <body>
        <div class="class">
            <div>
                <img src='<%=session.getAttribute("avatar")%>' height="60" width="60"/>
                <font size="+3" color="<%=session.getAttribute("cor")%>">
                    <%=session.getAttribute("nickname")%>
                </font>
            </div>

            <div>
                <h2>Anuncio</h2>
                <p id="anuncio">Aguardando anuncios...</p>
            </div>
        </div>
    </body>
</html>
