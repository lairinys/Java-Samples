/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import historiasmedicas.HistoriasMedicas;
import static historiasmedicas.HistoriasMedicas.miClinica;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.ModMedico;
import vistas.ViewMostrar;

/**
 *
 * @author lairinyssilva
 */
public final class CtrlMostrarMedicos {
    private final ViewMostrar vista=new ViewMostrar();
    private LinkedList<ModMedico> medicos;
    DefaultTableModel tablam = new DefaultTableModel(){

    @Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
};

    public CtrlMostrarMedicos() {
        if(hayPacientes())
        {
            vista.setTitle(HistoriasMedicas.TITULO);

            vista.setSize(new Dimension(605,464));
            vista.setLocationRelativeTo(null);
            vista.lblTitulo.setText("Listado de Médicos");
            vista.panelEncabezado.setVisible(true);
            vista.panelSeleccion.setVisible(false);
            vista.setVisible(true);
            mostrarDatos();
        }
        else{
            JOptionPane.showMessageDialog(null, "No Hay Médicos Registrados");
        }
    }

    private boolean hayPacientes() {
        medicos=miClinica.getMedicos();
        return !medicos.isEmpty();

    }

    private void mostrarDatos() {
        ArrayList row = new ArrayList();
        
        tablam.addColumn("Identificacion");
        tablam.addColumn("Nombres");
        tablam.addColumn("Apellidos");
        tablam.addColumn("Edad");
        tablam.addColumn("Género");
        tablam.addColumn("Especialidad");
        
        for(int i=0; i<medicos.size();i++){
            
            
            tablam.addRow(new Object[]{
                medicos.get(i).getIdentificacion(),
                medicos.get(i).getNombres(),
                medicos.get(i).getApellidos(),
                medicos.get(i).getEdad(),
                medicos.get(i).getGenero(),
                medicos.get(i).getEspecialidad()
            });
        }
        
        vista.tblPersonas.setModel(tablam);
        
    }
    
}
