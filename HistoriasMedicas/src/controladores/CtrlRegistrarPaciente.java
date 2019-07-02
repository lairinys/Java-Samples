package controladores;

import historiasmedicas.HistoriasMedicas;
import static historiasmedicas.HistoriasMedicas.miClinica;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.JOptionPane;
import modelos.ModPaciente;
import vistas.ViewRegistrarPersona;

/**
 *
 * @author lairinyssilva
 */
public final class CtrlRegistrarPaciente {
    private final ViewRegistrarPersona vista;
    private ModPaciente modelo;

    public CtrlRegistrarPaciente() {
        vista = new ViewRegistrarPersona();
        vista.setTitle(HistoriasMedicas.TITULO);
        vista.setSize(new Dimension(605,464));
        vista.setLocationRelativeTo(null);
        vista.txtEspecialidad.setVisible(false);
        vista.lblEspecialidad.setVisible(false);
        vista.lblTitulo.setText("Registrar Paciente");
        vista.panelEncabezado.setVisible(true);
        
        vista.btnGuardar.addActionListener((ActionEvent e) -> {
            guardar();
        }); 
        
        vista.setVisible(true);
        
    }
    

  
    public void guardar(){
        
        String id = vista.txtIdentificacion.getText();
        String nombres = vista.txtNombres.getText();
        String apellidos = vista.txtApellidos.getText();
        
        
         String genero = "";
        
        if (vista.optFemenino.isSelected()) {
            genero="Femenino";
        } else if (vista.optMasculino.isSelected()) {
            genero="Masculino";
        }
        
       if(miClinica.buscarPaciente(id)<0){
           if(validarCampos()){
               int edad = Integer.parseInt(vista.txtEdad.getText());
                modelo=new ModPaciente(id, nombres, apellidos, genero, edad);
                miClinica.addPaciente(modelo); 
                vista.dispose();  
           }
       }
       else{
           JOptionPane.showMessageDialog(null, "Ya existe un paciente con ese código");
       }
       
      
    } 

    private boolean validarCampos() {
        boolean resultado=false;
        if(vista.txtIdentificacion.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese la identificación");
        }else
            if(!isNumeric(vista.txtEdad.getText())){
                JOptionPane.showMessageDialog(null, "Error en la edad");
            }else{
                resultado=true;
            }
        return resultado;
    }
    
    public static boolean isNumeric(String cadena) {
        return cadena.matches("[0-9]*");
    }
 
}
