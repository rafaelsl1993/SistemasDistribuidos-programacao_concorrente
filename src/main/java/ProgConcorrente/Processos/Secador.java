
package ProgConcorrente.Processos;

import ProgConcorrente.Interfaces.InterfaceBiodiesel;
import ProgConcorrente.Interfaces.InterfaceSecador;
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

public class Secador extends UnicastRemoteObject implements InterfaceSecador{
    private static double produto;
    private Constantes constantes = null;
    private InterfaceBiodiesel remotoBiodiesel = null;
    
    public Secador() throws RemoteException{

    }
    
    public void rodarServer(){
        Secador.produto = 0;
        constantes = Constantes.getInstance();

        try{
            Secador secador = new Secador();
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(constantes.PORTASECADOR));
            registry.bind(constantes.SECADOR, secador);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Secador: Servidor rodando...");
    }

    public void rodarClient()throws NotBoundException, MalformedURLException, RemoteException{
        String url = constantes.RMIIP +  constantes.DPONTOS + constantes.PORTABIODIESEL 
                        + constantes.BARRA + constantes.BIODIESEL;
        
        remotoBiodiesel = (InterfaceBiodiesel)Naming.lookup(url);
        
        System.out.println("Secador: Conectado ao TanqueBioDiesel...");
    }
    
    public Runnable rodarThread = new Runnable(){
        public void run(){
            //this.etoh = etoh;
            double secando = 0;
            while(true){
                waiting(500);
                if(Secador.produto > 0){
                    secando = Secador.produto;
                    waiting(3000);
                    try {
                        remotoBiodiesel.recebeDiesel( (Secador.produto * 0.97));
                        Secador.produto = Secador.produto - secando;
                        System.out.println("Secador: secou: " + ( Secador.produto * 0.97));
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
    public void recebeSecagem(double produto) throws RemoteException {
        Secador.produto += produto;
        System.out.println("Secador: recebe produto = " + produto);
    }
    
}
