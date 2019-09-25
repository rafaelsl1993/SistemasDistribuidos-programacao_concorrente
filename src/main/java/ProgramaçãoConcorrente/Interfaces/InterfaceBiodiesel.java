
package ProgramaçãoConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceBiodiesel extends Remote{
    public void recebeDiesel(int diesel) throws RemoteException;
}
