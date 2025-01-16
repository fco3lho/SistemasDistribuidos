
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Felipe Campos
 */
public class ClientUDP {
    public static void main(String args[]){
        try{
            InetAddress addr = InetAddress.getByName("200.128.141.35");
            int port = 6666;
            byte[] msg = JOptionPane.showInputDialog("Mensagem a enviar:").getBytes();
            DatagramPacket pkg = new DatagramPacket(msg, msg.length, addr, port);
            
            DatagramSocket ds = new DatagramSocket();
            ds.send(pkg);
            ds.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
