
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Felipe Campos
 */
public class Socket03_DST {
    public static void main(String[] args) {
        try{
            InetAddress mCastAddress = InetAddress.getByName("239.0.0.1"); // IP do grupo multicast
            InetSocketAddress group = new InetSocketAddress(mCastAddress, 6666); // Cria um socket para o grupo
            NetworkInterface netIf = NetworkInterface.getByName("Wi-Fi"); // Dados da interface d erede para o socket
            MulticastSocket s = new MulticastSocket(group.getPort()); // Associa interface de rede ao socket
            s.joinGroup(group, netIf); //Registra o host no group
            
            byte[] buffer = new byte[1000];
            DatagramPacket recv = new DatagramPacket(buffer, buffer.length);
            s.receive(recv);
            JOptionPane.showMessageDialog(null, new String(buffer));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
