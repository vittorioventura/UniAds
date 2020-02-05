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
import it.unisa.model.Preferiti;
import it.unisa.model.PreferitiModel;

/**
 * Servlet implementation class PreferitiAjax
 */
@WebServlet("/PreferitiAjax")
public class PreferitiAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String emailUtente = request.getParameter("email");
		DriverManagerConnectionPool dmcp = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		PreferitiModel modelAnnuncio = new PreferitiModel(dmcp);
		if(emailUtente!=null && !emailUtente.equals("") && !emailUtente.equals("null")) {
			try {
				ArrayList<Preferiti> annunci=modelAnnuncio.doRetrieveAll("titolo_annuncio");
				ArrayList<Preferiti> annunciEffettivi = new ArrayList<Preferiti>(); 
				for(Preferiti a: annunci) {
					if(emailUtente.equals(a.getEmailUtente())) {
						annunciEffettivi.add(a);
					}
				}
				Gson gson = new Gson();
				String jsonString = gson.toJson(annunciEffettivi);
				response.getWriter().print(jsonString.toString());
			
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
