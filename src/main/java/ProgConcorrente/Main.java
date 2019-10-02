
package ProgConcorrente;

import ProgConcorrente.Processos.LavagemTres;
import ProgConcorrente.Processos.Biodiesel;
import ProgConcorrente.Processos.TanqueQuimico;
import ProgConcorrente.Processos.LavagemUm;
import ProgConcorrente.Processos.TanqueGlicerina;
import ProgConcorrente.Processos.TanqueOleo;
import ProgConcorrente.Processos.Decantador;
import ProgConcorrente.Processos.Reator;
import ProgConcorrente.Processos.Secador;
import ProgConcorrente.Processos.TanqueEtoh;
import ProgConcorrente.Processos.Oleo;
import ProgConcorrente.Processos.LavagemDois;
import ProgConcorrente.Processos.SecadorEtoh;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
    public static void main(String[] args) throws RemoteException {
        TanqueOleo tanqueOleo = new TanqueOleo();
        Decantador decantador = new Decantador();
        TanqueGlicerina tanqueGlicerina = new TanqueGlicerina();
        SecadorEtoh secadorEtoh = new SecadorEtoh();
        TanqueEtoh tanqueEtoh = new TanqueEtoh();
        TanqueQuimico tanqueQuimico = new TanqueQuimico();
        LavagemUm lavagemUm = new LavagemUm();
        LavagemDois lavagemDois = new LavagemDois();
        LavagemTres lavagemTres = new LavagemTres();
        Secador secador = new Secador();
        Biodiesel tanqueBiodiesel = new Biodiesel();
        
        Oleo oleo = new Oleo();
        Reator reator = new Reator();
        
        tanqueOleo.rodarServer();
        decantador.rodarServer();
        tanqueGlicerina.rodarServer();
        secadorEtoh.rodarServer();
        tanqueEtoh.rodarServer();
        tanqueQuimico.rodarServer();
        lavagemUm.rodarServer();
        lavagemDois.rodarServer();
        lavagemTres.rodarServer();
        secador.rodarServer();
        tanqueBiodiesel.rodarServer();
        
        
        try {
            oleo.rodarClient();
            reator.rodarClient();
            decantador.rodarClient();
            secadorEtoh.rodarClient();
            tanqueEtoh.rodarClient();
            lavagemUm.rodarClient();
            lavagemDois.rodarClient();
            lavagemTres.rodarClient();
            secador.rodarClient();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        Thread oleoThread = new Thread(oleo.rodarThread);
        Thread tanqueQuimicoThread = new Thread(tanqueQuimico.rodarThread);
        Thread decantadorThread = new Thread(decantador.rodarThread);
        Thread tanqueEtohThread = new Thread(tanqueEtoh.rodarThread);
        Thread lavagemUmThread = new Thread(lavagemUm.rodarThread);
        Thread lavagemDoisThread = new Thread(lavagemDois.rodarThread);
        Thread lavagemTresThread = new Thread(lavagemTres.rodarThread);
        
        Thread secadorThread = new Thread(secador.rodarThread);
        Thread secadorEtohThread = new Thread(secadorEtoh.rodarThread);
        Thread reatorThread = new Thread(reator.rodarThread);

        oleoThread.start();
        tanqueQuimicoThread.start();
        decantadorThread.start();
        tanqueEtohThread.start();
        lavagemUmThread.start();
        lavagemDoisThread.start();
        lavagemTresThread.start();
        secadorThread.start();
        secadorEtohThread.start();   
        reatorThread.start();
        
        while(true){
            //running....
        }
    }
    
}
