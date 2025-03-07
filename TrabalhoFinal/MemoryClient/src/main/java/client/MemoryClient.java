/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe Campos
 */
public class MemoryClient {
    public static void main(String[] args) {
        // Solicita o endereço do servidor
        String serverAddress = "localhost";
        try {
            Socket socket = new Socket(serverAddress, 4444);
            // Envia a identificação de jogador para o servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("PLAYER");
            new MemoryGUI(socket);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao servidor.");
        }
    }
}
