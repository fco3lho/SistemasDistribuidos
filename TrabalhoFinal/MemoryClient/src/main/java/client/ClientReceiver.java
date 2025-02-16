/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Felipe Campos
 */
public class ClientReceiver implements Runnable {
    private BufferedReader in;
    private MemoryGUI gui;

    public ClientReceiver(BufferedReader in, MemoryGUI gui) {
        this.in = in;
        this.gui = gui;
    }

    @Override
    public void run() {
        String line;
        try {
            while ((line = in.readLine()) != null) {
                System.out.println("Mensagem do servidor: " + line);
                String[] parts = line.split(" ");
                if (parts[0].equals("REVEAL") && parts.length >= 4) {
                    int row = Integer.parseInt(parts[1]);
                    int col = Integer.parseInt(parts[2]);
                    String value = parts[3];
                    gui.updateButton(row, col, value, true);
                } else if (parts[0].equals("HIDE") && parts.length >= 3) {
                    int row = Integer.parseInt(parts[1]);
                    int col = Integer.parseInt(parts[2]);
                    gui.updateButton(row, col, "", false);
                } else if (parts[0].equals("TURN") && parts.length >= 2) {
                    int turnPlayer = Integer.parseInt(parts[1]);
                    System.out.println("Vez do Jogador " + turnPlayer);
                    // Aqui você pode atualizar a interface para indicar de quem é a vez.
                } else if (parts[0].equals("SCORE") && parts.length >= 3) {
                    gui.updateScore(line);
                } else if (parts[0].equals("END") && parts.length >= 4) {
                    // Processa a mensagem de fim de jogo.
                    int score1 = Integer.parseInt(parts[1]);
                    int score2 = Integer.parseInt(parts[2]);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 3; i < parts.length; i++) {
                        sb.append(parts[i]).append(" ");
                    }
                    String resultMsg = sb.toString().trim();
                    // Chama o método que finaliza o jogo na interface.
                    gui.gameOver("Fim de Jogo!\nPlacar:\nJogador 1: " + score1 + "\nJogador 2: " + score2 + "\n" + resultMsg);
                } else if (parts[0].equals("MESSAGE")) {
                    StringBuilder msg = new StringBuilder();
                    for (int i = 1; i < parts.length; i++) {
                        msg.append(parts[i]).append(" ");
                    }
                    System.out.println(msg.toString());
                } else if (parts[0].equals("ERROR")) {
                    StringBuilder msg = new StringBuilder();
                    for (int i = 1; i < parts.length; i++) {
                        msg.append(parts[i]).append(" ");
                    }
                    System.out.println("Erro: " + msg.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
