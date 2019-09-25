
package ProgramaçãoConcorrente.Processos;

import ProgramaçãoConcorrente.Interfaces.InterfaceLavagem;
import ProgramaçãoConcorrente.Interfaces.InterfaceSecador;
import ProgramaçãoConcorrente.Utils.Constantes;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Lavagem extends Server implements InterfaceLavagem{
    private int produto;
    private Constantes constantes = null;
    private InterfaceSecador remotoSecador = null;
    
    public Lavagem() throws RemoteException{
        super(Constantes.getInstance().LAVAGEM, 
                Integer.parseInt(Constantes.getInstance().PORTALAVAGEM));
        constantes = Constantes.getInstance();
        this.produto = 0;
    }
    
    public void running() throws NotBoundException, MalformedURLException, RemoteException{
        String url = constantes.RMIIP + constantes.DPONTOS + constantes.PORTASECADOR
                        + constantes.BARRA + constantes.SECADOR;
        
        remotoSecador = (InterfaceSecador) Naming.lookup(url);
    }
    
    
    @Override
    public void recebeLavagem(int produto) {
        this.produto += produto;
    }
    
}
