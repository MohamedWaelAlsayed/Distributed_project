/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encyclopedia;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Ibrahim_Refa3i
 */
public class EncyclopediaImpl extends UnicastRemoteObject implements Encyclopedia {
    private static final long serialVersionUID = 1L;
    private final String data;
    
    public EncyclopediaImpl(String data) throws RemoteException {
        super();
        this.data = data;
    }

    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public int count() throws RemoteException {
        return data.length();
    }
    
    @Override
     public String[] repeatedWords() throws RemoteException {
        String[] words = data.split("\\s+");
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            if (wordCounts.containsKey(word)) {
                wordCounts.put(word, wordCounts.get(word) + 1);
            } else {
                wordCounts.put(word, 1);
            }
        }
        return wordCounts.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .toArray(String[]::new);
    }
     
    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
     public String longest() throws RemoteException {
        String[] words = data.split("\\s+");
        return Arrays.stream(words).max((a, b) -> a.length() - b.length()).orElse("");
    }

    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public String shortest() throws RemoteException {
        String[] words = data.split("\\s+");
        return Arrays.stream(words).min((a, b) -> a.length() - b.length()).orElse("");
    }

    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public Map<String, Integer> repeat() throws RemoteException {
        String[] words = data.split("\\s+");
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            if (wordCounts.containsKey(word)) {
                wordCounts.put(word, wordCounts.get(word) + 1);
            } else {
                wordCounts.put(word, 1);
            }
        }
        return wordCounts;
    }

    
}
