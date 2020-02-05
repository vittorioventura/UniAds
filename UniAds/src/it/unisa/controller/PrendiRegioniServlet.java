package it.unisa.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.Regione;
import it.unisa.model.RegioneModel;

@WebServlet("/PrendiRegioniServlet")
public class PrendiRegioniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		DriverManagerConnectionPool dmcp=(DriverManagerConnectionPool)getServletContext().getAttribute("DriverManager");
		RegioneModel modelRegione = new RegioneModel(dmcp);
		try {
			ArrayList<Regione> regioni = modelRegione.doRetrieveAll("nome");
			
			Gson gson = new Gson();
			String jsonString = gson.toJson(regioni);
			
			response.getWriter().print(jsonString.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
