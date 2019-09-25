
package ProgramaçãoConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceTanqueOleo extends Remote {
    public void recebeOleo(int oleo) throws RemoteException;
    public int enviaOleo(int oleo) throws RemoteException;
}
