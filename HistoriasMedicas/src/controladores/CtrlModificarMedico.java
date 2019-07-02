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
import java.util.LinkedList;
import javax.swing.JOptionPane;
import modelos.ModMedico;
import vistas.ViewModificarPersona;

/**
 *
 * @author lairinyssilva
 */
public final class CtrlModificarMedico {
    private final ViewModificarPersona vista;
    private ModMedico modelo;
    private final LinkedList<ModMedico> medicos=miClinica.getMedicos();

    public CtrlModificarMedico() {
        vista = new ViewModificarPersona();
        vista.setTitle(HistoriasMedicas.TITULO);
        vista.setSize(new Dimension(605,564));
        vista.setLocationRelativeTo(null);
        vista.txtIdentificacion.setEnabled(false);
        vista.txtEspecialidad.setVisible(true);
        vista.lblEspecialidad.setVisible(true);
        vista.txtEspecialidad.setEnabled(true);
        vista.lblTitulo.setText("Modificar Médico");
        vista.lblMensaje.setText("Seleccione el médico a modificar");
        vista.panelEncabezado.setVisible(true);
        actualizarListado();
        vista.btnGuardar.addActionListener((ActionEvent e) -> {
            guardar();
            actualizarListado();
        }); 
        vista.cmbPersona.addActionListener((ActionEvent e) -> {
            if(vista.cmbPersona.getItemCount()>0)
            {
                String[] datos=vista.cmbPersona.getSelectedItem().toString().split("-");
                String id=datos[0];
                llenarCampos(id);
            }
        }); 
        vista.setVisible(true);
        
    }
    

  
    public void guardar(){
     
        Object [] opciones ={"Si","No"};

        int eleccion;
        eleccion = JOptionPane.showOptionDialog(null,
                "Está seguro de que desea modificar los datos de esta persona",
                "Mensaje de Confirmacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,opciones,"Si");
        String[] datosMedico=vista.cmbPersona.getSelectedItem().toString().split("-");
        String idMedico=datosMedico[0];
        if (eleccion == JOptionPane.YES_OPTION)
        {    
            String id = vista.txtIdentificacion.getText();
            String nombres = vista.txtNombres.getText();
            String apellidos = vista.txtApellidos.getText();
            String especialidad=vista.txtEspecialidad.getText();
            int edad = Integer.parseInt(vista.txtEdad.getText());

             String genero = "";

            if (vista.optFemenino.isSelected()) {
                genero="Femenino";
            } else if (vista.optMasculino.isSelected()) {
                genero="Masculino";
            }

           int posicion=miClinica.buscarMedico(id);
           if(posicion>=0){
               modelo=new ModMedico(id, nombres, apellidos, genero, edad,especialidad);  
               medicos.set(posicion, modelo);
               JOptionPane.showMessageDialog(null, "Los datos fueron actualizados"); 
           }
           else{
               JOptionPane.showMessageDialog(null, "Este médico no existe");
           }  
        } 
    }
    
    private void actualizarListado() {
        vista.cmbPersona.removeAllItems();
        for(int i=0; i<medicos.size();i++){

            vista.cmbPersona.addItem(medicos.get(i).getIdYNombre());
        }
    }

    private void llenarCampos(String id) {
        int posicion=miClinica.buscarMedico(id);
        if (posicion>=0){
            ModMedico medico=medicos.get(posicion);
            vista.txtIdentificacion.setText(medico.getIdentificacion());
            vista.txtNombres.setText(medico.getNombres());
            vista.txtApellidos.setText(medico.getApellidos());
            vista.txtEdad.setText( Integer.toString(medico.getEdad()));
            vista.txtEspecialidad.setText(medico.getEspecialidad());
            if ("Femenino".equals(medico.getGenero())) {
                vista.optFemenino.setSelected(true);
            } else {
                vista.optMasculino.setSelected(true);
            }
        }      
    }

 
}
