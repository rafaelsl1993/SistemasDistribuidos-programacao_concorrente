
package ProgConcorrente.Processos;

import ProgConcorrente.Interfaces.InterfaceTanqueQuimico;
import ProgConcorrente.Utils.Constantes;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.Instant;

public class TanqueQuimico extends UnicastRemoteObject implements InterfaceTanqueQuimico{
    private static double naoh;
    private static double etoh;
    
    public TanqueQuimico() throws RemoteException{

    }
    
    public void rodarServer(){
        TanqueQuimico.naoh = 0;
        TanqueQuimico.etoh = 0;
        
        Constantes constantes = Constantes.getInstance();
        
        try{
            TanqueQuimico tanque = new TanqueQuimico();
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(constantes.PORTATANQUEQUIMICO));
            registry.bind(constantes.TANQUEQUIMICO, tanque);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("TanqueQuimico: Servidor rodando...");
    }
    
    @Override
    public double enviaNaoh(double naoh) {
        if(TanqueQuimico.naoh > naoh){
            TanqueQuimico.naoh -= naoh;
            return naoh;
        }
        naoh = TanqueQuimico.naoh;
        TanqueQuimico.naoh = 0;
        return naoh;
    }
    
    @Override
    public double enviaEtoh(double etoh){
        if(TanqueQuimico.etoh > etoh){
            TanqueQuimico.etoh -= etoh;
            return etoh;
        }
        etoh = TanqueQuimico.etoh;
        TanqueQuimico.etoh = 0;
        return etoh;
    }

    @Override
    public void recebeEtoh(double etoh) {
        TanqueQuimico.etoh += etoh;
        System.out.println("TanqueQuimico: recebe = " + etoh);
    }
    
    public Runnable rodarThread = new Runnable(){
        public void run(){
            Instant inicio;
            while(true){
                TanqueQuimico.naoh = TanqueQuimico.naoh + 0.45;
                TanqueQuimico.etoh = TanqueQuimico.etoh + 1;
                inicio = Instant.now();
                while(Duration.between(inicio,Instant.now()).toMillis() < 1000){
                    //Esperando 1 segundos até a próxima entrega de óleo;
                }
                System.out.println("TanqueQuimico: NaOH = " + TanqueQuimico.naoh + ", EtOH = " + TanqueQuimico.etoh);
            }
        }
    };
    
}
