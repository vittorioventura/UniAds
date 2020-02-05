package it.unisa.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.Amministratore;
import it.unisa.model.AmministratoreModel;
import it.unisa.model.Corriere;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.Utente;
import it.unisa.model.UtenteModel;

/**
 * Servlet implementation class ModificaRuoloServlet
 */
@WebServlet("/Admin/ModificaRuoloServlet")
public class ModificaRuoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("emailUtente");
		String ruolo = request.getParameter("ruolo");
		DriverManagerConnectionPool dmcp = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		
		UtenteModel modelUtente = new UtenteModel(dmcp);
		Utente utente = new Utente();
		utente.setEmail(email);
		Corriere corriere = new Corriere();
		corriere.setEmail(email);
	
		AmministratoreModel modelAdmin = new AmministratoreModel(dmcp);
		Amministratore admin = new Amministratore();
		admin.setEmail(email);
		
		
		try {
			if(ruolo.equals("Utente")) {
				utente = modelUtente.doRetrieveByKey(utente);
				if(utente!=null && utente.getEmail().equals(email)) {
					modelUtente.doDelete(utente);
					Utente modifica = new Utente();
					modifica.setEmail(email);
					modifica.setCognome(utente.getCognome());
					modifica.setNome(utente.getNome());
					modifica.setPassword(utente.getPassword());
					modifica.setIndirizzo(utente.getIndirizzo());
					
					while(utente.getEmail().equals("")) {
						modelUtente.doRetrieveByKey(utente);
					}
					modelUtente.doSave(modifica);
					request.setAttribute("completamentoModifica", "Ruolo modificato");
					RequestDispatcher d = getServletContext().getRequestDispatcher("/Admin/OperazioniAdmin.jsp");
					d.forward(request, response);
				}
				else {
					request.setAttribute("erroreModificaRuolo", "Impossibile modificare ruolo");
					RequestDispatcher d = getServletContext().getRequestDispatcher("/Admin/OperazioniAdmin.jsp");
					d.forward(request, response);

				}
			}
			else {
				utente = modelUtente.doRetrieveByKey(utente);
				if(utente!=null && utente.getEmail().equals(email)) {
					modelUtente.doDelete(utente);
					Amministratore modifica = new Amministratore();
					modifica.setEmail(email);
					modifica.setCognome(utente.getCognome());
					modifica.setNome(utente.getNome());
					modifica.setPassword(utente.getPassword());
					modifica.setIndirizzo(utente.getIndirizzo());
					while(utente.getEmail().equals("")) {
						modelUtente.doRetrieveByKey(utente);
					}
					modelAdmin.doSave(modifica);
					request.setAttribute("completamentoModifica", "Ruolo modificato");
					RequestDispatcher d = getServletContext().getRequestDispatcher("/Admin/OperazioniAdmin.jsp");
					d.forward(request, response);
				}
				else {
					request.setAttribute("erroreModificaRuolo", "Impossibile modificare ruolo");
					RequestDispatcher d = getServletContext().getRequestDispatcher("/Admin/OperazioniAdmin.jsp");
					d.forward(request, response);

				}
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
 