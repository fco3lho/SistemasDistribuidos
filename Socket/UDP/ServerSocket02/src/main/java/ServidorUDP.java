
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Felipe Campos
 */
public class ServidorUDP {
    public static void main(String args[]) throws SocketException, IOException{
        try{
            int port = 6789;
            DatagramSocket ds = new DatagramSocket(port);
            System.out.println("Ouvindo a porta: " + port);

            byte[] msg = new byte[256];
            DatagramPacket pkg = new DatagramPacket(msg, msg.length);

            ds.receive(pkg);
            ds.close();

            JOptionPane.showMessageDialog(null, new String(pkg.getData()).trim());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
