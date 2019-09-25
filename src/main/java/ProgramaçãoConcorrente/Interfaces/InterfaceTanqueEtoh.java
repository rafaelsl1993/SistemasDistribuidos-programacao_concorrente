
package ProgramaçãoConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceTanqueEtoh extends Remote{
    public void recebeEtoh(float etoh) throws RemoteException;
}
