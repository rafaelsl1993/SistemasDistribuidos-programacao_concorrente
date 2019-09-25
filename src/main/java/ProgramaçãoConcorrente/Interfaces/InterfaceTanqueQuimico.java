
package ProgramaçãoConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceTanqueQuimico extends Remote{
    public float enviaNaoh(float naoh) throws RemoteException;
    public float enviaEtoh(float etoh) throws RemoteException;
    public void recebeEtoh(float etoh) throws RemoteException;
}
