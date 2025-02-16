/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author Felipe Campos
 */
public class SpectatorHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    
    public SpectatorHandler(Socket socket) {
        this.socket = socket;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void sendMessage(String message) {
        out.println(message);
    }
    
    @Override
    public void run() {
        try {
            // Se desejar, pode ler mensagens do espectador (geralmente não são necessárias)
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                // Apenas loga qualquer mensagem recebida, se houver.
                System.out.println("Espectador enviou: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
