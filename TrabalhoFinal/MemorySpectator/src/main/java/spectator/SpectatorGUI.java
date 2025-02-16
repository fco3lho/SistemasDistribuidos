/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spectator;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 *
 * @author Felipe Campos
 */
public class SpectatorGUI extends JFrame {
    private Socket socket;
    private BufferedReader in;
    private JButton[][] buttons;
    private final int ROWS = 5;
    private final int COLS = 4;
    private JLabel scoreLabel;
    
    public SpectatorGUI(Socket socket) {
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Inicia a thread que receberá as mensagens do servidor
            new Thread(new SpectatorReceiver(in, this)).start();
        } catch(IOException e) {
            e.printStackTrace();
        }
        setupUI();
    }
    
    private void setupUI() {
        setTitle("Espectador - Jogo da Memória");
        setSize(800, 850); // Espaço para tabuleiro e placar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Painel para o placar
        JPanel scorePanel = new JPanel();
        scoreLabel = new JLabel("Score - Jogador 1: 0, Jogador 2: 0");
        scorePanel.add(scoreLabel);
        
        // Painel para o tabuleiro (botões desabilitados para que o espectador não possa interagir)
        JPanel boardPanel = new JPanel(new GridLayout(ROWS, COLS));
        buttons = new JButton[ROWS][COLS];
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                JButton button = new JButton("");
                button.setEnabled(false); // Desabilita a interação
                buttons[r][c] = button;
                boardPanel.add(button);
            }
        }
        
        // Layout geral
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scorePanel, BorderLayout.NORTH);
        getContentPane().add(boardPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    
    // Atualiza o texto de um botão (para REVEAL ou HIDE)
    public void updateButton(int row, int col, String text, boolean revealed) {
        buttons[row][col].setText(text);
    }
    
    // Atualiza o placar com base na mensagem SCORE recebida do servidor
    public void updateScore(String scoreMessage) {
        String[] parts = scoreMessage.split(" ");
        if (parts.length >= 3) {
            scoreLabel.setText("Score - Jogador 1: " + parts[1] + ", Jogador 2: " + parts[2]);
        }
    }
    
    // Método chamado quando o jogo termina: exibe um popup e fecha o aplicativo
    public void gameOver(String message) {
        JOptionPane.showMessageDialog(this, message, "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        System.exit(0);
    }
}
