
package ProgConcorrente.Processos;

import ProgConcorrente.Interfaces.InterfaceTanqueOleo;
import ProgConcorrente.Utils.Constantes;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class TanqueOleo extends UnicastRemoteObject implements InterfaceTanqueOleo{
    private double oleo;
    
    public TanqueOleo() throws RemoteException{
    }
    
    public void rodarServer(){
        oleo = 0;
        Constantes constantes = Constantes.getInstance();

        try{
            TanqueOleo tanque = new TanqueOleo();
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(constantes.PORTATANQUEOLEO));
            registry.bind(constantes.TANQUEOLEO, tanque);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("TanqueOleo: Servidor rodando...");
    }
    
    @Override
    public void recebeOleo(double oleo) throws RemoteException{
        this.oleo += oleo;
        System.out.println("TanqueOleo: recebe = " + oleo);
    }

    @Override
    public double enviaOleo(double oleo) throws RemoteException{
            if(this.oleo > oleo){
                this.oleo -= oleo;
            }else{
                oleo = this.oleo;
                this.oleo = 0;
            }
            System.out.println("TanqueOleo: envia = " + oleo);
            return oleo;
    }

}
