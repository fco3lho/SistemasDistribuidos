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
public class Worker2 extends UnicastRemoteObject implements WorkerInterface {
    private String workerName;

    protected Worker2() throws RemoteException {
        super();
        this.workerName = "Servidor 2";
    }

    @Override
    public String processRequest(String request) throws RemoteException {
        System.out.println(workerName + " processando: " + request);
        return "Mensagem \"" + request + "\" processada pelo " + workerName;
    }
}
