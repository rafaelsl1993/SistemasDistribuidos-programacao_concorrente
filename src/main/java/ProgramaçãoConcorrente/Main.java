
package Programa��oConcorrente;

import Programa��oConcorrente.Processos.Oleo;
import Programa��oConcorrente.Processos.TanqueOleo;
import java.rmi.RemoteException;

public class Main {
    
    public static void main(String[] args) throws RemoteException {
        TanqueOleo tanqueOleo = new TanqueOleo();
        tanqueOleo.rodarServer();
        Oleo oleo = new Oleo();
        oleo.rodarClient();
        
        Thread oleoThread = new Thread(oleo.rodarThread);
        oleoThread.start();
        while(true){
            //waiting....
        }
    }
    
}
