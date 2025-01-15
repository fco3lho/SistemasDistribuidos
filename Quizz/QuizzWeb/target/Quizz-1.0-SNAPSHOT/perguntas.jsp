<%@ page import="java.util.ArrayList, java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz</title>
</head>
<body>
    <%
        // Verifica e inicializa nome e pontuação, se ainda não estão definidos
        String nome = (String) session.getAttribute("nome");
        Integer pontuacao = (Integer) session.getAttribute("pontuacao");

        if (nome == null) {
            nome = request.getParameter("nome");
            pontuacao = 0;
            session.setAttribute("nome", nome);
            session.setAttribute("pontuacao", pontuacao);
        }

        // Pergunta atual
        int perguntaAtual = request.getParameter("perguntaAtual") != null ? Integer.parseInt(request.getParameter("perguntaAtual")) : 1;

        // Processa a resposta anterior e incrementa pontuação, se correta
        String respostaAnterior = request.getParameter("resposta");
        if (respostaAnterior != null) {
            if ((perguntaAtual == 2 && "Brasilia".equals(respostaAnterior)) ||
                (perguntaAtual == 3 && "Mercurio".equals(respostaAnterior)) ||
                (perguntaAtual == 4 && "Chris e Greg".equals(respostaAnterior)) ||
                (perguntaAtual == 5 && "Uno com escada no teto".equals(respostaAnterior)) ||
                (perguntaAtual == 6 && "Sim".equals(respostaAnterior))) {
                pontuacao++;
                session.setAttribute("pontuacao", pontuacao);
            }
        }
        
        // Redireciona para resultado.jsp após a quinta pergunta
        if (perguntaAtual > 5) {
            response.sendRedirect("resultado.jsp");
            return; // Interrompe a execução
        }

        // Define perguntas e alternativas com base na pergunta atual
        String pergunta = "";
        ArrayList<String> alternativas = new ArrayList<>();

        switch (perguntaAtual) {
            case 1:
                pergunta = "Qual é a capital do Brasil?";
                alternativas.add("Brasilia");
                alternativas.add("Rio de Janeiro");
                alternativas.add("Sao Paulo");
                alternativas.add("Salvador");
                break;
            case 2:
                pergunta = "Qual é o planeta mais próximo do Sol?";
                alternativas.add("Mercurio");
                alternativas.add("Venus");
                alternativas.add("Terra");
                alternativas.add("Marte");
                break;
            case 3:
                pergunta = "Quem é o protagonista da série 'Todo mundo odeia o Chris'?";
                alternativas.add("Chris e Greg");
                alternativas.add("Chris");
                alternativas.add("Chris Rock");
                alternativas.add("Tyles James");
                break;
            case 4:
                pergunta = "Dentre esses 4 carros, qual ganha a corrida?";
                alternativas.add("Uno com escada no teto");
                alternativas.add("Koenigsegg Jesko");
                alternativas.add("Ferrari SF90");
                alternativas.add("McLaren P1");
                break;
            case 5:
                pergunta = "Renato Aragão inspirou o nome de P. Diddy?";
                alternativas.add("Sim");
                alternativas.add("Nao");
                break;
        }

        Collections.shuffle(alternativas);
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
</body>
</html>
