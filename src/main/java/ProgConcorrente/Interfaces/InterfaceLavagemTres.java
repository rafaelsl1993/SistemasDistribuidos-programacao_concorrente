
package ProgConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceLavagemTres extends Remote{
    public void recebeLavagem(double produto) throws RemoteException;
}
