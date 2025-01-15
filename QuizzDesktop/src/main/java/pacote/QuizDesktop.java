package pacote;

import java.rmi.Naming;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QuizDesktop extends javax.swing.JFrame {

    private QuizService servidor;
    private int perguntaAtual = 1;
    private int pontuacao = 0;
    private String nomeJogador;

    public QuizDesktop() {
        initComponents();
        conectarServidor();
        telaInicial();
    }

    private void conectarServidor() {
        try {
            servidor = (QuizService) Naming.lookup("rmi://127.0.0.1:6789/servidorQuiz");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar com o servidor: " + e.getMessage());
            System.exit(0);
        }
    }

    private void telaInicial() {
        nomeJogador = JOptionPane.showInputDialog(this, "Digite seu nome:", "Bem-vindo ao Quiz!", JOptionPane.QUESTION_MESSAGE);
        if (nomeJogador == null || nomeJogador.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome inválido. O programa será encerrado.");
            System.exit(0);
        }
        carregarPergunta();
    }

    private void carregarPergunta() {
        try {
            String pergunta = servidor.getPergunta(perguntaAtual);
            List<String> alternativas = servidor.getAlternativas(perguntaAtual);

            lblPergunta.setText(pergunta);
            buttonGroup1.clearSelection();
            rbAlternativa1.setText(alternativas.get(0));
            rbAlternativa2.setText(alternativas.get(1));
            rbAlternativa3.setText(alternativas.get(2));
            rbAlternativa4.setText(alternativas.get(3));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar pergunta: " + e.getMessage());
        }
    }

    private void verificarResposta() {
        try {
            String respostaSelecionada = null;
            if (rbAlternativa1.isSelected()) respostaSelecionada = rbAlternativa1.getText();
            else if (rbAlternativa2.isSelected()) respostaSelecionada = rbAlternativa2.getText();
            else if (rbAlternativa3.isSelected()) respostaSelecionada = rbAlternativa3.getText();
            else if (rbAlternativa4.isSelected()) respostaSelecionada = rbAlternativa4.getText();

            if (respostaSelecionada == null) {
                JOptionPane.showMessageDialog(this, "Selecione uma alternativa.");
                return;
            }

            if (servidor.verificarResposta(perguntaAtual, respostaSelecionada)) {
                pontuacao++;
            }

            perguntaAtual++;
            if (perguntaAtual > 5) {
                finalizarQuiz();
            } else {
                carregarPergunta();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao verificar resposta: " + e.getMessage());
        }
    }

    private void finalizarQuiz() {
        try {
            servidor.atualizaRanking(nomeJogador + ":" + pontuacao);
            mostrarRanking();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao finalizar quiz: " + e.getMessage());
        }
    }

    private void mostrarRanking() {
        try {
            List<String[]> ranking = servidor.recuperaRanking();
            DefaultTableModel model = (DefaultTableModel) tblRanking.getModel();
            model.setRowCount(0); // Limpa a tabela

            for (String[] jogador : ranking) {
                model.addRow(new Object[]{jogador[0], jogador[1]});
            }

            pnlQuiz.setVisible(false);
            pnlRanking.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar ranking: " + e.getMessage());
        }
    }

    // Código gerado automaticamente pelo NetBeans para a GUI:
    @SuppressWarnings("unchecked")
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlQuiz = new javax.swing.JPanel();
        lblPergunta = new javax.swing.JLabel();
        rbAlternativa1 = new javax.swing.JRadioButton();
        rbAlternativa2 = new javax.swing.JRadioButton();
        rbAlternativa3 = new javax.swing.JRadioButton();
        rbAlternativa4 = new javax.swing.JRadioButton();
        btnResponder = new javax.swing.JButton();
        pnlRanking = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRanking = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quiz Desktop");

        lblPergunta.setText("Pergunta");
        buttonGroup1.add(rbAlternativa1);
        rbAlternativa1.setText("Alternativa 1");
        buttonGroup1.add(rbAlternativa2);
        rbAlternativa2.setText("Alternativa 2");
        buttonGroup1.add(rbAlternativa3);
        rbAlternativa3.setText("Alternativa 3");
        buttonGroup1.add(rbAlternativa4);
        rbAlternativa4.setText("Alternativa 4");

        btnResponder.setText("Responder");
        btnResponder.addActionListener(evt -> verificarResposta());

        javax.swing.GroupLayout pnlQuizLayout = new javax.swing.GroupLayout(pnlQuiz);
        pnlQuiz.setLayout(pnlQuizLayout);
        pnlQuizLayout.setHorizontalGroup(
            pnlQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuizLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPergunta, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(rbAlternativa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbAlternativa2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbAlternativa3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbAlternativa4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlQuizLayout.createSequentialGroup()
                        .addComponent(btnResponder)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlQuizLayout.setVerticalGroup(
            pnlQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuizLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPergunta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbAlternativa1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbAlternativa2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbAlternativa3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbAlternativa4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnResponder)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblRanking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Jogador", "Pontuação"}
        ));
        jScrollPane1.setViewportView(tblRanking);

        javax.swing.GroupLayout pnlRankingLayout = new javax.swing.GroupLayout(pnlRanking);
        pnlRanking.setLayout(pnlRankingLayout);
        pnlRankingLayout.setHorizontalGroup(
            pnlRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRankingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlRankingLayout.setVerticalGroup(
            pnlRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRankingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRanking.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlQuiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlRanking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlQuiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlRanking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new QuizDesktop().setVisible(true));
    }

    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton btnResponder;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPergunta;
    private javax.swing.JPanel pnlQuiz;
    private javax.swing.JPanel pnlRanking;
    private javax.swing.JRadioButton rbAlternativa1;
    private javax.swing.JRadioButton rbAlternativa2;
    private javax.swing.JRadioButton rbAlternativa3;
    private javax.swing.JRadioButton rbAlternativa4;
    private javax.swing.JTable tblRanking;
}
