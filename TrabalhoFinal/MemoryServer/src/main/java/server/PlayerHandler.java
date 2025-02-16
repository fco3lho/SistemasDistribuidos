/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Felipe Campos
 */
public class PlayerHandler implements Runnable {
    private Socket socket;
    private int id;
    private MemoryGame game;
    private BufferedReader in;
    private PrintWriter out;

    public PlayerHandler(Socket socket, int id, MemoryGame game) {
        this.socket = socket;
        this.id = id;
        this.game = game;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public int getId() {
        return id;
    }
    
    // Envia mensagem para o cliente.
    public void sendMessage(String message) {
        out.println(message);
    }
    
    @Override
    public void run() {
        sendMessage("BEM-VINDO Jogador " + id);
        try {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Jogador " + id + " enviou: " + line);
                if (line.startsWith("FLIP")) {
                    String[] parts = line.split(" ");
                    if (parts.length >= 3) {
                        int row = Integer.parseInt(parts[1]);
                        int col = Integer.parseInt(parts[2]);
                        // Processa o flip e obtém as mensagens a serem enviadas.
                        List<String> responses = game.processFlip(id, row, col);
                        for (String response : responses) {
                            if (response.startsWith("ERROR")) {
                                // Erros são enviados somente para o jogador que enviou a jogada.
                                sendMessage(response);
                            } else {
                                // Outras mensagens são enviadas para todos os jogadores.
                                MemoryServer.broadcast(response);
                            }
                        }
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}