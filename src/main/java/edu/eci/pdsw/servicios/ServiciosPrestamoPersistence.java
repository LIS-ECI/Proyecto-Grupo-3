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
import edu.eci.pdsw.entities.PrestamoException;
import edu.eci.pdsw.log.Registro;
import edu.eci.pdsw.persistence.DAOEquipoComplejo;
import edu.eci.pdsw.persistence.DAOFactory;
import edu.eci.pdsw.persistence.DAOPersona;
import edu.eci.pdsw.persistence.DAOPrestamo;
import edu.eci.pdsw.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2105684
 */
public class ServiciosPrestamoPersistence extends ServiciosPrestamo {

    private DAOPrestamo basePaciente;
    private DAOFactory daoF;
    private DAOPersona basePersona;
    
    public ServiciosPrestamoPersistence() {
    	try {
            
            InputStream input = getClass().getClassLoader().getResource("applicationconfig.properties").openStream();
            
            Properties properties=new Properties();
            properties.load(input);
            daoF = DAOFactory.getInstance(properties);
          
        } catch (IOException ex) {
            Registro.anotar(ex);
        }
    }

    @Override
    public List<Prestamo> consultarPrestamosMorosos() throws ExcepcionServicios {
        List<Prestamo> morosos=new LinkedList<>();
        try{
            daoF.beginSession();
            basePaciente=daoF.getDaoPrestamo();
            morosos=basePaciente.loadMorosos();
            Collections.sort(morosos);
        }catch(PersistenceException e){
            throw new ExcepcionServicios(e,e.getLocalizedMessage());
        }finally{
            daoF.endSession();
            return morosos;
        }
        
    }

    @Override
    public List<Prestamo> consultarPrestamosPersona(Persona p) {
        List<Prestamo> prestamos=new LinkedList<>();
        try{
            daoF.beginSession();
            basePaciente=daoF.getDaoPrestamo();
            prestamos=basePaciente.loadByCarne(p.getCarnet());
            Collections.sort(prestamos);
        }catch(PersistenceException e){
            throw new ExcepcionServicios(e,e.getLocalizedMessage());
        }finally{
            daoF.endSession();
            return prestamos;
        }
    }

    @Override
    public List<Prestamo> consultarPrestamosEquipoComplejo(EquipoComplejo ec) {
        List<Prestamo> prestamos=new LinkedList<>();
        try{
            daoF.beginSession();
            basePaciente=daoF.getDaoPrestamo();
            prestamos=basePaciente.loadByEquipoComplejo(ec);
            Collections.sort(prestamos);
        }catch(PersistenceException e){
            throw new ExcepcionServicios(e,e.getLocalizedMessage());
        }finally{
            daoF.endSession();
            return prestamos;
        }
    }

    @Override
    public List<Prestamo> consultarTodos() {
        List<Prestamo> prestamos=new LinkedList<>();
        try{
            daoF.beginSession();
            basePaciente=daoF.getDaoPrestamo();
            prestamos=basePaciente.loadAll();
            Collections.sort(prestamos);
        }catch(PersistenceException e){
            throw new ExcepcionServicios(e,e.getLocalizedMessage());
        }finally{
            daoF.endSession();
            return prestamos;
        }
    }

    @Override
    public void registrarPrestamo(Prestamo pres) throws ExcepcionServicios {
       try{
           daoF.beginSession();
           basePaciente=daoF.getDaoPrestamo();
           basePaciente.save(pres);
           daoF.commitTransaction();
       }catch(PersistenceException e){
           daoF.rollbackTransaction();
           throw new ExcepcionServicios(e,e.getLocalizedMessage());
       }finally{
           daoF.endSession();
       }
    }

    @Override
    public void registarDevolucion(int persona, String equipo, int cantidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrarDevolucion(int equipo) throws ExcepcionServicios{
        try{
            daoF.beginSession();
            DAOEquipoComplejo dec=daoF.getDaoEquipoComplejo();
            basePaciente=daoF.getDaoPrestamo();
            //Cargo el equipo a partir de su placa y luego cargo todos los prestamos asociados a ese equipo
            EquipoComplejo loaded=dec.load(equipo);
            List<Prestamo> tmp=basePaciente.loadByEquipoComplejo(loaded);
            boolean found=false;
            Prestamo prestamoEquipo=null;
            //Busco entre los prestamos asociados uno que tenga el equipo como faltante (Deberia ser el unico)
            for(int i=0;i<tmp.size() && !found;i++){
                if(tmp.get(i).esFaltante(loaded)){
                    prestamoEquipo=tmp.get(i);
                    found=true;
                }
            }
            if(prestamoEquipo==null)throw new ExcepcionServicios("El equipo no esta para devolucion");
            //De ese prestamo devuelvo el equipo y actualizo en la base de datos
            prestamoEquipo.returnEquipoComplejo(loaded);
            basePaciente.update(prestamoEquipo);
            daoF.commitTransaction();
        }catch(PersistenceException|PrestamoException e){
            daoF.rollbackTransaction();
            throw new ExcepcionServicios(e,e.getLocalizedMessage());
        }finally{
            daoF.endSession();
        }
    }

    //¿Esto no deberia estar en servicios persona?
    @Override
    public Persona personaCarne(String carne)throws ExcepcionServicios {
        Persona p;
        try{
            daoF.beginSession();
            DAOPersona dp=daoF.getDaoPersona();
            p=dp.load(carne);
        }catch(PersistenceException e){
            throw new ExcepcionServicios(e,e.getLocalizedMessage());
        }
        return p;
    }
    
}
