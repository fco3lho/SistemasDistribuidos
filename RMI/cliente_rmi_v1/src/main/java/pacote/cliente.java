/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

import java.awt.HeadlessException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe Campos
 */
public class cliente {
    public static void main(String[] args){
        try{
            rmi_interface objRmi = (rmi_interface)
                Naming.lookup("rmi://200.128.141.130:6666/servidor");
            
            double v1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro valor:"));
            double v2 = Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo valor:"));

            String[] operacoes = {"Somar", "Subtrair", "Multiplicar", "Dividir"};
            
            String operacaoEscolhida = (String) JOptionPane.showInputDialog(
                null, 
                "Escolha a operação:",
                "Operação",
                JOptionPane.QUESTION_MESSAGE,
                null,
                operacoes,
                operacoes[0]
            );
            
            String resultado = "";
            switch (operacaoEscolhida) {
                case "Somar":
                    resultado = objRmi.Somar(v1, v2);
                    break;
                case "Subtrair":
                    resultado = objRmi.Subtrair(v1, v2);
                    break;
                case "Multiplicar":
                    resultado = objRmi.Multiplicar(v1, v2);
                    break;
                case "Dividir":
                    resultado = objRmi.Dividir(v1, v2);
                    break;
                default:
                    resultado = "Operação inválida.";
            }

            JOptionPane.showMessageDialog(null, "Resultado: " + resultado);
        } catch (HeadlessException | MalformedURLException | NotBoundException | RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }
}
