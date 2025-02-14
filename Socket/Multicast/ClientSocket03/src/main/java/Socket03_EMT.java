
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
public class Socket03_EMT {
    public static void main(String[] args) {
        try{
            byte[] b = JOptionPane.showInputDialog("Mensagem a enviar: ").getBytes();
            InetAddress addr = InetAddress.getByName("239.0.0.1");
            DatagramPacket pkg = new DatagramPacket(b, b.length, addr, 6666);
            DatagramSocket ds = new DatagramSocket();
            
            ds.send(pkg);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
