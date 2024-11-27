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
public interface rmi_interface extends Remote {
    public String Somar(int v1, int v2) throws RemoteException;
}
