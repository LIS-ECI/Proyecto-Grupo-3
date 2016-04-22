/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.entities;

import com.mysql.jdbc.Blob;

/**
 *
 * @author Zawsx
 */
public class Modelo {
    private int vidaUtil;
    private String nombre;
    private String clase;
    private long valorComercial;
    private byte[] fotografia;
    private String descripcion;
    private String accesorios;


    public Modelo(int vidaU, String name, byte[] foto, String clas, long valor) {
       vidaUtil=vidaU;
       nombre=name;
       clase=clas;
       valorComercial=valor;
       fotografia=foto;
    }

    public Modelo(int vidaUtil, String nombre, String clase, long valorComercial, byte[] fotografia, String descripcion, String accesorios) {
        this.vidaUtil = vidaUtil;
        this.nombre = nombre;
        this.clase = clase;
        this.valorComercial = valorComercial;
        this.fotografia = fotografia;
        this.descripcion = descripcion;
        this.accesorios = accesorios;
    }

    public Modelo() {
    }

    public void setValorComercial(long valorComercial) {
        this.valorComercial = valorComercial;
    }
    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }

    public byte[] getFotografia() {
        return fotografia;
    }
    
    public void setVidaUtil(int vu){
        this.vidaUtil=vu;
    }
    
    public int getVidaUtil(){
        return vidaUtil;
    }
    
    public void setNombre(String n){
        this.nombre=n;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setClase(String c){
        this.clase=c;
    }
    
    public String getClase(){
        return clase;
    }
    
    public void setValorComercial(int vc){
        this.valorComercial=vc;
    }
    
    public long getValorComercial(){
        return valorComercial;
    }
    
    public void setAccesorios(String ac){
        this.accesorios=ac;
    }
    
    public String getAccesorios(){
        return accesorios;
    }
    
    @Override
    public String toString(){
        String res="Modelo:["+vidaUtil+","+nombre+","+clase+","+valorComercial+"]\n";
        return res;
    }
    
}
