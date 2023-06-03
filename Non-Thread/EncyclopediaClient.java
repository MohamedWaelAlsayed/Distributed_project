
package encyclopedia;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Map;

public class EncyclopediaClient {
     public static void main(String[] args) {
         try{
            Encyclopedia encyclopedia = (Encyclopedia) Naming.lookup("rmi://localhost/Encyclopedia");

            long startTime = System.currentTimeMillis();

            int count = encyclopedia.count();
            System.out.println("Number of letters: " + count);

            String[] repeatedWords = encyclopedia.repeatedWords();
            System.out.println("Repeated words: " + String.join(", ", repeatedWords));

            String longest = encyclopedia.longest();
            System.out.println("Longest word: " + longest);

            String shortest = encyclopedia.shortest();
            System.out.println("Shortest word: " + shortest);

            Map<String, Integer> repeat = encyclopedia.repeat();
            System.out.println("Repeat count: ");
            repeat.forEach((key, value) -> System.out.println(key + " : " + value));

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            System.out.println("Elapsed time: " + elapsedTime + " ms");
         }catch (MalformedURLException | NotBoundException | RemoteException e){
            System.err.println("Client exception: " + e.toString());
         }
     }
}
