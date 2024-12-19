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
        String caminhoArquivo = "C:\\SistemasDistribuidos\\Quizz\\ranking.txt";
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            escritor.write(nome + ":" + pontuacao + "\n");
        } catch (IOException e) {
            out.println("Erro ao salvar a pontuação no ranking.");
        }

        // Lê o ranking do arquivo
        List<String[]> ranking = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(":");
                ranking.add(dados);
            }
        } catch (IOException e) {
            out.println("Erro ao ler o ranking.");
        }

        // Ordena o ranking do maior para o menor
        ranking.sort((a, b) -> Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1])));
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
