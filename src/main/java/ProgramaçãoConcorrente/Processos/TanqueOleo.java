
package ProgramaçãoConcorrente.Processos;

import ProgramaçãoConcorrente.Interfaces.InterfaceTanqueOleo;
import ProgramaçãoConcorrente.Utils.Constantes;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class TanqueOleo extends UnicastRemoteObject implements InterfaceTanqueOleo{
    private int oleo;
    
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
        System.out.println("Rodando: TanqueOleo");
    }
    
    @Override
    public void recebeOleo(int oleo) throws RemoteException{
        this.oleo += oleo;
        System.out.println("TanqueOleo recebe: " + oleo);
    }

    @Override
    public int enviaOleo(int oleo) throws RemoteException{
            if(this.oleo > oleo){
                this.oleo -= oleo;
            }else{
                oleo = this.oleo;
                this.oleo = 0;
            }
            System.out.println("TanqueOleo envia: " + oleo);
            return oleo;
    }

}
