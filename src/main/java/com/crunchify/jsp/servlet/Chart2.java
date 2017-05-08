/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.DepartDAO;
import edu.co.sergio.mundo.dao.DepartamentoDAO;
import edu.co.sergio.mundo.vo.Depart;
import edu.co.sergio.mundo.vo.Proyecto;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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
public class Chart2 {
    
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
                DepartDAO Dao= new DepartDAO();
                ArrayList<Depart> dep = (ArrayList<Depart>) Dao.findAll();
                
                for (Depart depart : dep) {
                dataset.setValue(depart.getNom_depto(), depart.getNum());
                
            }
 /*               
                dataset.setValue("Ford", 23.3);
		dataset.setValue("Chevy", 32.4);
		dataset.setValue("Yugo", 44.2);
*/
		boolean legend = true;
		boolean tooltips = false;
		boolean urls = false;

		JFreeChart chart = ChartFactory.createPieChart("Deptos", dataset, legend, tooltips, urls);

		chart.setBorderPaint(Color.GREEN);
		chart.setBorderStroke(new BasicStroke(5.0f));
		chart.setBorderVisible(true);

		return chart;
	}
}
