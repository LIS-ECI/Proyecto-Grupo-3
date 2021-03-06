/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoException;
import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.Test;
import edu.eci.pdsw.entities.Modelo;
import edu.eci.pdsw.entities.OrdenCompra;
import edu.eci.pdsw.persistence.DAOEquipoComplejo;
import edu.eci.pdsw.persistence.DAOEquipoSencillo;
import edu.eci.pdsw.persistence.DAOFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;


/**
 *
 * @author Julian Devia
 */
public class RegistrarEquipoTest {
    public static InputStream input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
    public static Properties properties=new Properties();
    
     public RegistrarEquipoTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void clearDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:file:./target/db/testdb2;MODE=MYSQL", "anonymous", "");
        Statement stmt = conn.createStatement();
        stmt.execute("delete from Informacion_Compra");
        stmt.execute("delete from Equipos_Complejos");
        stmt.execute("delete from Equipos_Sencillos");
        stmt.execute("delete from Modelos");
        conn.commit();
    }
      
    
        //Deberia registrar un equipo complejo
    @Test
    public void CE1() throws IOException, PersistenceException, EquipoException{
        properties.load(input);
        DAOFactory daof=DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo reg=daof.getDaoEquipoComplejo();
        
        Modelo mod = new Modelo(100000,"Destornillador de estrella","A","udfyzsiudfyziduvz","Destornillador",5000);
        reg.saveModelo(mod);
        daof.commitTransaction();
        OrdenCompra dg=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi","orden","activo");
        EquipoComplejo ec=new EquipoComplejo(mod,"shdasdh564","123456",dg,0);
        ec.setEstado("hola");
        reg.save(ec);
        daof.commitTransaction();
        EquipoComplejo test=reg.load(ec.getPlaca());
        daof.endSession();
        Assert.assertEquals("no registra un equipo complejo",ec,test);
    }
    
    //No deberia registrar dos veces el mismo equipo
    @Test
    public void CE2() throws PersistenceException, EquipoException{
        Modelo mod = new Modelo(100000,"Destornillador de estrella","P",null,"Destornillador",5000);
        OrdenCompra dg=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi","orden","activo");
        EquipoComplejo ec=new EquipoComplejo(mod,"shdasdh564","123456",dg,0);
        DAOFactory daof = null;
        try{
        properties.load(input);
        daof=DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo reg=daof.getDaoEquipoComplejo();
        reg.saveModelo(mod);
        daof.commitTransaction();
       
        reg.save(ec);
        daof.commitTransaction();
        reg.save(ec);
        daof.commitTransaction();
        daof.endSession();
        Assert.fail("Registro dos veces el mismo equipo");
        }catch(IOException | PersistenceException e){
            Assert.assertEquals(e.getMessage(),"El equipo con placa "+ec.getPlaca()+" ya está registrado");
        }finally{
            daof.endSession();
        }
    }
    //Deberia registrar un equipo sencillo
    @Test
    public void CE3() throws EquipoException{
        try {
            properties.load(input);
            DAOFactory daof=DAOFactory.getInstance(properties);
            daof.beginSession();
            DAOEquipoSencillo reg=daof.getDaoEquipoSencillo();
            EquipoSencillo es=new EquipoSencillo("Cable UTP","Cable",100,400);
            reg.save(es);
            daof.commitTransaction();
            EquipoSencillo test =reg.load(es.getNombre());
            daof.commitTransaction();
            daof.endSession();
            Assert.assertEquals("fallo",es.toString(),test.toString());
        } catch (PersistenceException | IOException ex) {
            Assert.fail("no debio lanzar excepcion");
        }
    }
    
    //No deberia registrar mas de una vez el mismo equipo sencillo dos veces
    @Test
     public void CE4() throws PersistenceException, EquipoException{
         EquipoSencillo es=new EquipoSencillo("Cable UTP","Cable",100,400);
         DAOFactory daof=null;
        try{
            properties.load(input);
            daof=DAOFactory.getInstance(properties);
            daof.beginSession();
            DAOEquipoSencillo reg=daof.getDaoEquipoSencillo();

            reg.save(es);
            daof.commitTransaction();
            reg.save(es);
            daof.commitTransaction();
            daof.endSession();
            Assert.fail("Registro dos veces el mismo equipo"); 
        }catch(IOException | PersistenceException e){
            Assert.assertEquals(e.getMessage(),"El equipo con nombre "+es.getNombre()+" ya esta registrado");
        }finally{
            daof.endSession();
        }
    }
     
     //Deberia registrar el modelo del equipo
    @Test
     public void CE5() throws IOException,PersistenceException, EquipoException{
        properties.load(input);
        DAOFactory daof=DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo reg=daof.getDaoEquipoComplejo();
        
        Modelo mod=new Modelo(4000,"Nombre","Marca","Foto","Clase",2000);
        reg.saveModelo(mod);
        daof.commitTransaction();
        Modelo test=reg.loadModelo(mod.getNombre());
        OrdenCompra dg=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi","orden","activo");
        EquipoComplejo ec=new EquipoComplejo(test,"serial","123456",dg,0);
        reg.save(ec);
        daof.commitTransaction();
        ArrayList<EquipoComplejo> prueba=reg.loadByModelo(mod.getNombre());
        daof.commitTransaction();
        daof.endSession();
        Assert.assertEquals("no registra el modelo del equipo",ec.getModelo_Eq(), prueba.get(0).getModelo_Eq());
     }
}
    
