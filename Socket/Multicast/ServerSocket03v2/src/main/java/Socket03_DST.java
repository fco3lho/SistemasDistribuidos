
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
        try {
            // Definir o endereço multicast e a porta
            InetAddress mCastAddress = InetAddress.getByName("239.0.0.1");
            InetSocketAddress group = new InetSocketAddress(mCastAddress, 6667);
            NetworkInterface netIf = NetworkInterface.getByName("Wi-Fi");

            // Criar e iniciar a thread do receptor
            Thread receiverThread = new ReceiverThread(group, netIf);
            receiverThread.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}

class ReceiverThread extends Thread {
    private final InetSocketAddress group;
    private final NetworkInterface netIf;

    public ReceiverThread(InetSocketAddress group, NetworkInterface netIf) {
        this.group = group;
        this.netIf = netIf;
    }

    @Override
    public void run() {
        try {
            MulticastSocket s = new MulticastSocket(group.getPort());
            s.joinGroup(group, netIf); // Registra o receptor no grupo multicast
            
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket recv = new DatagramPacket(buffer, buffer.length);
                s.receive(recv); // Recebe o pacote
                String message = new String(recv.getData(), 0, recv.getLength());
                
                // Exibe a mensagem recebida
                JOptionPane.showMessageDialog(null, message);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
