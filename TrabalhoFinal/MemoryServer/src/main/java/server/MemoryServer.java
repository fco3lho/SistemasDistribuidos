/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe Campos
 */
public class MemoryServer {
    private static final int PORT = 4444;
    private static MemoryGame game;
    protected static List<PlayerHandler> players = new ArrayList<>();
    protected static List<SpectatorHandler> spectators = new ArrayList<>();
    
    public static void main(String[] args) {
        game = new MemoryGame(5, 4);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado na porta " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                
                // Cria leitores e escritores para identificar o papel
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                
                // Aguarda a mensagem de identificação: "PLAYER" ou "SPECTATOR"
                String role = in.readLine();
                if (role == null) {
                    socket.close();
                    continue;
                }
                
                if (role.equalsIgnoreCase("PLAYER")) {
                    // Verifica se já existem dois jogadores
                    if (players.size() >= 2) {
                        out.println("ERROR Já existem dois jogadores nesta partida");
                        socket.close();
                        System.out.println("Tentativa de conexão de PLAYER extra rejeitada.");
                    } else {
                        PlayerHandler player = new PlayerHandler(socket, players.size() + 1, game);
                        players.add(player);
                        new Thread(player).start();
                        System.out.println("Jogador " + player.getId() + " conectado.");
                    }
                } else if (role.equalsIgnoreCase("SPECTATOR")) {
                    // Adiciona o espectador
                    SpectatorHandler spectator = new SpectatorHandler(socket);
                    spectators.add(spectator);
                    new Thread(spectator).start();
                    System.out.println("Um espectador se conectou.");
                } else {
                    // Se a mensagem não for reconhecida, fecha a conexão.
                    out.println("ERROR Papel não reconhecido.");
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Método para enviar uma mensagem para todos os clientes (jogadores e espectadores)
    public static void broadcast(String message) {
        for (PlayerHandler player : players) {
            player.sendMessage(message);
        }
        for (SpectatorHandler spec : spectators) {
            spec.sendMessage(message);
        }
    }
}
