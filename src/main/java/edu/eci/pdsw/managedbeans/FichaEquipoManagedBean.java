/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.managedbeans;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.servicios.ExcepcionServicios;
import edu.eci.pdsw.servicios.ServiciosEquipoComplejo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author amoto
 */
@ManagedBean(name = "FichaEquipo")
@SessionScoped
public class FichaEquipoManagedBean {
    private String placaAConsultar;
    private ServiciosEquipoComplejo SEC=ServiciosEquipoComplejo.getInstance();
    private EquipoComplejo consultado;
    private boolean consulto;
    public void ConsultarEquipoByPlaca(){
        if(getPlacaAConsultar()!=null && getPlacaAConsultar().length()>0){

            try {
                consultado= SEC.consultarPorPlaca(getPlacaAConsultar());
            } catch (ExcepcionServicios ex) {
                facesError(ex.getMessage());
            }
            if(consultado!=null)
                consulto=true;
        }else{
            facesError("Por favor coloque una placa para consultar");
        }
    }
    
    /**
     * Muestra un mensaje de error en la vista
     *
     * @param message Mensaje de error
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + message, null));
    }

    /**
     * Muestra un mensaje de informacion en la vista
     *
     * @param message Mensaje de informativo
     */
    public void facesInfo(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    /**
     * Muestra un mensaje de alarma en la vista
     *
     * @param message Mensaje de Alarma
     */
    public void facesWarn(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
    }

    /**
     * Muestra un mensaje de error grave en la vista
     *
     * @param message Mensaje fatal
     */
    public void facesFatal(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, null));
    }

    /**
     * @return the placaAConsultar
     */
    public String getPlacaAConsultar() {
        return placaAConsultar;
    }

    /**
     * @param placaAConsultar the placaAConsultar to set
     */
    public void setPlacaAConsultar(String placaAConsultar) {
        this.placaAConsultar = placaAConsultar;
    }


}
