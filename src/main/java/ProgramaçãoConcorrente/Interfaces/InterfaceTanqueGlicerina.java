
package ProgramaçãoConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceTanqueGlicerina extends Remote {
    public void recebeGlicerina(int glicerina) throws RemoteException;
}
