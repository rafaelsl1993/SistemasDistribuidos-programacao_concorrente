
package Programa��oConcorrente.Processos;

import Programa��oConcorrente.Interfaces.InterfaceDecantador;
import Programa��oConcorrente.Interfaces.InterfaceTanqueGlicerina;
import Programa��oConcorrente.Interfaces.InterfaceTanqueOleo;
import Programa��oConcorrente.Interfaces.InterfaceTanqueQuimico;
import Programa��oConcorrente.Utils.Constantes;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Reator {
    private static Constantes constantes = null;
    private InterfaceTanqueOleo remotoTanqueOleo = null;
    private InterfaceTanqueGlicerina remotoTanqueGlicerina = null;
    private InterfaceTanqueQuimico remotoTanqueQuimico = null;
    private InterfaceDecantador remotoDecantador = null;

    Reator(){
        constantes = Constantes.getInstance();
    }

    public void running() throws RemoteException, NotBoundException, MalformedURLException{
        String resposta = new String();

        String url = constantes.RMIIP + constantes.DPONTOS + constantes.PORTATANQUEOLEO
                        + constantes.BARRA + constantes.TANQUEOLEO;
        
        remotoTanqueOleo = (InterfaceTanqueOleo) Naming.lookup(url);
        
        url = constantes.RMIIP + constantes.DPONTOS + constantes.PORTATANQUEGLICERINA
                        + constantes.BARRA + constantes.TANQUEGLICERINA;
        
        remotoTanqueGlicerina = (InterfaceTanqueGlicerina) Naming.lookup(url);
        
        url = constantes.RMIIP + constantes.DPONTOS + constantes.PORTATANQUEQUIMICO
                        + constantes.BARRA + constantes.TANQUEQUIMICO;
        
        remotoTanqueQuimico = (InterfaceTanqueQuimico) Naming.lookup(url);
        
        url = constantes.RMIIP + constantes.DPONTOS + constantes.PORTADECANTADOR
                        + constantes.BARRA + constantes.DECANTADOR;
        
        remotoDecantador = (InterfaceDecantador) Naming.lookup(url);
        
    }
}
