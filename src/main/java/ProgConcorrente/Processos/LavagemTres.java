
package ProgConcorrente.Processos;

import ProgConcorrente.Interfaces.InterfaceSecador;
import ProgConcorrente.Utils.Constantes;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import ProgConcorrente.Interfaces.InterfaceLavagemTres;
import java.time.Duration;
import java.time.Instant;

public class LavagemTres extends Server implements InterfaceLavagemTres{
    private static int produto;
    private Constantes constantes = null;
    private InterfaceSecador remotoSecador = null;
    
    public LavagemTres() throws RemoteException{

    }
    
    public void rodarServer(){
        LavagemTres.produto = 0;
        constantes = Constantes.getInstance();

        try{
            LavagemTres tanque = new LavagemTres();
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(constantes.PORTALAVAGEMTRES));
            registry.bind(constantes.LAVAGEMTRES, tanque);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("LavagemTres: Servidor rodando...");
    }
    
    public void rodarClient()throws NotBoundException, MalformedURLException, RemoteException{
        String url = constantes.RMIIP +  constantes.DPONTOS + constantes.PORTASECADOR 
                        + constantes.BARRA + constantes.SECADOR;
        
        remotoSecador = (InterfaceSecador)Naming.lookup(url);
        
        System.out.println("LavagemTres: Conectado ao Secador...");
    }
    
    public Runnable rodarThread = new Runnable(){
        public void run(){
            while(true){
                Instant inicio = Instant.now();
                while(Duration.between(inicio,Instant.now()).toMillis() < 500){
                    //Esperando meio segundos até a próxima entrega de óleo;
                }
                if(LavagemTres.produto > 0){
                    try {
                        remotoSecador.recebeSecagem((LavagemTres.produto * 0.97));
                        System.out.println("LavagemTres: lavou " +  (LavagemTres.produto * 0.97));
                        LavagemTres.produto = 0;
                    } catch (RemoteException ex) {
                        Logger.getLogger(LavagemUm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    };
    
    @Override
    public void recebeLavagem(double produto) {
        LavagemTres.produto += produto;
    }
    
}
