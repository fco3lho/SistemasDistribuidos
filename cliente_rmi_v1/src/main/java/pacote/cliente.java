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
                Naming.lookup("rmi://127.0.0.1:6666/servidor");
            JOptionPane.showMessageDialog(null, objRmi.Somar(100, 200));
        } catch (HeadlessException | MalformedURLException | NotBoundException | RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }
}
