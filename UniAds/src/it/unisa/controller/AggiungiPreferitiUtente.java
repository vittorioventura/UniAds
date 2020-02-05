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
import it.unisa.model.Preferiti;
import it.unisa.model.PreferitiModel;
import it.unisa.model.Utente;

/**
 * Servlet implementation class AggiungiPreferitiUtente
 */
@WebServlet("/User/AggiungiPreferitiUtente")
public class AggiungiPreferitiUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		String emailUtente = request.getParameter("emailUtente");
   		String emailAnnuncio = request.getParameter("emailAnnuncio");
   		String titoloAnnuncio = request.getParameter("titoloAnnuncio");
   		Boolean tipo = Boolean.parseBoolean(request.getParameter("tipo"));
   		DriverManagerConnectionPool dmcp = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
   		PreferitiModel modelPreferiti = new PreferitiModel(dmcp);
   		
   		if(emailUtente != null && emailAnnuncio != null && titoloAnnuncio != null ) {
   			Preferiti preferiti = new Preferiti();
   			preferiti.setEmailUtente(emailUtente);
   			preferiti.setEmailUtenteAnnuncio(emailAnnuncio);
   			preferiti.setTitoloAnnuncio(titoloAnnuncio);
   			try {
				preferiti=modelPreferiti.doRetrieveByKey(preferiti);
				System.out.println(preferiti.getEmailUtente());
				if(preferiti != null && preferiti.getEmailUtente().equals("") && preferiti.getTitoloAnnuncio().equals("") && preferiti.getEmailUtenteAnnuncio().equals("") && tipo.equals(false)) {
					preferiti.setEmailUtente(emailUtente);
					preferiti.setEmailUtenteAnnuncio(emailAnnuncio);
					preferiti.setTitoloAnnuncio(titoloAnnuncio);
					modelPreferiti.doSave(preferiti);
					AnnuncioModel modelAnnuncio = new AnnuncioModel(dmcp);
					Annuncio annuncio = new Annuncio();
					annuncio.setTitolo(titoloAnnuncio);
					Utente utente = new Utente();
					utente.setEmail(emailAnnuncio);
					annuncio.setUtente(utente);
					annuncio = modelAnnuncio.doRetrieveByKey(annuncio);
					RequestDispatcher d = getServletContext().getRequestDispatcher("/Tutti/PrelevaAnnunciServlet?email="+emailUtente+"&universita=0&categorie=0&search=0");
					d.forward(request, response);
				}
				else {
					preferiti.setEmailUtente(emailUtente);
					preferiti.setEmailUtenteAnnuncio(emailAnnuncio);
					preferiti.setTitoloAnnuncio(titoloAnnuncio);
					modelPreferiti.doDelete(preferiti);
					System.out.println("Ciao "+emailUtente+" "+titoloAnnuncio);
					RequestDispatcher d = getServletContext().getRequestDispatcher("/User/PrendiPreferitiUtente?email="+emailUtente);
					d.forward(request, response);
				}
				
   			}
   			catch (SQLException e) {
		
				e.printStackTrace();
			}
   			
   		}
   		else {
   			response.sendRedirect("/UniAds/Tutti/errore404.jsp");
   		}
   		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
