
package Programa��oConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceLavagem extends Remote{
    public void recebeLavagem(int produto) throws RemoteException;
}
