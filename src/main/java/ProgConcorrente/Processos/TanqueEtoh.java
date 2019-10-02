
package ProgConcorrente.Processos;

import ProgConcorrente.Interfaces.InterfaceTanqueEtoh;
import ProgConcorrente.Interfaces.InterfaceTanqueQuimico;
import ProgConcorrente.Utils.Constantes;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TanqueEtoh extends UnicastRemoteObject implements InterfaceTanqueEtoh{
    private static double etoh;
    private Constantes constantes = null;
    private InterfaceTanqueQuimico remotoTanqueQuimico = null;

    
    public TanqueEtoh() throws RemoteException{
        /*super(Constantes.getInstance().TANQUEETOH, 
                Integer.parseInt(Constantes.getInstance().PORTATANQUEETOH));
        constantes = Constantes.getInstance();
        this.etoh = 0;*/
    }
    
    public void rodarServer(){
        TanqueEtoh.etoh = 0;
        constantes = Constantes.getInstance();
        try{
            TanqueEtoh tanque = new TanqueEtoh();
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(constantes.PORTATANQUEETOH));
            registry.bind(constantes.TANQUEETOH, tanque);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("TanqueEtoh: Servidor rodando...");
    }

    public void rodarClient() throws NotBoundException, MalformedURLException, RemoteException{
        String url = constantes.RMIIP + constantes.DPONTOS + constantes.PORTATANQUEQUIMICO
                        + constantes.BARRA + constantes.TANQUEQUIMICO;
        
        remotoTanqueQuimico = (InterfaceTanqueQuimico) Naming.lookup(url);


        System.out.println("TanqueEtoh: Conectado ao TanqueQuimico...");
    }
    
    public Runnable rodarThread = new Runnable(){
        public void run(){
            Instant inicio;
            while(true){
                inicio = Instant.now();
                while(Duration.between(inicio,Instant.now()).toMillis() < 2000){
                    //Esperando 2 segundos até a próxima entrega de etoh;
                }
                try {
                    remotoTanqueQuimico.recebeEtoh(TanqueEtoh.etoh);
                    System.out.println("TanqueEtoh: Enviando EtOH...");
                    TanqueEtoh.etoh = 0;
                } catch (RemoteException ex) {
                    Logger.getLogger(TanqueEtoh.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };

    @Override
    public void recebeEtoh(double etoh) {
        TanqueEtoh.etoh += etoh;
    }
    
}
