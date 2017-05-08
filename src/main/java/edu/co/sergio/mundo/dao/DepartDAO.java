/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.vo.Depart;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JulDa
 */
public class DepartDAO implements IBaseDatos<Depart>{

    @Override
    public List<Depart> findAll() {
       List<Depart> departos= null;
	    String query = "select nom_depto, count(*) as num from Depto natural join Proyecto natural join DeptoProyecto group by nom_depto;";
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
	    	if(departos == null){
	    		departos= new ArrayList<Depart>();
	    	}
	      
	        Depart registro= new Depart();
	        id= rs.getString("nom_depto");
	        registro.setNom_depto(id);
	        
	        total = rs.getInt("num");
	        registro.setNum(total); 
	        
	        departos.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Departamentos");
			e.printStackTrace();
		}
	    
	    return departos;
	}
    

    @Override
    public boolean insert(Depart t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Depart t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Depart t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
