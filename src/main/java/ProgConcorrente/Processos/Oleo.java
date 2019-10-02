
package ProgConcorrente.Processos;

import ProgConcorrente.Interfaces.InterfaceTanqueOleo;
import ProgConcorrente.Utils.Constantes;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Oleo {
    private static Constantes constantes = null;
    private InterfaceTanqueOleo remotoTanque = null;

    public Oleo(){
        constantes = Constantes.getInstance();
    }

    public void rodarClient()throws NotBoundException, MalformedURLException, RemoteException{
        String url = constantes.RMIIP +  constantes.DPONTOS + constantes.PORTATANQUEOLEO 
                        + constantes.BARRA + constantes.TANQUEOLEO;
        
        remotoTanque = (InterfaceTanqueOleo)Naming.lookup(url);
        
        System.out.println("Oleo: Conectado ao TanqueOleo...");
    }
    
    public Runnable rodarThread = new Runnable(){
        public void run(){
            Instant inicio;
            double oleoAleatorio;
            Random gerador = new Random();
            
            while(true){
                oleoAleatorio = gerador.nextInt(100) + 101;
                
                try {
                    //System.out.println("Oleo: Enviando = " + oleoAleatorio);
                    remotoTanque.recebeOleo(oleoAleatorio);
                } catch (RemoteException ex) {
                    Logger.getLogger(Oleo.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Oleo: Problemas ao enviar óleo ao TanqueOleo");
                }
                
                inicio = Instant.now();
                while(Duration.between(inicio,Instant.now()).toMillis() < 5000){
                    //Esperando 5 segundos até a próxima entrega de óleo;
                }
            }
        }
    };
    
}
