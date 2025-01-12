<%@page import="pacote.QuizService"%>
<%@ page import="java.util.List, java.rmi.Naming" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz</title>
</head>
<body>
    <%
        String nome = (String) session.getAttribute("nome");
        Integer pontuacao = (Integer) session.getAttribute("pontuacao");
        if (nome == null) {
            nome = request.getParameter("nome");
            pontuacao = 0;
            session.setAttribute("nome", nome);
            session.setAttribute("pontuacao", pontuacao);
        }

        int perguntaAtual = request.getParameter("perguntaAtual") != null 
            ? Integer.parseInt(request.getParameter("perguntaAtual")) 
            : 1;

        String respostaAnterior = request.getParameter("resposta");
        try {
            QuizService servidor = (QuizService) Naming.lookup("rmi://127.0.0.1:6789/servidorQuiz");

            if (respostaAnterior != null && servidor.verificarResposta(perguntaAtual - 1, respostaAnterior)) {
                pontuacao++;
                session.setAttribute("pontuacao", pontuacao);
            }

            if (perguntaAtual > 5) {
                response.sendRedirect("resultado.jsp");
                return;
            }

            String pergunta = servidor.getPergunta(perguntaAtual);
            List<String> alternativas = servidor.getAlternativas(perguntaAtual);
    %>

    <h3><%= pergunta %></h3>
    <form action="perguntas.jsp" method="post">
        <input type="hidden" name="perguntaAtual" value="<%= perguntaAtual + 1 %>">
        <%
            for (String alternativa : alternativas) {
        %>
            <input type="radio" name="resposta" value="<%= alternativa %>" required> <%= alternativa %><br>
        <%
            }
        %>
        <button type="submit">Responder</button>
    </form>
    <%
        } catch (Exception e) {
            out.print("Erro ao conectar com o servidor: " + e.getMessage());
        }
    %>
</body>
</html>
