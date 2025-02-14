<%@page import="pacote.QuizService"%>
<%@page import="java.rmi.Naming"%>
<%@ page import="java.io.*, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resultado</title>
</head>
<body>
    <h2>Resultado do Quiz</h2>

    <%
        // Pontuação do usuário e verificação da última pergunta
        int pontuacao = Integer.parseInt(session.getAttribute("pontuacao").toString());
        String nome = session.getAttribute("nome").toString();

        // Verifica resposta da última pergunta
        String respostaAnterior = request.getParameter("resposta");
        if ("Sim".equals(respostaAnterior)) { // Ajustado para checar a última pergunta enviada
            pontuacao++;
            session.setAttribute("pontuacao", pontuacao);
        }

        // Salva a pontuação no arquivo de ranking
        String msg = nome + ":" + pontuacao;
        try {
            QuizService objRmi = (QuizService)Naming.lookup("rmi://localhost:6789/servidorQuiz");
            
            if(!objRmi.atualizaRanking(msg)){
                out.print("Erro atualizando ranking.");
            }
        } catch (Exception e) {
            out.print("Erro no servidor ao atualizar ranking: " + e.getMessage());
            e.printStackTrace();
        }

        // Lê o ranking do arquivo
        List<String[]> ranking = new ArrayList<>();
        
        try {
            QuizService objRmi = (QuizService)Naming.lookup("rmi://localhost:6789/servidorQuiz");
            ranking = objRmi.recuperaRanking();
        } catch (Exception e) {
            out.print("Erro no servidor ao recuperar ranking: " + e.getMessage());
            e.printStackTrace();
        }
    %>

    <h3>Parabéns, <%= nome %>! Sua pontuação: <%= pontuacao %></h3>
    <h3>Ranking:</h3>
    <table border="1">
        <tr>
            <th>Nome</th>
            <th>Pontuação</th>
            <th>Visualização da Pontuação</th>
        </tr>
        <%
            for (String[] entry : ranking) {
                int entryPontuacao = Integer.parseInt(entry[1]);
        %>
            <tr>
                <td><%= entry[0] %></td>
                <td><%= entryPontuacao %></td>
                <td>
                    <%
                        for (int i = 0; i < entryPontuacao; i++) {
                    %>
                    <img src="images/quadrado-vermelho.png" alt="pontuação" width="15" height="15"/>
                    <%
                        }
                    %>
                </td>
            </tr>
        <%
            }
        %>
    </table>

    <form action="index.jsp">
        <button type="submit">Voltar à Página Inicial</button>
    </form>
</body>
</html>
