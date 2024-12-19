/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pacote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Felipe Campos
 */
public interface QuizService extends Remote{
    public boolean atualizaRanking(String msg) 
        throws RemoteException;
        
    public List<String[]> recuperaRanking()
        throws RemoteException;
}
