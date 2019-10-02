
package ProgConcorrente.Processos;

import ProgConcorrente.Interfaces.InterfaceDecantador;
import ProgConcorrente.Interfaces.InterfaceTanqueGlicerina;
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
import ProgConcorrente.Interfaces.InterfaceLavagemUm;
import ProgConcorrente.Interfaces.InterfaceSecadorEtoh;

public class Decantador  extends UnicastRemoteObject implements InterfaceDecantador{
    private static double produto;
    private Constantes constantes = null;
    private InterfaceSecadorEtoh remotoSecador = null;
    private InterfaceLavagemUm remotoLavagem = null;
    private InterfaceTanqueGlicerina remotoTanqueGlicerina = null;

    
    public Decantador() throws RemoteException{

    }
    
    public void rodarClient()throws NotBoundException, MalformedURLException, RemoteException{
        String url = constantes.RMIIP +  constantes.DPONTOS + constantes.PORTASECADORETOH
                        + constantes.BARRA + constantes.SECADORETOH;
        
        remotoSecador = (InterfaceSecadorEtoh)Naming.lookup(url);
        
        System.out.println("Decantador: Conectado ao SecadorEtOH...");
        
        url = constantes.RMIIP +  constantes.DPONTOS + constantes.PORTALAVAGEM 
                        + constantes.BARRA + constantes.LAVAGEMUM;
        
        remotoLavagem = (InterfaceLavagemUm)Naming.lookup(url);
        
        System.out.println("Decantador: Conectado ao Lavagem...");
        
        url = constantes.RMIIP +  constantes.DPONTOS + constantes.PORTATANQUEGLICERINA 
                        + constantes.BARRA + constantes.TANQUEGLICERINA;
        
        remotoTanqueGlicerina = (InterfaceTanqueGlicerina)Naming.lookup(url);
        
        System.out.println("Decantador: Conectado ao TanqueGlicerina...");
    }
    
    public void rodarServer(){
        this.produto = 0;
        this.constantes = Constantes.getInstance();

        try{
            Decantador decantador = new Decantador();
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(constantes.PORTADECANTADOR));
            registry.bind(constantes.DECANTADOR, decantador);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Decantador: Servidor rodando...");
    }
    
    public Runnable rodarThread = new Runnable(){
        public void run(){
            double contador = 0;
            double envio = 0;
            while(true){
                waiting(500);
                if(produto > 0){
                    if(produto > 100){
                        envio = 100;
                    }else{
                        envio = produto;
                    }
                    try {
                        remotoSecador.recebeSecagem( (envio * 0.08));
                        remotoLavagem.recebeLavagem( (envio * 0.90));
                        remotoTanqueGlicerina.recebeGlicerina( (envio * 0.02));
                        contador += envio;
                        produto = produto - envio;
                        System.out.println("Decantador: Enviou = " + envio);
                        if(contador >= 100){
                            System.out.println("Decantador: Descançando...");
                            waiting(5000);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(Decantador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    };
    
    private void waiting(int t){
        Instant inicio = Instant.now();
        while(Duration.between(inicio,Instant.now()).toMillis() < t){
            //Esperando 5 segundos até a próxima entrega de óleo;
        }
    }
    
    @Override
    public boolean recebeProduto(double produto) {
        if(Decantador.produto + produto > constantes.LIMITEDECANTADOR){
            System.out.println("Decantador: Nivel máximo...");
            return false;
        }
        Decantador.produto += produto;
        System.out.println("Decantador: Recebendo = " + produto);
        return true;
    }
    
}
