
package ProgConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceBiodiesel extends Remote{
    public void recebeDiesel(double diesel) throws RemoteException;
}
