/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Felipe Campos
 */
public class Worker1 extends UnicastRemoteObject implements WorkerInterface {
    private String workerName;

    protected Worker1() throws RemoteException {
        super();
        this.workerName = "Servidor 1";
    }

    @Override
    public String processRequest(String request) throws RemoteException {
        System.out.println(workerName + " processando: " + request);
        return "Mensagem \"" + request + "\" processada pelo " + workerName;
    }
}
