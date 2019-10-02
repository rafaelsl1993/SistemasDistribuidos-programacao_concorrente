
package ProgConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceDecantador extends Remote{
    public boolean recebeProduto(double produto) throws RemoteException;
}
