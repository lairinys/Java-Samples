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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import modelos.ModHistorialClinico;
import modelos.ModMedico;
import modelos.ModPaciente;
import vistas.ViewModificarHistoria;

/**
 *
 * @author lairinyssilva
 */
public final class CtrlModificarHistorial {
    private final ViewModificarHistoria vista=new ViewModificarHistoria();
    private final LinkedList<ModMedico> medicos=miClinica.getMedicos();
    private final LinkedList<ModPaciente> pacientes=miClinica.getPacientes();
    private ModHistorialClinico modelo;
    String[] ids;

    public CtrlModificarHistorial() {
        if(hayHistorias())
        {
            vista.setTitle(HistoriasMedicas.TITULO);

            vista.setSize(new Dimension(625,240));
            vista.setLocationRelativeTo(null);
            vista.lblTitulo.setText("Editar Historial Clínico");
            vista.panelEncabezado.setVisible(true);
            vista.panelSeleccion.setVisible(true);
            vista.cmbHistoria.setVisible(false);
            vista.txtCodigo.setEnabled(false);
            
            vista.lblMensaje.setText("Seleccione la historia a eliminar");
            vista.setVisible(true);
            vista.optMedico.addActionListener((ActionEvent arg0) -> {
                mostrarPorMedico();
            });
 
            vista.optPaciente.addActionListener((ActionEvent arg1) -> {
                mostrarPorPaciente();
            }); 
             vista.btnGuardar.addActionListener((ActionEvent arg2) -> {
                guardar();
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
        removerListener(vista.cmbHistoria);
        for(int i=0; i<historialPaciente.size();i++){
            
            idMedico = historialPaciente.get(i).getMedico();
            posMedico=miClinica.buscarMedico(idMedico);

            
            item="Historia: "+
                 historialPaciente.get(i).getCodigo()+" "+
                 historialPaciente.get(i).getFecha()+
                 " Médico: " + medicos.get(posMedico).getIdYNombre();
                 
            vista.cmbHistoria.addItem(item);
        }
        vista.setSize(new Dimension(625,320));
        vista.cmbHistoria.addActionListener((ActionEvent arg2) -> {
            try {
                mostrarDatos();
            } catch (ParseException ex) {
                Logger.getLogger(CtrlModificarHistorial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 

    }
    
    private void llenarComboM(ArrayList<ArrayList<String>> historiasPorMedico) {
        
        String item;
        
        vista.cmbHistoria.removeAllItems();
        removerListener(vista.cmbHistoria);
        for(int i=0; i<historiasPorMedico.size();i++){

            item="Historia: "+
                 historiasPorMedico.get(i).get(0)+" "+
                 historiasPorMedico.get(i).get(1)+" Paciente: " +
                 historiasPorMedico.get(i).get(2);
            vista.cmbHistoria.addItem(item);
        }
        vista.setSize(new Dimension(625,320));
        vista.cmbHistoria.addActionListener((ActionEvent arg2) -> {
            try {
                mostrarDatos();
            } catch (ParseException ex) {
                Logger.getLogger(CtrlModificarHistorial.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
 
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


    
    private boolean confirmarAccion() {
    Object [] opciones ={"Si","No"};

    int eleccion;
    eleccion = JOptionPane.showOptionDialog(null,
            "En realidad desea guardar los cambios realizado",
            "Mensaje de Confirmacion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,null,opciones,"Si");
    return eleccion == JOptionPane.YES_OPTION;

    }
    
    //devuelve un arreglo con los codigos de paciente, historia y medico
    private String[] sacarIds() {

        String[] datosPaciente,datosHistoria;
        String[] ids=new String[3];
        String idPaciente,idHistoria,idMedico;
        datosHistoria=vista.cmbHistoria.getSelectedItem().toString().split(" ");
        String[] datosPersona = vista.cmbPersona.getSelectedItem().toString().split("-");
        String[] persona;
        idHistoria=datosHistoria[1];
        
        if(vista.optPaciente.isSelected()){
            
            idPaciente=datosPersona[0];
            persona=datosHistoria[4].split("-");
            idMedico=persona[0];
            
        }else{   
            persona=datosHistoria[4].split("-");
            idPaciente=persona[0]; 
            idMedico=datosPersona[0];
        }
        
        ids[0]=idPaciente;
        ids[1]=idHistoria;
        ids[2]=idMedico;
        return ids;
    }

    private void mostrarDatos() throws ParseException {
        if (vista.cmbHistoria.getItemCount()>0)
        {    
            ids = sacarIds();
            int posPaciente=miClinica.buscarPaciente(ids[0]);
            ModHistorialClinico historia= pacientes.get(posPaciente).getHistoriaPorId(ids[1]);

            vista.txtCodigo.setText(historia.getCodigo());


            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            Date date = sdf.parse(historia.getFecha());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            vista.dChfecha.setSelectedDate(cal);

            llenarCmbPaciente(ids[0]);
            llenarCmbMedico(ids[2]);
            vista.txtObservaciones.setText(historia.getObservaciones());
            vista.setSize(new Dimension(625,750));
        }
        else{
            vista.setSize(new Dimension(625,240));
        }
    }

   // Llena el combo para seleccionar el paciente
    private void llenarCmbPaciente(String id) {
        int j=0;
        vista.cmbPaciente.removeAllItems();
        for(int i=0; i<pacientes.size();i++){
            
            vista.cmbPaciente.addItem(pacientes.get(i).getIdYNombre());
            if(pacientes.get(i).getIdentificacion().equals(id)){
                vista.cmbPaciente.setSelectedIndex(j);
            }
            j++;
        }
    }

    private void llenarCmbMedico(String id) {
        int j=0;
        vista.cmbMedico.removeAllItems();
        for(int i=0; i<medicos.size();i++){
            
            vista.cmbMedico.addItem(medicos.get(i).getIdYNombre());
            if(medicos.get(i).getIdentificacion().equals(id)){
                vista.cmbMedico.setSelectedIndex(j);
            }
            j++;
        }
    }

    private void guardar() {
        if(confirmarAccion()){
            ids=sacarIds();
            if(ids.length>0){
                    String id = vista.txtCodigo.getText();
                    String fecha = vista.dChfecha.getText();
                    String observaciones = vista.txtObservaciones.getText();
                    int posPaciente=miClinica.buscarPaciente(ids[0]);

                    String medico=ids[2];


                    modelo=new ModHistorialClinico(id, fecha, medico, observaciones);

                    pacientes.get(posPaciente).setHistoria(modelo);
                    vista.dispose();
            }
        }
    }

 
    
}
