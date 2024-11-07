/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe Campos
 */
public class server {
    public server(){
        try{
            Registry reg = LocateRegistry.createRegistry(6666);
            rmi_interface objRmi = (rmi_interface) new rmi_implementation();
            reg.rebind("servidor", objRmi);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }
}
