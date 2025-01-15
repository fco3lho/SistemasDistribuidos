
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Felipe Campos
 */
public class ClienteTCP {
    public void executar(){
        try{
            Socket cliente = new Socket("127.0.0.1", 6666);
            ObjectInputStream reader = new ObjectInputStream(cliente.getInputStream());
            
            JOptionPane.showMessageDialog(null, "Mensagem recebida: " + reader.readUTF());
            
            reader.close();
            cliente.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro no cliente: " + e.getMessage());
        }
    }
    
    public static void main(String args[]){
        ClienteTCP ObjCliente = new ClienteTCP();
        ObjCliente.executar();
    }
}
