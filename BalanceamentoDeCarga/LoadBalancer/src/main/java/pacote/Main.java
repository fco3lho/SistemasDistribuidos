/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Felipe Campos
 */
public class Main {
    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "localhost");
            Registry registry = null;
            
            try {
                registry = LocateRegistry.createRegistry(4567);
                System.out.println("Registry criado na porta 4567.");
            } catch (RemoteException e) {
                System.out.println("Registry já está em execução. Obtendo a instância existente.");
                registry = LocateRegistry.getRegistry();
            }
            
            // Cria e vincula os workers
            Worker1 w1 = new Worker1();
            Worker2 w2 = new Worker2();
            Worker3 w3 = new Worker3();
            
            registry.rebind("worker1", w1);
            registry.rebind("worker2", w2);
            registry.rebind("worker3", w3);
            
            System.out.println("Workers vinculados no RMI Registry.");
            
            // Cria e vincula o Load Balancer
            LoadBalancer lb = new LoadBalancer();
            registry.rebind("loadBalancer", lb);
            
            System.out.println("Load Balancer vinculado no RMI Registry.");            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
