/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import historiasmedicas.HistoriasMedicas;
import static historiasmedicas.HistoriasMedicas.miClinica;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.JOptionPane;
import modelos.ModMedico;
import vistas.ViewRegistrarPersona;

/**
 *
 * @author lairinyssilva
 */
public final class CtrlRegistrarMedico{
    private final ViewRegistrarPersona vista;
    private ModMedico modelo;

    public CtrlRegistrarMedico() {
        vista = new ViewRegistrarPersona();
        vista.setTitle(HistoriasMedicas.TITULO);
        //vista.setPreferredSize(new Dimension(605,464));
        vista.setSize(new Dimension(605,464));
        vista.setLocationRelativeTo(null);
        vista.txtEspecialidad.setVisible(true);
        vista.txtEspecialidad.setEnabled(true);
        vista.txtEspecialidad.setEditable(true);
        vista.lblEspecialidad.setVisible(true);
        vista.lblTitulo.setText("Registrar Médico");
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
        String especialidad = vista.txtEspecialidad.getText();
        
        
         String genero = "";
        
        if (vista.optFemenino.isSelected()) {
            genero="Femenino";
        } else if (vista.optMasculino.isSelected()) {
            genero="Masculino";
        }
        
        if(miClinica.buscarMedico(id)<0){
            if(validarCampos()){
                int edad = Integer.parseInt(vista.txtEdad.getText());
                modelo=new ModMedico(id, nombres, apellidos, genero, edad,especialidad);
                miClinica.addMedico(modelo); 
                vista.dispose();   
            }
        }else{
            JOptionPane.showMessageDialog(null, "Ya existe un médico con ese código");
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
