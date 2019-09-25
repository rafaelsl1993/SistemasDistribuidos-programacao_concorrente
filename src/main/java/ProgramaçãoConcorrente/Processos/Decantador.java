
package ProgramaçãoConcorrente.Processos;

import ProgramaçãoConcorrente.Interfaces.InterfaceDecantador;
import ProgramaçãoConcorrente.Interfaces.InterfaceLavagem;
import ProgramaçãoConcorrente.Interfaces.InterfaceSecador;
import ProgramaçãoConcorrente.Utils.Constantes;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Decantador  extends Server implements InterfaceDecantador{
    private int produto;
    private Constantes constantes = null;
    private InterfaceSecador remotoSecador = null;
    private InterfaceLavagem remotoLavagem = null;
    
    public Decantador() throws RemoteException{
        super(Constantes.getInstance().DECANTADOR, 
                Integer.parseInt(Constantes.getInstance().PORTADECANTADOR));
        constantes = Constantes.getInstance();
        this.produto = 0;
    }
    
    
    @Override
    public boolean recebeProduto(int produto) {
        if(this.produto + produto > constantes.LIMITEDECANTADOR){
            return false;
        }
        this.produto += produto;
        return true;
    }
    
    public void running() throws NotBoundException, MalformedURLException, RemoteException{
        String url = constantes.RMIIP + constantes.DPONTOS + constantes.PORTALAVAGEM
                        + constantes.BARRA + constantes.LAVAGEM;
        
        remotoLavagem = (InterfaceLavagem) Naming.lookup(url);
        
        url = constantes.RMIIP + constantes.DPONTOS + constantes.PORTASECADOR
                        + constantes.BARRA + constantes.SECADORETOH;
        
        remotoSecador = (InterfaceSecador) Naming.lookup(url);
    }
    
}
