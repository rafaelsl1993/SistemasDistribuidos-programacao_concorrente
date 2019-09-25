
package ProgramaçãoConcorrente.Processos;

import ProgramaçãoConcorrente.Interfaces.InterfaceBiodiesel;
import ProgramaçãoConcorrente.Interfaces.InterfaceSecador;
import ProgramaçãoConcorrente.Utils.Constantes;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Secador extends Server implements InterfaceSecador{
    private int produto;
    private Constantes constantes = null;
    private InterfaceBiodiesel remotoBiodiesel = null;
    
    public Secador() throws RemoteException{
        super(Constantes.getInstance().SECADOR,
                Integer.parseInt(Constantes.getInstance().PORTASECADOR));
        constantes = Constantes.getInstance();
        this.produto = 0;
    }
    
    public void running() throws NotBoundException, MalformedURLException, RemoteException{
        String url = constantes.RMIIP + constantes.DPONTOS + constantes.PORTABIODIESEL
                        + constantes.BARRA + constantes.BIODIESEL;
        
        remotoBiodiesel = (InterfaceBiodiesel) Naming.lookup(url);

    }
    
    
    @Override
    public void recebeEtohSecagem(int produto) {
        this.produto = produto;
    }
    
}
