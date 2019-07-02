/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author lairinyssilva
 */
public class ModClinica {
    
    LinkedList<ModPaciente> pacientes=new LinkedList<>();
    LinkedList<ModMedico> medicos=new LinkedList<>();
    
    public void addPaciente(ModPaciente paciente){
        
        this.pacientes.add(paciente);
        JOptionPane.showMessageDialog(null, "Paciente Agregado");
    }
    
    public void addMedico(ModMedico medico){
        this.medicos.add(medico);
        JOptionPane.showMessageDialog(null, "Medico Agregado");
    }

    public LinkedList<ModPaciente> getPacientes() {
        return pacientes;
    }

    public LinkedList<ModMedico> getMedicos() {
        return medicos;
    }
    
    
    // Registra una Historia clinica
    public void registrarHistoria(String idPaciente,ModHistorialClinico historia){
        int posicion=buscarPaciente(idPaciente);
        
        ModPaciente paciente=pacientes.get(posicion);
        paciente.addHistoria(historia);
        
    }
    
    // Devuelve la ubicacion de un paciente en la lista de pacientes
    public int buscarPaciente(String idPaciente) {
        int posicion=-1;
        
        for(int i=0;i<pacientes.size()&&posicion==-1;i++){
            
            if(pacientes.get(i).getIdentificacion().equals(idPaciente)){
                posicion=i;

            }
        }
        return posicion;
    }
    // devuelve la posicion de un medico en la lista de medicos
    public int buscarMedico(String idMedico) {
        int posicion=-1;
        
        for(int i=0;i<medicos.size()&&posicion==-1;i++){
            
            if(medicos.get(i).getIdentificacion().equals(idMedico)){
                posicion=i;

            }
        }
        return posicion;
    }
    // Devuelve el total de historias registradas
    public int totalHistorias(){
        int i, total=0;
        for(i=0;i<pacientes.size();i++){
            total+=pacientes.get(i).totalHistorias();
        }
        return total;
    }
    
    // Devuelve una lista con el historial de  un paciente
    
    public LinkedList<ModHistorialClinico> historialPaciente(String idPaciente) {
        
        int posicion=buscarPaciente(idPaciente);
        return pacientes.get(posicion).getHistorial();
    
    }
    
    public ArrayList<ArrayList<String>> historiasPorMedico(String idMedico) {
        
        int posicion=buscarMedico(idMedico);
        
        
        LinkedList<ModHistorialClinico> historial;
        ArrayList<ArrayList<String>> historiasMedico = new ArrayList<>();
        
        int k=0;
        
        for(int i=0;i<pacientes.size();i++){
            historial=pacientes.get(i).getHistorial();
            
            
            for(int j=0;j<historial.size();j++)
            {
                
                if(historial.get(j).getMedico().equals(idMedico)){
 
                  historiasMedico.add(new ArrayList());
                  historiasMedico.get(k).add(historial.get(j).getCodigo());
                  historiasMedico.get(k).add(historial.get(j).getFecha());
                  historiasMedico.get(k).add(pacientes.get(i).getIdYNombre());
                  historiasMedico.get(k).add(historial.get(j).getObservaciones());
                  
                  k++;
                }
            }

        }        
        return historiasMedico;
    
    }
    
    public boolean existeHistorial(String codHistoria){
        boolean resultado=false;
        LinkedList<ModHistorialClinico> historia;
        if(!codHistoria.isEmpty()){
            for(int i=0; i<pacientes.size();i++){
                historia=pacientes.get(i).getHistorial();
                for(int j=0; j<historia.size();j++){
                    if(historia.get(j).getCodigo().equals(codHistoria)){
                        resultado=true;
                    }
                }
            }
            
        }
        return resultado;
    }
    


}
