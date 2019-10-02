
package ProgConcorrente.Processos;

import ProgConcorrente.Interfaces.InterfaceSecadorEtoh;
import ProgConcorrente.Interfaces.InterfaceTanqueEtoh;
import ProgConcorrente.Utils.Constantes;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.Instant;



public class SecadorEtoh extends UnicastRemoteObject implements InterfaceSecadorEtoh{
    private static double etoh;
    private Constantes constantes = null;
    private InterfaceTanqueEtoh remotoTanqueEtoh = null;
    
    public SecadorEtoh() throws RemoteException{
        /*super(Constantes.getInstance().SECADORETOH,
                Integer.parseInt(Constantes.getInstance().PORTASECADOR));
        constantes = Constantes.getInstance();
        this.etoh = 0;*/
    }
    
    public void rodarServer(){
        SecadorEtoh.etoh = 0;
        Constantes constantes = Constantes.getInstance();
        
        try{
            SecadorEtoh tanque = new SecadorEtoh();
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(constantes.PORTASECADORETOH));
            registry.bind(constantes.SECADORETOH, tanque);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("SecadorEtoh: Servidor rodando...");
    }
    
    public void rodarClient(){
        String url = constantes.RMIIP + constantes.DPONTOS + constantes.PORTATANQUEETOH
                        + constantes.BARRA + constantes.TANQUEETOH;
        
        try {
            remotoTanqueEtoh = (InterfaceTanqueEtoh) Naming.lookup(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("SecadorEtoh: Conectado ao TanqueEtOH...");

    }
    
    public Runnable rodarThread = new Runnable(){
        public void run(){
            double secando = 0;
            while(true){
                waiting(500);
                if(SecadorEtoh.etoh > 0){
                    secando = SecadorEtoh.etoh;
                    waiting(3000);
                    try {
                        remotoTanqueEtoh.recebeEtoh( (etoh * 0.97));
                        System.out.println("SecadorEtoh: secou: " + ( SecadorEtoh.etoh * 0.97));
                        SecadorEtoh.etoh = SecadorEtoh.etoh - secando;
                    } catch (RemoteException ex) {
                        System.out.println("Erro ao enviar Etoh entre tanques");
                        //Logger.getLogger(SecadorEtoh.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
    
    @Override
    public void recebeSecagem(double etoh) {
       SecadorEtoh.etoh = SecadorEtoh.etoh + etoh;
       System.out.println("SecadorEtoh: recebeu =" + etoh);
    }
    
}
