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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Felipe Campos
 */
public class QuizServer extends UnicastRemoteObject implements QuizService {

    public QuizServer() throws RemoteException{
        super();
    }

    @Override
    public boolean atualizaRanking(String msg) throws RemoteException {        
        boolean bret = false;
        
        try{
            File arquivo = new File("C:\\SistemasDistribuidos\\Quizz\\ranking.txt");
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
    public List<String[]> recuperaRanking() throws RemoteException {
        List<String[]> ranking = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\SistemasDistribuidos\\Quizz\\ranking.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(":");
                ranking.add(dados);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ranking;
        }

        // Ordena o ranking do maior para o menor
        ranking.sort((a, b) -> Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1])));
        
        return ranking;
    }
}
