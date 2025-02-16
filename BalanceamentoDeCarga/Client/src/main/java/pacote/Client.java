/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

import java.rmi.Naming;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe Campos
 */
public class Client {
    public static void main(String[] args) {
        try {
            String serverAddress = JOptionPane.showInputDialog(
                "Digite o endereço de IP do servidor:");
            
            LoadBalancerInterface lb = (LoadBalancerInterface) Naming.lookup("rmi://" + serverAddress + ":4567/loadBalancer");
            System.out.println("Conectado ao Load Balancer.");
            
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Envia uma mensagem: ");
                String input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) break;
                
                // Envia a requisição e exibe a resposta
                String response = lb.forwardRequest(input);
                System.out.println("Resposta: " + response);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
