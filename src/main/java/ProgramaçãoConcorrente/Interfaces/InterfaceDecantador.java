
package ProgramaçãoConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceDecantador extends Remote{
    public boolean recebeProduto(int produto) throws RemoteException;
}
