package encyclopedia;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EncyclopediaImpl extends UnicastRemoteObject implements Encyclopedia {
    private static final long serialVersionUID = 1L;
    private final String data;
    private final ExecutorService executor;

    public EncyclopediaImpl(String data) throws RemoteException {
        super();
        this.data = data;
        this.executor = Executors.newFixedThreadPool(5);
    }

    @Override
    public int count() throws RemoteException {
        executor.submit(() -> {
            int count = data.length();
            System.out.println("Count: " + count);
        });
        return data.length();
    }

    @Override
public String[] repeatedWords() throws RemoteException {
    executor.submit(() -> {
        // This is the task submitted to the executor
        String[] words = data.split("\\s+");
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            if (wordCounts.containsKey(word)) {
                wordCounts.put(word, wordCounts.get(word) + 1);
            } else {
                wordCounts.put(word, 1);
            }
        }
        String[] repeatedWords = wordCounts.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toArray(String[]::new);
        System.out.println("Repeated Words: " + Arrays.toString(repeatedWords));
    });

    // This part executes concurrently with the task submitted above
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
            .map(Map.Entry::getKey)
            .toArray(String[]::new);
}


    @Override
    public String longest() throws RemoteException {
        executor.submit(() -> {
            String[] words = data.split("\\s+");
            String longestWord = Arrays.stream(words)
                    .max((a, b) -> a.length() - b.length())
                    .orElse("");
            System.out.println("Longest Word: " + longestWord);
        });
        String[] words = data.split("\\s+");
        return Arrays.stream(words)
                .max((a, b) -> a.length() - b.length())
                .orElse("");
    }

    @Override
    public String shortest() throws RemoteException {
        executor.submit(() -> {
            String[] words = data.split("\\s+");
            String shortestWord = Arrays.stream(words).min((a, b) -> a.length() - b.length())
                    .orElse("");
            System.out.println("Shortest Word: " + shortestWord);
        });
        String[] words = data.split("\\s+");
        return Arrays.stream(words)
                .min((a, b) -> a.length() - b.length())
                .orElse("");
    }

    @Override
    public Map<String, Integer> repeat() throws RemoteException {
        executor.submit(() -> {
            String[] words = data.split("\\s+");
            Map<String, Integer> wordCounts = new HashMap<>();
            for (String word : words) {
                if (wordCounts.containsKey(word)) {
                    wordCounts.put(word, wordCounts.get(word) + 1);
                } else {
                    wordCounts.put(word, 1);
                }
            }
            System.out.println("Word Counts: " + wordCounts);
    });
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