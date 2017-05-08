/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.Consulta3DAO;
import edu.co.sergio.mundo.vo.Consulta3;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author JulDa
 */
public class Chart3 extends HttpServlet {
    
     private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        	response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		JFreeChart chart = getChart();
		int width = 500;
		int height = 350;
		ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);

	}

	public JFreeChart getChart() {
		
                DefaultPieDataset dataset = new DefaultPieDataset();
	        //Crear la capa de servicios que se enlace con el DAO
                Consulta3DAO Dao= new Consulta3DAO();
                ArrayList<Consulta3> con = (ArrayList<Consulta3>) Dao.findAll();
                
                for (Consulta3 consulta3 : con) {
                    dataset.setValue(consulta3.getNom_depto(), consulta3.getTotal());
                
            }
 /*               
                dataset.setValue("Ford", 23.3);
		dataset.setValue("Chevy", 32.4);
		dataset.setValue("Yugo", 44.2);
*/
		boolean legend = true;
		boolean tooltips = false;
		boolean urls = false;

		JFreeChart chart = ChartFactory.createPieChart("Contratos", dataset, legend, tooltips, urls);

		chart.setBorderPaint(Color.GREEN);
		chart.setBorderStroke(new BasicStroke(5.0f));
		chart.setBorderVisible(true);

		return chart;
	}
    
}
