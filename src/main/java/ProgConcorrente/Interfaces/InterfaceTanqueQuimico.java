
package ProgConcorrente.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceTanqueQuimico extends Remote{
    public double enviaNaoh(double naoh) throws RemoteException;
    public double enviaEtoh(double etoh) throws RemoteException;
    public void recebeEtoh(double etoh) throws RemoteException;
}
