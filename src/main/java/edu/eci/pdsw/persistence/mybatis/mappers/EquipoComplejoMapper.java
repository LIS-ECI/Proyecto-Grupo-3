/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.mybatis.mappers;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.Modelo;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author German Lopez
 */
public interface EquipoComplejoMapper {
    /**
     * Registra un equipo complejo en la base de datos
     * @param ec 
     */
    public void insertEquipo(@Param("EqComplejo") EquipoComplejo ec);
    
    /**
     * Registra un modelo en la base de datos
     * @param m 
     */
    public void insertModelo(@Param("Modelo")Modelo m);
    
    /**
     * Consulta todos los equipos complejos que esten disponibles
     * @return un arreglo de equipos complejos que esten disponibles
     */
    public ArrayList<EquipoComplejo> loadDisponibles();
    
    /**
     * Consulta un equipo complejo por un serial especifico
     * @param serial
     * @return el euipo complejo que tenga ese serial
     */
    public EquipoComplejo loadEquipoBySerial(@Param("Eq_Serial") String serial);
    
    /**
     * Consulta un equipo complejo que posea una placa especifica
     * @param placa
     * @return el equipo complejo que posea esa placa
     */
    public EquipoComplejo loadEquipoByPlaca(@Param("Eq_Placa") int placa);
    
    /**
     * Reemplaza un equipo complejo por uno que tenga nueva informacion
     * @param eqv
     * @param eqn 
     */
    public void update(@Param("Eq_Viejo") EquipoComplejo eqv,@Param("Eq_Nuevo") EquipoComplejo eqn);
    
    /**
     * Elimina un equipo complejo de la base de datos
     * @param ec 
     */
    public void delete(@Param("Eq_Complejo") EquipoComplejo ec);
    
    /**
     * Consulta todos los equipos complejos que posee la base de datos
     * @return un arreglo con todos los equipos complejos
     */
    public ArrayList<EquipoComplejo> loadAll();
    
    /**
     * Consultar los equipos complejos que tengan un modelo especifico
     * @param modelo
     * @return un arreglo de equipos complejos que posean ese modelo
     */
    public ArrayList<EquipoComplejo> loadEquipoByModelo(@Param("Eq_Modelo") String modelo);
    
    /**
     * Consultar un modelo con un nombre especifico
     * @param nombre
     * @return un modelo que posea ese nombre
     */
    public Modelo loadModelo(@Param("Modelo")String nombre);
}