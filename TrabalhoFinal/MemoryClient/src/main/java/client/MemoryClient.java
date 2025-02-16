/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe Campos
 */
public class MemoryClient {
    public static void main(String[] args) {
        // Solicita o endereço do servidor ao usuário
        String serverAddress = JOptionPane.showInputDialog(
                "Entre com o endereço do servidor (por exemplo, localhost):");
        try {
            Socket socket = new Socket(serverAddress, 12345);
            // Inicia a interface gráfica, passando o socket
            new MemoryGUI(socket);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao servidor.");
        }
    }
}
