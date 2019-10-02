
package ProgConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceTanqueOleo extends Remote {
    public void recebeOleo(double oleo) throws RemoteException;
    public double enviaOleo(double oleo) throws RemoteException;
}
