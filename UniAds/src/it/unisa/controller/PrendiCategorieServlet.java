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

import it.unisa.model.Categoria;
import it.unisa.model.CategoriaModel;
import it.unisa.model.DriverManagerConnectionPool;

/**
 * Servlet implementation class PrendiCategorieServlet
 */
@WebServlet("/PrendiCategorieServlet")
public class PrendiCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		DriverManagerConnectionPool dmcp=(DriverManagerConnectionPool)getServletContext().getAttribute("DriverManager");
		CategoriaModel modelCategoria = new CategoriaModel(dmcp);
		try {
			
			ArrayList<Categoria> categorie = modelCategoria.doRetrieveAll("nome");
			Gson gson = new Gson();
			String jsonString = gson.toJson(categorie);
			response.getWriter().print(jsonString.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
