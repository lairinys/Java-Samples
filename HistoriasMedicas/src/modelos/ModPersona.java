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
public class ModPersona {
    private final String identificacion;
    private final String nombres;
    private final String apellidos;
    private final String genero;
    private final int edad;

    public ModPersona(String identificacion, String nombres, String apellidos, String genero, int edad) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.edad = edad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public int getEdad() {
        return edad;
    }
    
    public String getIdYNombre(){
        return identificacion+"-"+nombres+", "+apellidos;
    }
}
