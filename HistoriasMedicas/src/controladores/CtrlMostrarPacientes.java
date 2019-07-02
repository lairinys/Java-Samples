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
import modelos.ModPaciente;
import vistas.ViewMostrar;

/**
 *
 * @author lairinyssilva
 */
public final class CtrlMostrarPacientes {
    private final ViewMostrar vista=new ViewMostrar();
    private LinkedList<ModPaciente> pacientes=miClinica.getPacientes();
    DefaultTableModel tablam = new DefaultTableModel(){

    @Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
};

    public CtrlMostrarPacientes() {
        if(pacientes.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No Hay pacientes Registrados");
        }
        else{
            vista.setTitle(HistoriasMedicas.TITULO);

            vista.setSize(new Dimension(605,464));
            vista.setLocationRelativeTo(null);
            vista.lblTitulo.setText("Listado de Pacientes");
            vista.panelEncabezado.setVisible(true);
            vista.panelSeleccion.setVisible(false);
            vista.setVisible(true);
            mostrarDatos();
        }
    }



    private void mostrarDatos() {
        ArrayList row = new ArrayList();
        
        tablam.addColumn("Identificacion");
        tablam.addColumn("Nombres");
        tablam.addColumn("Apellidos");
        tablam.addColumn("Edad");
        tablam.addColumn("Género");
        
        for(int i=0; i<pacientes.size();i++){
            
            
            tablam.addRow(new Object[]{
                pacientes.get(i).getIdentificacion(),
                pacientes.get(i).getNombres(),
                pacientes.get(i).getApellidos(),
                pacientes.get(i).getEdad(),
                pacientes.get(i).getGenero()
            });
        }
        
        vista.tblPersonas.setModel(tablam);
        
    }
   
 
}
