<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Quizz</h1>
        <form action="perguntas.jsp" method="post">
            <label for="nome">Digite seu nome:</label>
            <input type="text" id="nome" name="nome" required>
            <button type="submit">Jogar</button>
        </form>
    </body>
</html>
