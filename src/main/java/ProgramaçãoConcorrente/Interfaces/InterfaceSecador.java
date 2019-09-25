
package ProgramaçãoConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceSecador extends Remote {
    public void recebeSecagem(float produto) throws RemoteException;
}
