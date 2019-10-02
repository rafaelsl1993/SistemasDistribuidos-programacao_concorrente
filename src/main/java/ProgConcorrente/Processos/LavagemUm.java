
package ProgConcorrente.Processos;

import ProgConcorrente.Interfaces.InterfaceLavagemDois;
import ProgConcorrente.Utils.Constantes;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import ProgConcorrente.Interfaces.InterfaceLavagemUm;
import java.time.Duration;
import java.time.Instant;

public class LavagemUm extends Server implements InterfaceLavagemUm{
    private static double produto;
    private Constantes constantes = null;
    private InterfaceLavagemDois remotoLavagemDois = null;
    
    public LavagemUm() throws RemoteException{

    }
    
    public void rodarServer(){
        LavagemUm.produto = 0;
        constantes = Constantes.getInstance();

        try{
            LavagemUm tanque = new LavagemUm();
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(constantes.PORTALAVAGEM));
            registry.bind(constantes.LAVAGEMUM, tanque);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("LavagemUm: Servidor rodando...");
    }
    
    public void rodarClient()throws NotBoundException, MalformedURLException, RemoteException{
        String url = constantes.RMIIP +  constantes.DPONTOS + constantes.PORTALAVAGEMDOIS 
                        + constantes.BARRA + constantes.LAVAGEMDOIS;
        
        remotoLavagemDois = (InterfaceLavagemDois)Naming.lookup(url);
        
        System.out.println("LavagemUm: Conectado ao LavagemDois...");
    }
    
    public Runnable rodarThread = new Runnable(){
        public void run(){
            while(true){
                Instant inicio = Instant.now();
                while(Duration.between(inicio,Instant.now()).toMillis() < 500){
                    //Esperando meio segundos até a próxima entrega de óleo;
                }
                if(LavagemUm.produto>0){
                    try {
                        remotoLavagemDois.recebeLavagem( (LavagemUm.produto * 0.97));
                        System.out.println("LavagemUm: lavou " +  (LavagemUm.produto * 0.97));
                        LavagemUm.produto = 0;
                    } catch (RemoteException ex) {
                        Logger.getLogger(LavagemUm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    };
    
    @Override
    public void recebeLavagem(double produto) {
        LavagemUm.produto += produto;
               System.out.println("LavagemUm: recebeu = " + produto);

    }
    
}
