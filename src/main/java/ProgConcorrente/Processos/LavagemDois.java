
package ProgConcorrente.Processos;

import ProgConcorrente.Utils.Constantes;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import ProgConcorrente.Interfaces.InterfaceLavagemDois;
import ProgConcorrente.Interfaces.InterfaceLavagemTres;
import java.time.Duration;
import java.time.Instant;

public class LavagemDois extends Server implements InterfaceLavagemDois{
    private static int produto;
    private Constantes constantes = null;
    private InterfaceLavagemTres remotoLavagemTres = null;
    
    public LavagemDois() throws RemoteException{

    }
    
    public void rodarServer(){
        LavagemDois.produto = 0;
        constantes = Constantes.getInstance();

        try{
            LavagemDois tanque = new LavagemDois();
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(constantes.PORTALAVAGEMDOIS));
            registry.bind(constantes.LAVAGEMDOIS, tanque);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("LavagemDois: Servidor rodando...");
    }
    
    public void rodarClient()throws NotBoundException, MalformedURLException, RemoteException{
        String url = constantes.RMIIP +  constantes.DPONTOS + constantes.PORTALAVAGEMTRES 
                        + constantes.BARRA + constantes.LAVAGEMTRES;
        
        remotoLavagemTres = (InterfaceLavagemTres)Naming.lookup(url);
        
        System.out.println("LavagemDois: Conectado ao LavagemTres...");
    }
    
    public Runnable rodarThread = new Runnable(){
        public void run(){
            while(true){
                Instant inicio = Instant.now();
                while(Duration.between(inicio,Instant.now()).toMillis() < 500){
                    //Esperando meio segundos até a próxima entrega de óleo;
                }
                if(LavagemDois.produto > 0){
                    try {
                        remotoLavagemTres.recebeLavagem( (LavagemDois.produto * 0.97));
                        System.out.println("LavagemDois: lavou " +  (LavagemDois.produto * 0.97));
                        LavagemDois.produto = 0;
                    } catch (RemoteException ex) {
                        Logger.getLogger(LavagemUm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    };
    
    @Override
    public void recebeLavagem(double produto) {
        LavagemDois.produto += produto;
    }
    
}
