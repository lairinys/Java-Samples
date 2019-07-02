package historiasmedicas;

import vistas.ViewMenuPrincipal;
import modelos.ModClinica;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author lairinyssilva
 * Icons made by Freepik from www.flaticon.com
 * 
 */
public class HistoriasMedicas {

    public static final String TITULO="Sistema de Historias Médicas";
    
    public static ViewMenuPrincipal Principal = new ViewMenuPrincipal(); 
 
    public static ModClinica miClinica=new ModClinica();
 
    public static void main(String[] args) {
      Principal.setLocationRelativeTo(null);
      Principal.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      Principal.setVisible (true); 

    }    
    
}
