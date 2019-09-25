
package ProgramaçãoConcorrente.Processos;

import ProgramaçãoConcorrente.Interfaces.InterfaceBiodiesel;
import ProgramaçãoConcorrente.Utils.Constantes;
import java.rmi.RemoteException;

public class Biodiesel extends Server implements InterfaceBiodiesel{
    private int biodiesel;
    
    public Biodiesel() throws RemoteException{
        super(Constantes.getInstance().BIODIESEL,
                Integer.parseInt(Constantes.getInstance().PORTABIODIESEL));
        biodiesel = 0;
    }
    
    @Override
    public void recebeDiesel(int biodiesel) {
        this.biodiesel += biodiesel;
    }
    
}
