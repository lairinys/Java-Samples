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
import modelos.ModPaciente;
import vistas.ViewModificarPersona;

/**
 *
 * @author lairinyssilva
 */
public final class CtrlModificarPaciente {
    private final ViewModificarPersona vista;
    private ModPaciente modelo;
    private final LinkedList<ModPaciente> pacientes=miClinica.getPacientes();

    public CtrlModificarPaciente() {
        vista = new ViewModificarPersona();
        vista.setTitle(HistoriasMedicas.TITULO);
        vista.setSize(new Dimension(605,564));
        vista.setLocationRelativeTo(null);
        vista.txtIdentificacion.setEnabled(false);
        vista.txtEspecialidad.setVisible(false);
        vista.lblEspecialidad.setVisible(false);
        vista.lblTitulo.setText("Modificar Paciente");
        vista.panelEncabezado.setVisible(true);
        vista.lblMensaje.setText("Seleccione el Paciente modificar");
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
            int edad = Integer.parseInt(vista.txtEdad.getText());

             String genero = "";

            if (vista.optFemenino.isSelected()) {
                genero="Femenino";
            } else if (vista.optMasculino.isSelected()) {
                genero="Masculino";
            }

           int posicion=miClinica.buscarPaciente(id);
           if(posicion>=0){
               modelo=new ModPaciente(id, nombres, apellidos, genero, edad);   
               pacientes.set(posicion, modelo);
               JOptionPane.showMessageDialog(null, "Los datos fueron actualizados");
           }
        }
    } 
    
    private void actualizarListado() {
        vista.cmbPersona.removeAllItems();
        for(int i=0; i<pacientes.size();i++){

            vista.cmbPersona.addItem(pacientes.get(i).getIdYNombre());
        }
    }

    private void llenarCampos(String id) {
        int posicion=miClinica.buscarPaciente(id);
        if (posicion>=0){
            ModPaciente paciente=pacientes.get(posicion);
            vista.txtIdentificacion.setText(paciente.getIdentificacion());
            vista.txtNombres.setText(paciente.getNombres());
            vista.txtApellidos.setText(paciente.getApellidos());
            vista.txtEdad.setText( Integer.toString(paciente.getEdad()));
            if ("Femenino".equals(paciente.getGenero())) {
                vista.optFemenino.setSelected(true);
            } else {
                vista.optMasculino.setSelected(true);
            }
        }      
    }

 
}
