/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import historiasmedicas.HistoriasMedicas;
import static historiasmedicas.HistoriasMedicas.miClinica;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import modelos.ModMedico;
import vistas.ViewEliminarPersonas;

/**
 *
 * @author lairinyssilva
 */
public class CtrlEliminarMedico {
    private final ViewEliminarPersonas vista=new ViewEliminarPersonas();
    private final LinkedList<ModMedico> medicos=miClinica.getMedicos();

    public CtrlEliminarMedico() {
        
        if(medicos.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No Hay médicos Registrados");
        }
        else{
            vista.setTitle(HistoriasMedicas.TITULO);
            vista.setSize(new Dimension(394,254));
            vista.setLocationRelativeTo(null);
            vista.lblTitulo.setText("Eliminar Médicos");
            vista.lblMensaje.setText("Seleccione el médico a eliminar:");

            actualizarListado();

            vista.cmbPersona.setVisible(true);           
            vista.btnEliminar.addActionListener((ActionEvent e) -> {
            if(vista.cmbPersona.getItemCount()>0)
            {
                    Object [] opciones ={"Si","No"};

                    int eleccion;
                    eleccion = JOptionPane.showOptionDialog(null,
                            "En realidad desea eliminar este médico",
                            "Mensaje de Confirmacion",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,null,opciones,"Si");
                    String[] datosMedico=vista.cmbPersona.getSelectedItem().toString().split("-");
                    String idMedico=datosMedico[0];
                            if (eleccion == JOptionPane.YES_OPTION)
                            {
                                eliminar(idMedico);
                            }
                }
            });
            vista.setVisible(true);
        }
    }
    

    private void eliminar(String idMedico) {
             
        medicos.remove( miClinica.buscarMedico(idMedico));
        JOptionPane.showMessageDialog(null, "Médico Eliminado");
        actualizarListado();
    }

    private void actualizarListado() {
        vista.cmbPersona.removeAllItems();
        for(int i=0; i<medicos.size();i++){

            vista.cmbPersona.addItem(medicos.get(i).getIdYNombre());
        }
    }
    
}
