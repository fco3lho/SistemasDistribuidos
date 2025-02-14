/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pacote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Felipe Campos
 */
public interface LoadBalancerInterface extends Remote {
    String forwardRequest(String request) throws RemoteException;
}
