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
 * Servlet implementation class EliminaAnnuncioServlet
 */
@WebServlet("/Admin/EliminaAnnuncioServlet")
public class EliminaAnnuncioServlet extends HttpServlet {
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
				request.setAttribute("completamentoEliminazioneAnnuncio", "Annuncio eliminato");
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Admin/OperazioniAdmin.jsp");
				d.forward(request, response);
			}
			else {
				request.setAttribute("erroreEliminazioneAnnuncio", "Impossibile eliminare annuncio");
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Admin/OperazioniAdmin.jsp");
				d.forward(request, response);

			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

}
