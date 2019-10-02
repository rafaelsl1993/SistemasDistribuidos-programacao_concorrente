
package ProgConcorrente.Processos;

import ProgConcorrente.Interfaces.InterfaceBiodiesel;
import ProgConcorrente.Utils.Constantes;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Biodiesel extends UnicastRemoteObject implements InterfaceBiodiesel{
    private static double biodiesel;
    
    public Biodiesel() throws RemoteException{

    }
    
    public void rodarServer(){
        Biodiesel.biodiesel = 0;
        Constantes constantes = Constantes.getInstance();

        try{
            Biodiesel remotobiodiesel = new Biodiesel();
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(constantes.PORTABIODIESEL));
            registry.bind(constantes.BIODIESEL, remotobiodiesel);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("TanqueBioDiesel: Servidor rodando...");
    }
    
    @Override
    public void recebeDiesel(double biodiesel) {
        Biodiesel.biodiesel = Biodiesel.biodiesel + biodiesel;
        System.out.println("TanqueBioDiesel: recebe = " + biodiesel + ", contem= " + Biodiesel.biodiesel);
    }
    
}
