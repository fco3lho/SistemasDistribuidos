/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

/**
 *
 * @author Felipe Campos
 */
public class MemoryGUI extends JFrame {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private JButton[][] buttons;
    private final int ROWS;
    private final int COLS;
    private JLabel scoreLabel; // Rótulo para o placar

    public MemoryGUI(Socket socket) {
        // Ajuste as dimensões conforme o novo tabuleiro (ex.: 4 linhas x 5 colunas para 10 pares)
        this.ROWS = 5;
        this.COLS = 4;
        this.socket = socket;
        try {
            if(socket != null) {
                out = new PrintWriter(socket.getOutputStream(), true);
                in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                new Thread(new ClientReceiver(in, this)).start();
            } else {
                System.out.println("Executando sem conexão com servidor.");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        setupUI();
    }

    private void setupUI() {
        setTitle("Jogo da Memória Multiplayer");
        setSize(800, 850); // Aumentamos um pouco a altura para incluir o placar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Painel para o placar
        JPanel scorePanel = new JPanel();
        scoreLabel = new JLabel("Score - Jogador 1: 0, Jogador 2: 0");
        scorePanel.add(scoreLabel);
        
        // Painel para o tabuleiro
        JPanel boardPanel = new JPanel(new GridLayout(ROWS, COLS));
        buttons = new JButton[ROWS][COLS];
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                JButton button = new JButton("");
                final int row = r;
                final int col = c;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (out != null) {
                            out.println("FLIP " + row + " " + col);
                        } else {
                            System.out.println("Botão clicado em (" + row + ", " + col + ")");
                        }
                    }
                });
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

    // Atualiza um botão na posição indicada
    public void updateButton(int row, int col, String text, boolean revealed) {
        buttons[row][col].setText(text);
        buttons[row][col].setEnabled(!revealed);
    }

    // Atualiza o placar usando a mensagem "SCORE" enviada pelo servidor
    public void updateScore(String scoreMessage) {
        // O formato esperado é: "SCORE <score1> <score2>"
        String[] parts = scoreMessage.split(" ");
        if (parts.length >= 3) {
            scoreLabel.setText("Score - Jogador 1: " + parts[1] + ", Jogador 2: " + parts[2]);
        }
    }
    
    public void gameOver(String message) {
        // Exibe o pop-up com o resultado final.
        JOptionPane.showMessageDialog(this, message, "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
        // Fecha a janela e finaliza o aplicativo.
        dispose();
        System.exit(0);
    }
}
