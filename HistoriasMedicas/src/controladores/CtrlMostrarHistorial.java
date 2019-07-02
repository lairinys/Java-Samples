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
import vistas.ViewMostrar;

/**
 *
 * @author lairinyssilva
 */
public final class CtrlMostrarHistorial {
    private final ViewMostrar vista=new ViewMostrar();
    private final LinkedList<ModMedico> medicos=miClinica.getMedicos();
    private final LinkedList<ModPaciente> pacientes=miClinica.getPacientes();
    DefaultTableModel tablam = new DefaultTableModel(){

    @Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
};


    public CtrlMostrarHistorial() {
        if(hayHistorias())
        {
            vista.setTitle(HistoriasMedicas.TITULO);

            vista.setSize(new Dimension(605,464));
            vista.setLocationRelativeTo(null);
            vista.lblTitulo.setText("Historial Clínico");
            vista.panelEncabezado.setVisible(true);
            vista.panelSeleccion.setVisible(true);
            vista.cmbPersona.setVisible(false);
            vista.tblPersonas.setVisible(false);
            
            vista.setVisible(true);
            vista.optMedico.addActionListener((ActionEvent e) -> {
            mostrarPorMedico();
        }); 
            
            vista.optPaciente.addActionListener((ActionEvent e) -> {
            mostrarPorPaciente();
        }); 
           
        }
        else{
            JOptionPane.showMessageDialog(null, "No Hay Historias Registradas");
        }
    }

    private boolean hayHistorias() {
        return miClinica.totalHistorias()>0;

    }

    private void llenarTablaHistorialP(LinkedList<ModHistorialClinico> historialPaciente, String idPaciente) {
        
        ArrayList row = new ArrayList();
        String idMedico;
        int posMedico;
        
        tablam.setColumnCount(0);
        tablam.setRowCount(0);
        tablam.addColumn("Codigo");
        tablam.addColumn("Fecha");
        tablam.addColumn("especialidad");
        tablam.addColumn("Medico");
        tablam.addColumn("Observaciones");
                
        
        for(int i=0; i<historialPaciente.size();i++){
            
            idMedico = historialPaciente.get(i).getMedico();
            posMedico=miClinica.buscarMedico(idMedico);

            
            tablam.addRow(new Object[]{
                historialPaciente.get(i).getCodigo(),
                historialPaciente.get(i).getFecha(),
                medicos.get(posMedico).getEspecialidad(),
                medicos.get(posMedico).getIdYNombre(),
                historialPaciente.get(i).getObservaciones()
            }); 
        }
        
        vista.tblPersonas.setModel(tablam);

     
        vista.tblPersonas.setVisible(true);
    }
    
    private void llenarTablaHistorialM(ArrayList<ArrayList<String>> historiasPorMedico) {
        tablam.setColumnCount(0);
        tablam.setRowCount(0);
        tablam.addColumn("Codigo");
        tablam.addColumn("Fecha");
        tablam.addColumn("Paciente");
        tablam.addColumn("Observaciones");
        
        for(int i=0; i<historiasPorMedico.size();i++){
            
            tablam.addRow(new Object[]{
                historiasPorMedico.get(i).get(0),
                historiasPorMedico.get(i).get(1),
                historiasPorMedico.get(i).get(2),
                historiasPorMedico.get(i).get(3)
            }); 

        }
        vista.tblPersonas.setModel(tablam);
        vista.tblPersonas.setVisible(true);
    }
        
    private void mostrarPorMedico() {
        
        int i;
        
        vista.cmbPersona.removeAllItems();
        removerListener(vista.cmbPersona);
        tablam.setColumnCount(0);
        tablam.setRowCount(0);
        
        for(i=0; i<medicos.size();i++){
            
            vista.cmbPersona.addItem(medicos.get(i).getIdYNombre());
        }   
        vista.cmbPersona.setVisible(true);
        
        vista.cmbPersona.addActionListener((ActionEvent e) -> {
            if(vista.cmbPersona.getItemCount()>0)
            {
                String[] datosMedico=vista.cmbPersona.getSelectedItem().toString().split("-");
                String idMedico=datosMedico[0];
                ArrayList<ArrayList<String>> histPorMed=miClinica.historiasPorMedico(idMedico);
                
                if(histPorMed.size()>0)
                {
                    llenarTablaHistorialM(histPorMed);
                }else{
                    JOptionPane.showMessageDialog(null, "Este médico no tiene historias registradas");
                    vista.tblPersonas.setVisible(false);
                }
            }
        }); 
    
    }

    private void mostrarPorPaciente() {
        
        int i;
        vista.cmbPersona.removeAllItems();
        removerListener(vista.cmbPersona);
        tablam.setColumnCount(0);
        tablam.setRowCount(0);
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
                    llenarTablaHistorialP(miClinica.historialPaciente(idPaciente),idPaciente);
                }else{
                    JOptionPane.showMessageDialog(null, "Este paciente no tiene historias registradas");
                    vista.tblPersonas.setVisible(false);
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

 
    
}
