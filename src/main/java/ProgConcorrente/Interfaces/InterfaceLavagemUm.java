
package ProgConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceLavagemUm extends Remote{
    public void recebeLavagem(double produto) throws RemoteException;
}
