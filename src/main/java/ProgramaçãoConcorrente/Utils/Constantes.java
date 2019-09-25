
package ProgramaçãoConcorrente.Utils;


public class Constantes {
    public static Constantes instancia = null;
    
    public static final String DPONTOS = ":";
    public static final String BARRA = "/";
    public static final String RMIIP = "rmi://127.0.0.1";
    
    public static final String PORTATANQUEOLEO = "1000";
    public static final String TANQUEOLEO = "TanqueOleo";
    
    public static final String PORTATANQUEGLICERINA = "1002";
    public static final String TANQUEGLICERINA = "TanqueGlicerina";
    
    public static final String PORTATANQUEQUIMICO = "1004";
    public static final String TANQUEQUIMICO = "TanqueQuimico";
    
    public static final String PORTADECANTADOR = "1006";
    public static final String DECANTADOR = "Decantador";
    
    public static final String PORTALAVAGEM = "1008";
    public static final String LAVAGEM = "Lavagem";
    
    public static final String PORTASECADOR = "1010";
    public static final String SECADORETOH = "SecadorEtoh";
    public static final String SECADOR = "Secador";
    
    public static final String PORTATANQUEETOH = "1012";
    public static final String TANQUEETOH = "TanqueEtoh";
    
    public static final String PORTABIODIESEL = "1014";
    public static final String BIODIESEL = "Biodiesel";
    
    
    
    public final int LIMITEDECANTADOR = 500;
    
    private Constantes(){

    }
    
    public static Constantes getInstance(){
        if(instancia == null){
            instancia = new Constantes();
        }
        return instancia;
    }
    
}
