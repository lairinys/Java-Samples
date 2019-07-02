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
import modelos.ModPaciente;
import vistas.ViewEliminarPersonas;

/**
 *
 * @author lairinyssilva
 */
public class CtrlEliminarPaciente {
    private final ViewEliminarPersonas vista=new ViewEliminarPersonas();
    private final LinkedList<ModPaciente> pacientes=miClinica.getPacientes();

    public CtrlEliminarPaciente() {
        
        if(pacientes.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No Hay pacientes Registrados");
        }
        else{
            vista.setTitle(HistoriasMedicas.TITULO);
            vista.setSize(new Dimension(394,254));
            vista.setLocationRelativeTo(null);
            vista.lblTitulo.setText("Eliminar Pacientes");
            vista.lblMensaje.setText("Seleccione el paciente a eliminar:");

            actualizarListado();
            
            vista.cmbPersona.setVisible(true);           
            vista.btnEliminar.addActionListener((ActionEvent e) -> {
                if(vista.cmbPersona.getItemCount()>0)
                {
                    Object [] opciones ={"Si","No"};

                    int eleccion;
                    eleccion = JOptionPane.showOptionDialog(null,
                            "En realidad desea eliminar este paciente",
                            "Mensaje de Confirmacion",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,null,opciones,"Si");
                    String[] datosPaciente=vista.cmbPersona.getSelectedItem().toString().split("-");
                    String idPaciente=datosPaciente[0];
                    if (eleccion == JOptionPane.YES_OPTION)
                    {
                        eliminar(idPaciente);
                    }
                }
            });
            vista.setVisible(true);
        }
    }
    

    private void eliminar(String idPaciente) {
             
        pacientes.remove( miClinica.buscarPaciente(idPaciente));
        JOptionPane.showMessageDialog(null, "Paciente Eliminado");
        actualizarListado();
    }

    private void actualizarListado() {
        vista.cmbPersona.removeAllItems();
        for(int i=0; i<pacientes.size();i++){

            vista.cmbPersona.addItem(pacientes.get(i).getIdYNombre());
        }
    }
    
}
