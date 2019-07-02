/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author lairinyssilva
 */
public class ModMedico extends ModPersona {
    private final String especialidad;

    public ModMedico(String identificacion, String nombres, String apellidos, String genero, int edad, String especialidad) {
        super(identificacion, nombres, apellidos, genero, edad);
        this.especialidad=especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }



    


}
