
package ProgConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceSecadorEtoh extends Remote {
    public void recebeSecagem(double produto) throws RemoteException;
}
