/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe Campos
 */
public class MemoryServer {
    private static final int PORT = 12345;
    private static MemoryGame game;
    protected static List<PlayerHandler> players = new ArrayList<>();
    
    public static void main(String[] args) {
        // Inicializa o jogo com um tabuleiro 20x20
        game = new MemoryGame(5, 4);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado na porta " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                PlayerHandler player = new PlayerHandler(socket, players.size() + 1, game);
                players.add(player);
                new Thread(player).start();
                System.out.println("Jogador " + player.getId() + " conectado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Método para enviar uma mensagem para todos os jogadores.
    public static void broadcast(String message) {
        for (PlayerHandler player : players) {
            player.sendMessage(message);
        }
    }
}
