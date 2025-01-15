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
    public String Somar(double v1, double v2) throws RemoteException {
        String retorno = "Não foi possível somar.";
        try{
            retorno = "O resultado da soma é: " + (v1+v2);
        } catch (Exception e){
            throw new RemoteException("Erro: " + e.getMessage());
        }
        return retorno;
    }
    
    @Override
    public String Subtrair(double v1, double v2) throws RemoteException {
        String retorno = "Não foi possível subtrair.";
        try{
            retorno = "O resultado da subtração é: " + (v1-v2);
        } catch (Exception e){
            throw new RemoteException("Erro: " + e.getMessage());
        }
        return retorno;
    }
    
    @Override
    public String Multiplicar(double v1, double v2) throws RemoteException {
        String retorno = "Não foi possível multiplicar.";
        try{
            retorno = "O resultado da multiplicação é: " + (v1*v2);
        } catch(Exception e){
            throw new RemoteException("Erro: " + e.getMessage());
        }
        return retorno;
    }
    
    @Override
    public String Dividir(double v1, double v2) throws RemoteException{
        String retorno = "Não foi possível dividir.";
        
        if(v2 == 0){
            retorno = "O denominador não pode ser 0.";
            return retorno;
        }
        
        try{
            retorno = "O resultado da divisão é: " + (v1/v2);
        } catch(Exception e){
            throw new RemoteException("Erro: " + e.getMessage());
        }
        return retorno;
    }
}
