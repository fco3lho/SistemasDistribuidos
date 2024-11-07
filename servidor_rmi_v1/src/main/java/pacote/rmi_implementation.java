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
public class rmi_implementation extends UnicastRemoteObject implements rmi_interface{

    public rmi_implementation() throws RemoteException{
        super();
    }
    
    @Override
    public String Somar(int v1, int v2) throws RemoteException {
        String retorno = "Não foi possível somar.";
        try{
            retorno = "A soma é: " + (v1+v2);
        } catch (Exception e){
            throw new RemoteException("Erro: " + e.getMessage());
        }
        return retorno;
    }    
}
