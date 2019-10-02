
package ProgConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceTanqueGlicerina extends Remote {
    public void recebeGlicerina(double glicerina) throws RemoteException;
}
