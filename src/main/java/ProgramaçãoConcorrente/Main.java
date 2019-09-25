
package ProgramaçãoConcorrente;

import ProgramaçãoConcorrente.Processos.Oleo;
import ProgramaçãoConcorrente.Processos.TanqueOleo;
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
