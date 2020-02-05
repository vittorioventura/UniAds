package it.unisa.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class PrendiPreferiti
 */
@WebServlet("/Tutti/PrendiPreferiti")
public class PrendiPreferiti extends HttpServlet {
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
				request.setAttribute("numeroAnnunciPreferiti", annunciEffettivi.size());
				request.setAttribute("annunciPreferiti", annunciEffettivi);
				request.setAttribute("annunciJsonPreferiti", new Gson().toJson(annunciEffettivi));
				
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Tutti/VisualizzaAnnunci.jsp");
				d.forward(request, response);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
		else {
			System.out.println("Preferiti");
			RequestDispatcher d = getServletContext().getRequestDispatcher("/Tutti/VisualizzaAnnunci.jsp");
			d.forward(request, response);
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
