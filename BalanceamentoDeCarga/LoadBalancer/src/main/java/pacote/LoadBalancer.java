/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe Campos
 */
public class LoadBalancer extends UnicastRemoteObject implements LoadBalancerInterface{
    private List<WorkerInterface> servers = new ArrayList<>();
    private int currentIndex = 0;

    public LoadBalancer() throws RemoteException {
        try {
            servers.add((WorkerInterface) Naming.lookup("rmi://localhost:4567/worker1"));
            servers.add((WorkerInterface) Naming.lookup("rmi://localhost:4567/worker2"));
            servers.add((WorkerInterface) Naming.lookup("rmi://localhost:4567/worker3"));
            System.out.println("Load Balancer iniciado com " + servers.size() + " workers.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String forwardRequest(String request) {
        if (servers.isEmpty()) {
            return "Nenhum servidor disponível!";
        }
        
        WorkerInterface server = servers.get(currentIndex);
        currentIndex = (currentIndex + 1) % servers.size(); // Round Robin
        
        try {
            return server.processRequest(request);
        } catch (Exception e) {
            return "Erro ao processar a requisição: " + e.getMessage();
        }
    }

    public static void main(String[] args) throws RemoteException {
        LoadBalancer lb = new LoadBalancer();
        // Pode-se testar enviando requisições a partir do balanceador
        System.out.println(lb.forwardRequest("Teste 1"));
        System.out.println(lb.forwardRequest("Teste 2"));
        System.out.println(lb.forwardRequest("Teste 3"));
    }
}
