/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Hugo Alvarez
 */
public class Persona {
    private String carnet;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String rol;
    private Set<String> departamentos;

    public Persona(String carnet, String nombre, String apellido, String email, String telefono, String rol) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.rol=rol;
    }

    public Persona() {
    }
    /**
     * Obj: Sirve para saber a que departamento es la persona.
     * pre: un departamento
     * pos: Si, la persona es de ese departamento, No d.l.c.
     * @param departamento, el departamento a revisar
     * @return True si es de ese departamento, False d.l.c
     */
    public boolean perteneceA(String departamento){
        boolean estaElDepartamento = false;
        for (Iterator<String> iterator = getDepartamentos().iterator();!estaElDepartamento && iterator.hasNext();) {
            String next = iterator.next();
            estaElDepartamento = next.equals(departamento);
        }
        return estaElDepartamento;
    }

    @Override
    public boolean equals(Object obj) {
        Persona p=(Persona)obj; 
        return carnet.equals(p.getCarnet());
    }

    @Override
    public String toString() {
        return "Persona["+carnet+ " \n"+nombre+" \n"+apellido+" \n"+email+" \n"+telefono+" \n"+rol+"] \n";
    }

    /**
     * @return the carnet
     */
    public String getCarnet() {
        return carnet;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @return the roles
     */
    public String getRol() {
        return rol;
    }

    /**
     * @return the departamentos
     */
    public Set<String> getDepartamentos() {
        return departamentos;
    }
    /**
     * 
     * @param carnet 
     */
    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }
    /**
     * 
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * 
     * @param apellido 
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    /**
     * 
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 
     * @param telefono 
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    /**
     * 
     * @param rol
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
    /**
     * 
     * @param departamentos 
     */
    public void setDepartamentos(Set<String> departamentos) {
        this.departamentos = departamentos;
    }
    
}