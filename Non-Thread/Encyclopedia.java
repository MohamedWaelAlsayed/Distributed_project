package encyclopedia;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface Encyclopedia extends Remote {
    int count() throws RemoteException;

    String[] repeatedWords() throws RemoteException;

    String longest() throws RemoteException;

    String shortest() throws RemoteException;

    Map<String, Integer> repeat() throws RemoteException;
}
