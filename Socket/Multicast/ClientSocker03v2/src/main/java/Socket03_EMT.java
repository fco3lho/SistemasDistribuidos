
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
        try {
            // Definir as marcas para enviar
            String[] marcas = {"Coca-Cola", "Google", "Americanas"};
            InetAddress addr = InetAddress.getByName("239.0.0.1");
            DatagramSocket ds = new DatagramSocket();
            
            // Criar e iniciar a thread para enviar as mensagens
            Thread senderThread = new SenderThread(marcas, addr, ds);
            senderThread.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}

class SenderThread extends Thread {
    private final String[] marcas;
    private final InetAddress addr;
    private final DatagramSocket ds;

    public SenderThread(String[] marcas, InetAddress addr, DatagramSocket ds) {
        this.marcas = marcas;
        this.addr = addr;
        this.ds = ds;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (String marca : marcas) {
                    byte[] b = marca.getBytes();
                    DatagramPacket pkg = new DatagramPacket(b, b.length, addr, 6667);
                    
                    ds.send(pkg);
                    System.out.println("Anuncio enviado: " + marca);
                    
                    Thread.sleep(2000); // Atraso de 2 segundos
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
