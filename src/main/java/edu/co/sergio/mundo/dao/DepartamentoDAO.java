package edu.co.sergio.mundo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.co.sergio.mundo.vo.Departamento;
import edu.co.sergio.mundo.vo.Proyecto;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Isabel-Fabian
 * @since 12/08/2015
 * @version 2
 * Clase que permite la gestion de la tabla Depto en la base de datos.
 * 
 * CREATE TABLE Depto(
 *     id_depto integer,
 *     nom_depto varchar(40),
 *     PRIMARY KEY(id_depto)
 * );
 */
 

public class DepartamentoDAO implements IBaseDatos<Proyecto> {

	/**
	 * Funcion que permite obtener una lista de los departamentos existentes en la base de datos
	 * @return List<Departamento> Retorna la lista de Departamentos existentes en la base de datos
	 */
	public List<Proyecto> findAll() {
		List<Proyecto> proyectos= null;
	    String query = "select nom_proy, count(*) as total from Proyecto left join Recurso using (id_proyecto) group by nom_proy";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    String id =null;
	    int total = 0;
	
	    while (rs.next()){
	    	if(proyectos == null){
	    		proyectos= new ArrayList<Proyecto>();
	    	}
	      
	        Proyecto registro= new Proyecto();
	        id= rs.getString("nom_proy");
	        registro.setNom_proyecto(id);
	        
	        total = rs.getInt("total");
	        registro.setTotal(total); 
	        
	        proyectos.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Departamentos");
			e.printStackTrace();
		}
	    
	    return proyectos;
	}

    @Override
    public boolean insert(Proyecto t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Proyecto t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Proyecto t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	
	/**
	 * Funcion que permite realizar la insercion de un nuevo registro en la tabla Departamento
	 * @param Departamento recibe un objeto de tipo Departamento 
	 * @return boolean retorna true si la operacion de insercion es exitosa.
	 */
	
}
