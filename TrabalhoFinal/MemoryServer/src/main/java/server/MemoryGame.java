/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Felipe Campos
 */
public class MemoryGame {
    private int rows;
    private int cols;
    private int[][] board;        // Armazena os valores das cartas
    private boolean[][] revealed; // Indica se a carta está revelada

    // Controle de turno e dois cliques
    private int currentTurn;
    private boolean awaitingSecondFlip;
    private int firstFlipRow;
    private int firstFlipCol;
    private int firstFlipValue;

    // Pontuação para dois jogadores
    private int score1;
    private int score2;

    public MemoryGame(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new int[rows][cols];
        revealed = new boolean[rows][cols];
        initializeBoard();
        currentTurn = 1; // Jogador 1 inicia
        awaitingSecondFlip = false;
        score1 = 0;
        score2 = 0;
    }

    private void initializeBoard() {
        // Cria os pares (exemplo: para 10 pares, o total de cartas deve ser 20, por exemplo, 4x5)
        List<Integer> values = new ArrayList<>();
        int pairs = (rows * cols) / 2;
        for (int i = 1; i <= pairs; i++) {
            values.add(i);
            values.add(i);
        }
        Collections.shuffle(values);
        int index = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = values.get(index++);
                revealed[r][c] = false;
            }
        }
    }

    /**
     * Processa o comando de virar uma carta.
     * Retorna uma lista de mensagens a serem enviadas para os jogadores.
     */
    public synchronized List<String> processFlip(int playerId, int row, int col) {
        List<String> messages = new ArrayList<>();

        // Valida se é a vez do jogador.
        if (playerId != currentTurn) {
            messages.add("ERROR Não é sua vez.");
            return messages;
        }
        // Valida as coordenadas.
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            messages.add("ERROR Coordenadas inválidas.");
            return messages;
        }
        // Verifica se a carta já está revelada.
        if (revealed[row][col]) {
            messages.add("ERROR Carta já revelada.");
            return messages;
        }

        // Primeiro clique
        if (!awaitingSecondFlip) {
            firstFlipRow = row;
            firstFlipCol = col;
            firstFlipValue = board[row][col];
            awaitingSecondFlip = true;
            revealed[row][col] = true;
            messages.add("REVEAL " + row + " " + col + " " + firstFlipValue);
        } else {
            // Segundo clique
            if (row == firstFlipRow && col == firstFlipCol) {
                messages.add("ERROR Selecionou a mesma carta.");
                return messages;
            }
            int secondValue = board[row][col];
            revealed[row][col] = true;
            messages.add("REVEAL " + row + " " + col + " " + secondValue);

            if (firstFlipValue == secondValue) {
                // Acertou o par, soma ponto para o jogador
                if (playerId == 1) {
                    score1++;
                } else if (playerId == 2) {
                    score2++;
                }
                messages.add("MESSAGE Par encontrado! Jogador " + playerId + " continua.");
                messages.add("SCORE " + score1 + " " + score2);

                // Verifica se o jogo acabou (todos os pares foram encontrados)
                if (isGameOver()) {
                    String result;
                    if (score1 > score2) {
                        result = "Jogador 1 venceu!";
                    } else if (score2 > score1) {
                        result = "Jogador 2 venceu!";
                    } else {
                        result = "Empate!";
                    }
                    // Adiciona a mensagem final de término com placares e resultado.
                    messages.add("END " + score1 + " " + score2 + " " + result);
                }
            } else {
                // Caso não seja par, cria uma thread para esperar e depois ocultar as cartas.
                messages.add("MESSAGE Par não confere. As cartas serão ocultadas.");
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (MemoryGame.this) {
                        revealed[firstFlipRow][firstFlipCol] = false;
                        revealed[row][col] = false;
                        MemoryServer.broadcast("HIDE " + firstFlipRow + " " + firstFlipCol);
                        MemoryServer.broadcast("HIDE " + row + " " + col);
                        // Muda a vez para o outro jogador.
                        currentTurn = (currentTurn == 1 ? 2 : 1);
                        MemoryServer.broadcast("TURN " + currentTurn);
                    }
                }).start();
            }
            awaitingSecondFlip = false;
        }
        return messages;
    }
    
    public boolean isGameOver() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!revealed[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }
}
