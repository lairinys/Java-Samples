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
import modelos.ModHistorialClinico;
import modelos.ModMedico;
import modelos.ModPaciente;
import vistas.ViewRegistrarHistoria;

/**
 *
 * @author lairinyssilva
 */
public class CtrlRegistrarHistorial {
    private final ViewRegistrarHistoria vista;
    private ModHistorialClinico modelo;
    
    private final LinkedList<ModPaciente> pacientes;
    private final LinkedList<ModMedico> medicos;

    public CtrlRegistrarHistorial() {
        
        int i;
        
        vista = new ViewRegistrarHistoria();
        vista.setTitle(HistoriasMedicas.TITULO);
        vista.setSize(new Dimension(630,527));
        vista.setLocationRelativeTo(null);
       

        vista.lblTitulo.setText("Registrar Historial");
        
        // Llenar el combo con los pacientes registrados
        
        pacientes=miClinica.getPacientes();
        for(i=0; i<pacientes.size();i++){
            
            vista.cmbPaciente.addItem(pacientes.get(i).getIdYNombre());
        }
        
        //Llenar el combo con los medicos registrados
        
        medicos=miClinica.getMedicos();
        for(i=0; i<medicos.size();i++){
            
            vista.cmbMedico.addItem(medicos.get(i).getIdYNombre());
        }        
        
        vista.btnGuardar.addActionListener((ActionEvent e) -> {
            guardar();
        }); 
        
        vista.setVisible(true);
    }
    // Guardar los datos
    public void guardar(){
        // valida que el c[odigo de historia no est[a vacio
        if(!vista.txtCodigo.getText().isEmpty()){
            // valida que el codigo de historia no este repetido
            if(miClinica.existeHistorial(vista.txtCodigo.getText())){
                JOptionPane.showMessageDialog(null, "Ya existe una istoria con ese código");
            }else{
                String[] datosPaciente=vista.cmbPaciente.getSelectedItem().toString().split("-");
                String[] datosMedico=vista.cmbMedico.getSelectedItem().toString().split("-");

                String id = vista.txtCodigo.getText();
                String fecha = vista.dChfecha.getText();
                String observaciones = vista.txtObservaciones.getText();
                String paciente=datosPaciente[0];
                String medico=datosMedico[0];
                
                
                modelo=new ModHistorialClinico(id, fecha, medico, observaciones);
                
                miClinica.registrarHistoria(paciente,modelo);
                vista.dispose();
            }
        

        }
        else{
            JOptionPane.showMessageDialog(null, "Ingrese el código de la historia");
        }
    } 
    
    
}
