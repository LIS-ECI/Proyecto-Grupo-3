/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.servicios;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.entities.Persona;
import edu.eci.pdsw.entities.Prestamo;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Julian Devia
 */
public abstract class ServiciosPrestamo {
    
    private static final ServiciosPrestamo INSTANCE=new ServiciosPrestamoPersistence();

    public static ServiciosPrestamo getInstance() throws RuntimeException{        
        return INSTANCE;
    }
    
    /**
     * Registra una devolucion
     * @param equipo del cual se hace la devolucion
     * @throws ExcepcionServicios si no se encontro el equipo a devolver
     */
    public abstract void registrarDevolucion(String equipo)throws ExcepcionServicios;
    
    /**
     * Registra la devolucion de un equipo sencillo
     * @param persona que devuelve el equipo
     * @param equipo que se devuelve
     * @param cantidad devuelta
     * @throws ExcepcionServicios si se devuelven demasiados objetos, la persona no existe o no tiene prestamos de ese equipo pendientes
     */
    public abstract void registarDevolucion(String persona,String equipo,int cantidad)throws ExcepcionServicios;
    /**
     * Registra un prestamo
     * @param pres
     * @throws ExcepcionServicios 
     */
    public abstract void registrarPrestamo(Prestamo pres) throws ExcepcionServicios;
    /**
     * Consulta todos los prestamos con algun equipo en mora
     * @return los prestamos que posean algun equipo en mora de ser entregado
     * @throws ExcepcionServicios
     */
    public abstract List<Prestamo> consultarPrestamosMorosos() throws ExcepcionServicios;
    
    /**
     * Consulta todos los prestamos de una persona determinada
     * @param p la persona de la que se quieren conocer los prestamos
     * @return Los prestamos de p
     * @throws ExcepcionServicios
     */
    public abstract List<Prestamo> consultarPrestamosPersona(String p) throws ExcepcionServicios;
    
    /**
     * Consulta todos los prestamos de un equipo complejo determinado
     * @param ec el equipo complejo del que se quieren conocer los prestamos
     * @return Los prestamos de ec
     * @throws ExcepcionServicios
     */
    public abstract List<Prestamo> consultarPrestamosEquipoComplejo(EquipoComplejo ec) throws ExcepcionServicios;
    
    /**
     * Consulta todos los prestamos
     * @return todos los prestamos registrados
     * @throws ExcepcionServicios si hay un error en DAO
     */
    public abstract List<Prestamo> consultarTodos()throws ExcepcionServicios;
    
    /**
     * Consulta todos los prestamos
     * @param p, prestamo a revisar
     * @return el prestamo registrado
     * @throws ExcepcionServicios si el prestamo no existe
     */
    public abstract Prestamo consultarPrestamosPrestamo(Prestamo p) throws ExcepcionServicios;
    
    /**
     *Consultar una persona por su carne 
     * @param carne de la persona consultada
     * @return La persona
     * @throws ExcepcionServicios si la persona no existe
     */
    public abstract Persona personaCarne(String carne) throws ExcepcionServicios;
    
    /**
     * Registra los equipos sencillos en el prestamo
     * @param p
     * @param es
     * @throws ExcepcionServicios 
     */
    public abstract void registrarEquipoSencilloPrestamo(Prestamo p, EquipoSencillo es) throws ExcepcionServicios;
    
    /**
     * Registra los equipos complejos en el prestamo
     * @param p
     * @param ec
     * @throws ExcepcionServicios 
     */
    public abstract void registrarEquipoComplejoPrestamo(Prestamo p,EquipoComplejo ec) throws ExcepcionServicios;
    
    /**
     * carga la fecha actual
     * @return la fecha actual
     */
    public abstract Timestamp currDate();
    
    /**
     * Actualiza un prestamo
     * @param p
     * @throws ExcepcionServicios 
     */
    public abstract void actualizarPrestamo(Prestamo p) throws ExcepcionServicios;
}
