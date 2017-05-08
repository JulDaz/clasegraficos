/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.vo.Consulta3;
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
public class Consulta3DAO implements IBaseDatos<Consulta3>{

    @Override
    public List<Consulta3> findAll() {
        List<Consulta3> consul= null;
	    String query = "select nom_depto, tipo_contrato, count(*) as total from Depto join Empleado using (id_depto) group by nom_depto, tipo_contrato having count(*)>1";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(Consulta3DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    String id =null;
            String f2= null;
	    int total = 0;
	
	    while (rs.next()){
	    	if(consul == null){
	    		consul= new ArrayList<Consulta3>();
	    	}
	      
	        Consulta3 registro= new Consulta3();
	        id= rs.getString("nom_depto");
	        registro.setNom_depto(id);
	        
                f2= rs.getString("tipo_contrato");
	        registro.setTipo_contrato(f2);
                
	        total = rs.getInt("total");
	        registro.setTotal(total);
	        
	        consul.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Departamentos");
			e.printStackTrace();
		}
	    
	    return consul;
    }

    @Override
    public boolean insert(Consulta3 t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Consulta3 t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Consulta3 t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
