/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author lairinyssilva
 */
public class ModPaciente extends ModPersona{
    
    LinkedList<ModHistorialClinico> historial=new LinkedList<>();

    public ModPaciente(String identificacion, String nombres, String apellidos, String genero, int edad) {
        super(identificacion, nombres, apellidos, genero, edad);
    }

    public void addHistoria(ModHistorialClinico historia){
        
        this.historial.add(historia);
        JOptionPane.showMessageDialog(null, "Historia agregada");
    }

    public LinkedList<ModHistorialClinico> getHistorial() {
        return historial;
    }
    
    public int totalHistorias(){
        return historial.size();
    }
    // retorna una historia clinica deacuerdo a su codigo
    public ModHistorialClinico getHistoriaPorId(String idHistoria){
        ModHistorialClinico historia=null;
        for(int i=0;i<historial.size();i++){
            if(historial.get(i).getCodigo().equals(idHistoria)){
                historia=historial.get(i);
            }
        }
        
        return historia;
    }
// elimina una historia deacuerdo al código
    public void removeHistoriaPorId(String idHistoria){
       
        for(int i=0;i<historial.size();i++){
            if(historial.get(i).getCodigo().equals(idHistoria)){
               historial.remove(i);
            }
        }
        
    }    
// actualiza una fila del historial clinico del paciente
    public void setHistoria(ModHistorialClinico historia) {
        for(int i=0; i<this.historial.size();i++){
            if(this.historial.get(i).getCodigo().equals(historia.getCodigo()))
            {
                this.historial.get(i).setFecha(historia.getFecha());
                this.historial.get(i).setMedico(historia.getMedico());
                this.historial.get(i).setObservaciones(historia.getObservaciones());
                JOptionPane.showMessageDialog(null, "Historia actualizada");
            }
        }
    }
            
}
