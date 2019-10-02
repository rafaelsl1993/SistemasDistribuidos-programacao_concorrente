
package ProgConcorrente.Processos;

import ProgConcorrente.Interfaces.InterfaceDecantador;
import ProgConcorrente.Interfaces.InterfaceTanqueOleo;
import ProgConcorrente.Interfaces.InterfaceTanqueQuimico;
import ProgConcorrente.Utils.Constantes;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reator {
    private static Constantes constantes = null;
    private InterfaceTanqueOleo remotoTanqueOleo = null;
    private InterfaceTanqueQuimico remotoTanqueQuimico = null;
    private InterfaceDecantador remotoDecantador = null;

    public Reator(){
        constantes = Constantes.getInstance();
    }

    public void rodarClient() throws RemoteException, NotBoundException, MalformedURLException{
        String resposta = new String();

        String url = constantes.RMIIP + constantes.DPONTOS + constantes.PORTATANQUEOLEO
                        + constantes.BARRA + constantes.TANQUEOLEO;
        
        remotoTanqueOleo = (InterfaceTanqueOleo) Naming.lookup(url);
        
        System.out.println("Reator: Conectado ao TanqueOleo...");
        
        url = constantes.RMIIP + constantes.DPONTOS + constantes.PORTATANQUEQUIMICO
                        + constantes.BARRA + constantes.TANQUEQUIMICO;
        
        remotoTanqueQuimico = (InterfaceTanqueQuimico) Naming.lookup(url);
        
        System.out.println("Reator: Conectado ao TanqueQuimico...");
        
        url = constantes.RMIIP + constantes.DPONTOS + constantes.PORTADECANTADOR
                        + constantes.BARRA + constantes.DECANTADOR;
        
        remotoDecantador = (InterfaceDecantador) Naming.lookup(url);
        
        System.out.println("Reator: Conectado ao Decantador...");
        
    }
    
    public Runnable rodarThread = new Runnable(){
        public void run(){
            double oleo = 0;
            double etoh = 0;
            double naoh = 0;
            boolean tanqueNaoCheio = true;

            while(true){
                try {
                    naoh = remotoTanqueQuimico.enviaNaoh(( 0.476));
                    etoh = remotoTanqueQuimico.enviaEtoh( (naoh * 4.002));
                    if(etoh < (naoh * 4.002)){
                        System.out.println("Reator: esperando EtOH...");
                        waiting(500);
                        etoh += remotoTanqueQuimico.enviaEtoh((naoh * 4.002) - etoh);
                    }
                    oleo = remotoTanqueOleo.enviaOleo( (naoh * 100.040));
                    if(oleo < (naoh * 100.040)){
                        System.out.println("Reator: esperando Oleo...");
                        waiting(500);
                        oleo += remotoTanqueQuimico.enviaEtoh((naoh * 100.040) - oleo);
                    }
                } catch (RemoteException ex) {
                    System.out.println("Reator: problema recebendo produtos");
                    Logger.getLogger(Reator.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Reator: Ativado...");
                
                waiting(1000);

                try {
                    do{
                        System.out.println("Reator: Enviando ao Decantador...");
                        tanqueNaoCheio = remotoDecantador.recebeProduto(naoh + etoh + oleo);
                        waiting(1000);
                    }while(!tanqueNaoCheio);
                } catch (RemoteException ex) {
                    Logger.getLogger(Reator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };
    
    
    private void waiting(int time){
        Instant inicio = Instant.now();
                while(Duration.between(inicio,Instant.now()).toMillis() < time){
                    //Esperando 'time' milisegundo para processar;
        }
    }
}
