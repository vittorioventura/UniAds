package it.unisa.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.Annuncio;
import it.unisa.model.AnnuncioModel;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.GenericUser;
import it.unisa.model.Utente;

/**
 * Servlet implementation class EliminaAnnuncioUtente
 */
@WebServlet("/User/EliminaAnnuncioUtente")
public class EliminaAnnuncioUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titoloAnnuncio = request.getParameter("titoloAnnuncio");
		String emailUtente = request.getParameter("emailUtente");
		DriverManagerConnectionPool dmcp = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		AnnuncioModel modelAnnuncio = new AnnuncioModel(dmcp);
		Annuncio annuncio = new Annuncio();
		GenericUser utente = new Utente();
		utente.setEmail(emailUtente);
		annuncio.setTitolo(titoloAnnuncio);
		annuncio.setUtente(utente);
		try {
			if((annuncio=modelAnnuncio.doRetrieveByKey(annuncio)) != null && annuncio.getTitolo().equals(titoloAnnuncio) && annuncio.getUtente().getEmail().equals(emailUtente)) {
				modelAnnuncio.doDelete(annuncio);
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Tutti/PrelevaAnnunciServlet?tutti="+emailUtente);
				d.forward(request, response);
			}
			else {
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Tutti/PrelevaAnnunciServlet?tutti="+emailUtente);
				d.forward(request, response);

			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
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
