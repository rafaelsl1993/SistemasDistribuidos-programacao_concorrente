
package ProgramaçãoConcorrente.Processos;

import ProgramaçãoConcorrente.Interfaces.InterfaceSecador;
import ProgramaçãoConcorrente.Interfaces.InterfaceTanqueEtoh;
import ProgramaçãoConcorrente.Utils.Constantes;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class SecadorEtoh extends UnicastRemoteObject implements InterfaceSecador{
    private float etoh;
    private Constantes constantes = null;
    private InterfaceTanqueEtoh remotoTanqueEtoh = null;
    
    public SecadorEtoh() throws RemoteException{
        /*super(Constantes.getInstance().SECADORETOH,
                Integer.parseInt(Constantes.getInstance().PORTASECADOR));
        constantes = Constantes.getInstance();
        this.etoh = 0;*/
    }
    
    public void rodarServer(){
        etoh = 0;
        Constantes constantes = Constantes.getInstance();
        
        try{
            SecadorEtoh tanque = new SecadorEtoh();
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(constantes.PORTASECADOR));
            registry.bind(constantes.SECADORETOH, tanque);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Rodando: SecadorEtoh");
    }
    
    public void rodarClient(){
        String url = constantes.RMIIP + constantes.DPONTOS + constantes.PORTATANQUEETOH
                        + constantes.BARRA + constantes.TANQUEETOH;
        
        try {
            remotoTanqueEtoh = (InterfaceTanqueEtoh) Naming.lookup(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Secador Etoh Conectado...");

    }
    
    @Override
    public void recebeSecagem(float etoh) {
        this.etoh = etoh;
    }
    
}
