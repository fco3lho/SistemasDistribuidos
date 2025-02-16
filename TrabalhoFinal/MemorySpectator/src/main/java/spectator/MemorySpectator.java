/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spectator;

import javax.swing.JOptionPane;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Felipe Campos
 */
public class MemorySpectator {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        
        try {
            Socket socket = new Socket(serverAddress, 4444);
            // Envia a identificação de espectador
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("SPECTATOR");
            new SpectatorGUI(socket);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao servidor.");
        }
    }
}
