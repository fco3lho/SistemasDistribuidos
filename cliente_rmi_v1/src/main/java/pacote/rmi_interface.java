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
    public String Somar(double v1, double v2) throws RemoteException;
    public String Subtrair(double v1, double v2) throws RemoteException;
    public String Dividir(double v1, double v2) throws RemoteException;
    public String Multiplicar(double v1, double v2) throws RemoteException;
}
