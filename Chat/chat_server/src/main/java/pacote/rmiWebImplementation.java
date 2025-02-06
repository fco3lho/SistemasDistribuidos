/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author Felipe Campos
 */
public class rmiWebImplementation extends UnicastRemoteObject implements rmiWebInterface{
    
    public rmiWebImplementation() throws RemoteException{
        super();
    }

    @Override
    public boolean gravaMsg(String msg) throws RemoteException {        
        boolean bret = false;
        
        try{
            File arquivo = new File("C:\\SistemasDistribuidos\\arquivo.txt");
            FileWriter escritor = new FileWriter(arquivo, true);

            escritor.write(msg);
            escritor.close();
            
            bret = true;
        }
        catch (Exception e) {
            bret = false;
            e.printStackTrace();
        }
        
        return bret;
    }

    @Override
    public String recuperaMsgs() throws RemoteException {
        String sRet = "";
        
        try{
            File arquivo = new File("C:\\SistemasDistribuidos\\arquivo.txt");
            try (FileReader leitor = new FileReader(arquivo)) {
                BufferedReader buffer = new BufferedReader(leitor);
                
                while(buffer.ready()){
                    sRet += buffer.readLine();
                }
            }
        }
        catch (IOException e) {
            sRet = "Mensagens indisponiveis - Erro no repositório";
            e.printStackTrace();
        }
        
        return sRet;
    }
}
