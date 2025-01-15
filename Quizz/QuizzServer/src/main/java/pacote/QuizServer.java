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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Felipe Campos
 */
public class QuizServer extends UnicastRemoteObject implements QuizService {
    
    private Map<Integer, String> perguntas = null;
    private Map<Integer, List<String>> alternativas = null;
    private Map<Integer, String> respostasCorretas = null;

    public QuizServer() throws RemoteException{
        super();
        
        perguntas = new HashMap<>();
        alternativas = new HashMap<>();
        respostasCorretas = new HashMap<>();

        perguntas.put(1, "Qual é a capital do Brasil?");
        alternativas.put(1, List.of("Brasilia", "Rio de Janeiro", "Sao Paulo", "Salvador"));
        respostasCorretas.put(1, "Brasilia");

        perguntas.put(2, "Qual é o planeta mais próximo do Sol?");
        alternativas.put(2, List.of("Mercurio", "Venus", "Terra", "Marte"));
        respostasCorretas.put(2, "Mercurio");

        perguntas.put(3, "Quem é o protagonista da série 'Todo mundo odeia o Chris'?");
        alternativas.put(3, List.of("Travis Flory", "Terry Crews", "Vincent Martella", "Tyler James"));
        respostasCorretas.put(3, "Tyler James");

        perguntas.put(4, "Qual dos carros é uma Ferrari");
        alternativas.put(4, List.of("296 GTB", "Jesko", "P1", "911 GT3"));
        respostasCorretas.put(4, "296 GTB");

        perguntas.put(5, "Qual a capital dos EUA?");
        alternativas.put(5, List.of("Washington", "Los Angeles", "New York", "Detroit"));
        respostasCorretas.put(5, "Washington");
    }

    @Override
    public boolean atualizaRanking(String msg) throws RemoteException {        
        boolean bret = false;
        
        try{
            File arquivo = new File("C:\\SistemasDistribuidos\\Quizz\\ranking.txt");
            FileWriter escritor = new FileWriter(arquivo, true);

            escritor.write(msg + "\n");
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

    @Override
    public String getPergunta(int numero) throws RemoteException {
        return perguntas.getOrDefault(numero, "Pergunta não encontrada");
    }

    @Override
    public List<String> getAlternativas(int numero) throws RemoteException {
        List<String> lista = new ArrayList<>(alternativas.getOrDefault(numero, Collections.emptyList()));
        Collections.shuffle(lista);
        return lista;
    }

    @Override
    public boolean verificarResposta(int numero, String resposta) throws RemoteException {
        return resposta != null && resposta.equals(respostasCorretas.get(numero));
    }
}
