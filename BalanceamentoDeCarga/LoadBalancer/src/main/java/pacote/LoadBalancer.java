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
import java.util.Random;

/**
 *
 * @author Felipe Campos
 */
public class LoadBalancer extends UnicastRemoteObject implements LoadBalancerInterface{
    private List<WorkerInterface> servers = new ArrayList<>();
    private int currentIndex = 0;
    private Random random = new Random();
    private String strategy; // Estratégia definida no início ("roundrobin" ou "random")
    
    // Construtor que recebe a estratégia desejada
    public LoadBalancer(String strategy) throws RemoteException {
        super();
        this.strategy = strategy;
       
        try {
            // Realiza o lookup dos 3 workers utilizando a porta 4567
            servers.add((WorkerInterface) Naming.lookup("rmi://localhost:4567/worker1"));
            servers.add((WorkerInterface) Naming.lookup("rmi://localhost:4567/worker2"));
            servers.add((WorkerInterface) Naming.lookup("rmi://localhost:4567/worker3"));
            System.out.println("Load Balancer iniciado com " + servers.size() + " workers utilizando estrategia: " + strategy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Implementação da estratégia Round Robin
    private String forwardRequestRoundRobin(String request) {
        if (servers.isEmpty()) {
            return "Nenhum servidor disponível!";
        }
        WorkerInterface server = servers.get(currentIndex);
        currentIndex = (currentIndex + 1) % servers.size();
        try {
            return server.processRequest(request);
        } catch (Exception e) {
            return "Erro ao processar a requisição: " + e.getMessage();
        }
    }
    
    // Implementação da estratégia Random (aleatória)
    private String forwardRequestRandom(String request) {
        if (servers.isEmpty()) {
            return "Nenhum servidor disponível!";
        }
        int index = random.nextInt(servers.size());
        WorkerInterface server = servers.get(index);
        try {
            return server.processRequest(request);
        } catch (Exception e) {
            return "Erro ao processar a requisição: " + e.getMessage();
        }
    }
    
    // O método forwardRequest utiliza a estratégia definida ao iniciar o LoadBalancer
    @Override
    public String forwardRequest(String request) throws RemoteException {
        switch(strategy.toLowerCase()) {
            case "random":
                return forwardRequestRandom(request);
            case "roundrobin":
            default:
                return forwardRequestRoundRobin(request);
        }
    }
    
    public static void main(String[] args) throws RemoteException {
        LoadBalancer lb = new LoadBalancer("roundrobin");
        
        System.out.println(lb.forwardRequest("Teste 1"));
        System.out.println(lb.forwardRequest("Teste 2"));
        System.out.println(lb.forwardRequest("Teste 3"));
    }
}
