/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.servicios;

/**
 *
 * @author 2105684
 */
public abstract class ServiciosPrestamo {
    
    private static final ServiciosPrestamo instance=new ServiciosPrestamoPersistence();
    
}
