
package ProgramaçãoConcorrente.Processos;

import ProgramaçãoConcorrente.Interfaces.InterfaceTanqueEtoh;
import ProgramaçãoConcorrente.Interfaces.InterfaceTanqueQuimico;
import ProgramaçãoConcorrente.Utils.Constantes;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TanqueEtoh extends UnicastRemoteObject implements InterfaceTanqueEtoh{
    private float etoh;
    private Constantes constantes = null;
    private InterfaceTanqueQuimico remotoTanqueQuimico = null;

    
    public TanqueEtoh() throws RemoteException{
        /*super(Constantes.getInstance().TANQUEETOH, 
                Integer.parseInt(Constantes.getInstance().PORTATANQUEETOH));
        constantes = Constantes.getInstance();
        this.etoh = 0;*/
    }
    
    public void rodarServer(){
        etoh = 0;
        constantes = Constantes.getInstance();
        try{
            TanqueEtoh tanque = new TanqueEtoh();
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(constantes.PORTATANQUEETOH));
            registry.bind(constantes.TANQUEETOH, tanque);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Rodando: TanqueEtoh");
    }

    public void rodarClient() throws NotBoundException, MalformedURLException, RemoteException{
        String url = constantes.RMIIP + constantes.DPONTOS + constantes.PORTATANQUEQUIMICO
                        + constantes.BARRA + constantes.TANQUEQUIMICO;
        
        remotoTanqueQuimico = (InterfaceTanqueQuimico) Naming.lookup(url);

        System.out.println("TanqueEtoh Conectado...");
    }

    @Override
    public void recebeEtoh(float etoh) {
        try {
            //this.etoh += etoh;
            remotoTanqueQuimico.recebeEtoh(etoh);
        } catch (RemoteException ex) {
            Logger.getLogger(TanqueEtoh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
