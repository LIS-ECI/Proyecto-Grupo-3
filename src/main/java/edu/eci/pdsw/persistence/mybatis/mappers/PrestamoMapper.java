/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.mybatis.mappers;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.entities.Prestamo;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Hugo Alvarez
 */
public interface PrestamoMapper {

    /**
     * Inserta un prestamo
     *
     * @param p el prestamo a insertar
     */
    public void insertPrestamo(@Param("Prestamo") Prestamo p);

    /**
     *
     * Se agrega un equipo complejo a un prestamo asociado
     *
     * @param p , prestamo
     * @param e, equipo complejo
     */
    public void insertEquipoComplejo_Prestamo(@Param("Prestamo_id") Prestamo p, @Param("Equipo_id") EquipoComplejo e);

    /**
     *
     * Se agrega un equipo sencillo a un prestamo asociado
     *
     * @param p , prestamo
     * @param e, equipo sencillo
     */
    public void insertEquipoSencillo_Prestamo(@Param("Prestamo_id") Prestamo p, @Param("Equipo_id") EquipoSencillo e);

    /**
     *
     * Se actualiza los datos de un prestamo
     *
     * @param pn , prestamo
     */
    public void updatePrestamo(@Param("Prestamo") Prestamo pn);

    /**
     * Actualiza la relacion de equipo complejo en el prestamo
     *
     * @param p prestamo
     * @param eq equipo
     */
    public void updateEquipoComplejo(@Param("Prestamo") Prestamo p, @Param("Equipo") EquipoComplejo eq);

    /**
     *
     * Se actualiza la cantidad de que tiene en prestamo de un equipo sencillo
     *
     * @param p , prestamo
     * @param es, equipo sencillo
     */
    public void updateEquipoSencillo(@Param("Prestamo") Prestamo p, @Param("Eq_Sencillo") EquipoSencillo es);

    /**
     *
     * Se cargan el prestamo de una persona en una fecha dada
     *
     * @param fecha , fecha del prestamo
     * @param carne, Carnet de la persona. Prestamista
     * @return Prestamo
     */
    public Prestamo loadPrestamo(@Param("time") Timestamp fecha, @Param("carnet") String carne);

    /**
     *
     * Se cargan todos los prestamos
     *
     * @return Lista prestamos
     */
    public List<Prestamo> loadAll();

    /**
     *
     * Se cargan los prestamos por fecha
     *
     * @param fecha , fecha del prestamo
     * @return lista Prestamos
     */
    public List<Prestamo> loadByFecha(@Param("fecha") Timestamp fecha);

    /**
     *
     * Se cargan todos los prestamos de una persona
     *
     * @param carne, Carnet de la persona. Prestamista
     * @return lista de Prestamos
     */
    public List<Prestamo> loadByCarne(@Param("carne") String carne);

    /**
     *
     * Se cargan los prestamos que estan en mora
     *
     * @return lista de Prestamos
     */  
    public List<Prestamo> loadMorosos();

    /**
     *
     * Se cargan todos prestamos en los que aparece el equipo complejo
     *
     * @param equipocomplejo, equipo complejo
     * @return lista de Prestamos
     */
    public List<Prestamo> loadByEquipoComplejo(@Param("Eq_Complejo") EquipoComplejo equipocomplejo);

    /**
     *
     * Se cargan todos prestamos en los que aparece el equipo sencillo
     *
     * @param equiposencillo, equipo complejo
     * @return lista de Prestamos
     */
    public List<Prestamo> loadByEquipoSencillo(@Param("Eq_Sencillo") EquipoSencillo equiposencillo);

    /**
     * Solicita la fecha actual de la base de datos
     *
     * @return la fecha actual de la base de datos
     */
    public Date loadCurrDate();
    
    /**
     * Carga el prestamo del equipo en el cual esta activo
     * 
     * @param equipo a buscar
     * @return el prestamo actual de ese equipo
     */
    public Prestamo loadPrestamoActualEquipo(@Param("Equipo")EquipoComplejo equipo);
    
    /**
     * Calcula la diferencia en horas entre la fecha actual y la fecha de fin estimada del prestamo
     * 
     * @param pres el prestamo que se usará para consultar
     * @return la diferencia en horas entre la fecha actual y la fecha de fin estimada del prestamo
     */
    public long diffHours(@Param("Prestamo") Prestamo pres);
    
    /**
     * Carga el prestamo actual de la persona
     * 
     * @param carnet de la persona
     * @return el prestamo activo de esa persona
     */
    public Prestamo loadActualDePersona(String carnet);
}
