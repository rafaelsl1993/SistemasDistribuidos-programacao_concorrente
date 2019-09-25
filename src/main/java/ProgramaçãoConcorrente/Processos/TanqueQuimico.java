
package ProgramaçãoConcorrente.Processos;

import ProgramaçãoConcorrente.Interfaces.InterfaceTanqueQuimico;
import ProgramaçãoConcorrente.Utils.Constantes;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.Instant;

public class TanqueQuimico  extends UnicastRemoteObject implements InterfaceTanqueQuimico{
    private float naoh;
    private float etoh;
    
    TanqueQuimico() throws RemoteException{
        /*super(Constantes.getInstance().TANQUEQUIMICO, 
                Integer.parseInt(Constantes.getInstance().PORTATANQUEQUIMICO));
        naoh = 0;
        etoh = 0;*/
    }
    
    public void rodarServer(){
        naoh = 0;
        etoh = 0;
        
        Constantes constantes = Constantes.getInstance();
        
        try{
            TanqueQuimico tanque = new TanqueQuimico();
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(constantes.PORTATANQUEQUIMICO));
            registry.bind(constantes.TANQUEQUIMICO, tanque);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Rodando: TanqueQuimico");
    }
    
    @Override
    public float enviaNaoh(float naoh) {
        if(this.naoh > naoh){
            this.naoh -= naoh;
            return naoh;
        }
        naoh = this.naoh;
        this.naoh = 0;
        return naoh;
    }
    
    @Override
    public float enviaEtoh(float etoh){
        if(this.etoh > etoh){
            this.etoh -= etoh;
            return etoh;
        }
        etoh = this.etoh;
        this.etoh = 0;
        return etoh;
    }

    @Override
    public void recebeEtoh(float etoh) {
        this.etoh += etoh;
        System.out.println("TanqueQuimico recebe: " + etoh);
    }
    
    public Runnable rodarThread = new Runnable(){
        public void run(){
            Instant inicio;
            while(true){
                naoh += 0.45;
                etoh += 1;
                inicio = Instant.now();
                while(Duration.between(inicio,Instant.now()).toMillis() < 1000){
                    //Esperando 5 segundos até a próxima entrega de óleo;
                }
                System.out.println("TanqueQuimico: NaOH= " + naoh + ", EtOH= " + etoh);
            }
        }
    };
    
}
