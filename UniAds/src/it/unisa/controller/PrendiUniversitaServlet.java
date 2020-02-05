package it.unisa.controller;
//ciao
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
import it.unisa.model.Universita;
import it.unisa.model.UniversitaModel;

/**
 * Servlet implementation class PrendiUniversitaServlet
 */
@WebServlet("/PrendiUniversitaServlet")
public class PrendiUniversitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrendiUniversitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		DriverManagerConnectionPool dmcp=(DriverManagerConnectionPool)getServletContext().getAttribute("DriverManager");
		UniversitaModel modelUniversita = new UniversitaModel(dmcp);
		try {
			
			ArrayList<Universita> uni = modelUniversita.doRetrieveAll("sigla");
			Gson gson = new Gson();
			String jsonString = gson.toJson(uni);
			response.getWriter().print(jsonString.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
