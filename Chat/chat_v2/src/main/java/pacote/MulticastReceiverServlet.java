/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pacote;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Felipe Campos
 */
@WebServlet("/multicastReceiver")
public class MulticastReceiverServlet extends HttpServlet {
    private static String lastMessage = "Nenhum anuncio";
    
    @Override
    public void init() throws ServletException {
        Thread listenerThread = new Thread(() -> {
            try {
                MulticastSocket socket = new MulticastSocket(6667);
                InetAddress group = InetAddress.getByName("239.0.0.1");
                socket.joinGroup(group);

                byte[] buffer = new byte[1000];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                while (true) {
                    socket.receive(packet);
                    lastMessage = new String(packet.getData(), 0, packet.getLength());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        listenerThread.setDaemon(true);
        listenerThread.start();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(lastMessage);
        out.flush();
    }
}
