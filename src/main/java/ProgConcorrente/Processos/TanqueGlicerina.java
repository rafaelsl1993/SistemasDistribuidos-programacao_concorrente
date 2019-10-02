
package ProgConcorrente.Processos;

import ProgConcorrente.Interfaces.InterfaceTanqueGlicerina;
import ProgConcorrente.Utils.Constantes;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class TanqueGlicerina extends UnicastRemoteObject implements InterfaceTanqueGlicerina{
    private double glicerina;
    
    public TanqueGlicerina() throws RemoteException{
        /*super(Constantes.getInstance().TANQUEGLICERINA, 
                Integer.parseInt(Constantes.getInstance().PORTATANQUEGLICERINA));
        glicerina = 0;*/
    }
    
    public void rodarServer(){
        glicerina = 0;
        Constantes constantes = Constantes.getInstance();

        try{
            TanqueGlicerina tanque = new TanqueGlicerina();
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(constantes.PORTATANQUEGLICERINA));
            registry.bind(constantes.TANQUEGLICERINA, tanque);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("TanqueGlicerina: Servidor rodando...");
    }

    @Override
    public void recebeGlicerina(double glicerina) {
        this.glicerina += glicerina;
        System.out.println("TanqueGlicerina recebe = " + glicerina);
    }

}
