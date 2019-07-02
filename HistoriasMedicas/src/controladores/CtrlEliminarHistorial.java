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
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.ModHistorialClinico;
import modelos.ModMedico;
import modelos.ModPaciente;
import vistas.ViewEliminarHistorial;

/**
 *
 * @author lairinyssilva
 */
public final class CtrlEliminarHistorial {
    private final ViewEliminarHistorial vista=new ViewEliminarHistorial();
    private final LinkedList<ModMedico> medicos=miClinica.getMedicos();
    private final LinkedList<ModPaciente> pacientes=miClinica.getPacientes();
    DefaultTableModel tablam = new DefaultTableModel();

    public CtrlEliminarHistorial() {
        if(hayHistorias())
        {
            vista.setTitle(HistoriasMedicas.TITULO);

            vista.setSize(new Dimension(605,350));
            vista.setLocationRelativeTo(null);
            vista.lblTitulo.setText("Eliminar Historial Clínico");
            vista.panelEncabezado.setVisible(true);
            vista.panelSeleccion.setVisible(true);
            vista.cmbHistoria.setVisible(false);
            
            vista.setVisible(true);
            vista.optMedico.addActionListener((ActionEvent e) -> {
                mostrarPorMedico();
        }); 
            
            vista.optPaciente.addActionListener((ActionEvent e) -> {
                mostrarPorPaciente();
        }); 
            vista.btnEliminar.addActionListener((ActionEvent e) -> {
                if(confirmarAccion()){
                    eliminarHistoria();
                    vista.dispose();
                }
        });
           
        }
        else{
            JOptionPane.showMessageDialog(null, "No Hay Historias Registradas");
        }
    }

    private boolean hayHistorias() {
        return miClinica.totalHistorias()>0;

    }

    private void llenarComboP(LinkedList<ModHistorialClinico> historialPaciente) {
        
        
        String idMedico;
        int posMedico;
        String item;
        
        vista.cmbHistoria.removeAllItems();
        
        for(int i=0; i<historialPaciente.size();i++){
            
            idMedico = historialPaciente.get(i).getMedico();
            posMedico=miClinica.buscarMedico(idMedico);

            
            item="Historia: "+
                 historialPaciente.get(i).getCodigo()+" "+
                 historialPaciente.get(i).getFecha()+
                 " Médico: " + medicos.get(posMedico).getIdYNombre();
                 
            vista.cmbHistoria.addItem(item);
        }
        
 
    }
    
    private void llenarComboM(ArrayList<ArrayList<String>> historiasPorMedico) {
        
        String item;
        
        vista.cmbHistoria.removeAllItems();
        for(int i=0; i<historiasPorMedico.size();i++){

            item="Historia: "+
                 historiasPorMedico.get(i).get(0)+" "+
                 historiasPorMedico.get(i).get(1)+" Paciente: " +
                 historiasPorMedico.get(i).get(2);
            vista.cmbHistoria.addItem(item);
        }

    }
        
    private void mostrarPorMedico() {
        
        int i;
        
        vista.cmbPersona.removeAllItems();
        removerListener(vista.cmbPersona);
        
        
        for(i=0; i<medicos.size();i++){
            
            vista.cmbPersona.addItem(medicos.get(i).getIdYNombre());
        }   
        
        
        vista.cmbPersona.addActionListener((ActionEvent e) -> {
            vista.cmbHistoria.removeAllItems();
            vista.cmbHistoria.setVisible(true);
            if(vista.cmbPersona.getItemCount()>0)
            {
                String[] datosMedico=vista.cmbPersona.getSelectedItem().toString().split("-");
                String idMedico=datosMedico[0];
                ArrayList<ArrayList<String>> histPorMed=miClinica.historiasPorMedico(idMedico);
                
                if(histPorMed.size()>0)
                {
                    llenarComboM(histPorMed);
                }else{
                    JOptionPane.showMessageDialog(null, "Este médico no tiene historias registradas");
                    
                }
            }
        }); 
    
    }

    private void mostrarPorPaciente() {
        
        int i;
        vista.cmbPersona.removeAllItems();
        removerListener(vista.cmbPersona);

        for(i=0; i<pacientes.size();i++){
            
            vista.cmbPersona.addItem(pacientes.get(i).getIdYNombre());
        }
        vista.cmbPersona.setVisible(true);
        
        
        vista.cmbPersona.addActionListener((ActionEvent e) -> {
            if(vista.cmbPersona.getItemCount()>0)
            {
                String[] datosPaciente=vista.cmbPersona.getSelectedItem().toString().split("-");
                String idPaciente=datosPaciente[0];
                if(miClinica.historialPaciente(idPaciente).size()>0)
                {
                    llenarComboP(miClinica.historialPaciente(idPaciente));
                    vista.cmbHistoria.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Este paciente no tiene historias registradas");
                }
            }
        } );
    }



    private void removerListener(JComboBox<String> cmbPersona) {
        if(cmbPersona.getActionListeners().length > 0) {
            for(ActionListener g : cmbPersona.getActionListeners()) {
                cmbPersona.removeActionListener(g);
            }
        }
    }

  
// Elimina la historia seleccionada
    private void eliminarHistoria() {
        
        String[] ids=sacarIds();
        
        
        int posPaciente=miClinica.buscarPaciente(ids[0]);
        pacientes.get(posPaciente).removeHistoriaPorId(ids[1]);
        JOptionPane.showMessageDialog(null, "Historia Eliminada");

    }


    //devuelve un arreglo con el codigo del paciente y el codigo de la historia
    private String[] sacarIds() {
        String[] datosPaciente,datosHistoria;
        String[] ids=new String[2];
        String idPaciente,idHistoria;
        datosHistoria=vista.cmbHistoria.getSelectedItem().toString().split(" ");
        idHistoria=datosHistoria[1];
        
        if(vista.optPaciente.isSelected()){
            datosPaciente=vista.cmbPersona.getSelectedItem().toString().split("-");
            idPaciente=datosPaciente[0];
            
        }else{   
            idPaciente=datosHistoria[5];        
        }
        
        ids[0]=idPaciente;
        ids[1]=idHistoria;
        return ids;
    }

    // solicita al usuario que confirme si desea eliminar la historia clinica
    private boolean confirmarAccion() {
        Object [] opciones ={"Si","No"};

        int eleccion;
        eleccion = JOptionPane.showOptionDialog(null,
                "En realidad desea eliminar esta historia clínica",
                "Mensaje de Confirmacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,opciones,"Si");
        return eleccion == JOptionPane.YES_OPTION;

    }
    
}
