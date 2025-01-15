
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
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
public class ServidorTcp {
    public void executar(){
        try{
            ServerSocket server = new ServerSocket(6666);
            JOptionPane.showMessageDialog(null, "Servidor iniciado.");
            
            String msg = JOptionPane.showInputDialog("Mensagem a enviar:");
            Socket cliente = server.accept();
            ObjectOutputStream writer = new ObjectOutputStream(cliente.getOutputStream());
            writer.flush();
            writer.writeUTF("IP do cliente: " + cliente.getInetAddress().getAddress()[0] 
                    + "." + cliente.getInetAddress().getAddress()[1] 
                    + "." + cliente.getInetAddress().getAddress()[2] 
                    + "." + cliente.getInetAddress().getAddress()[3] 
                    + " in " + msg);
            writer.close();
            cliente.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro no servidor: " + e.getMessage());
        }  
    }
    
    public static void main(String args[]){
        ServidorTcp objServidor = new ServidorTcp();
        objServidor.executar();
    }
}
