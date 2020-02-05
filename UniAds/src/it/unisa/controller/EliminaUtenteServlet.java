package it.unisa.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.Utente;
import it.unisa.model.UtenteModel;

/**
 * Servlet implementation class EliminaUtenteServlet
 */
@WebServlet("/Admin/EliminaUtenteServlet")
public class EliminaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("emailUtente"); 
		Utente utente = new Utente();
		utente.setEmail(email);
		DriverManagerConnectionPool dmcp = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		UtenteModel modelUtente = new UtenteModel(dmcp);
		try {
			if((utente=modelUtente.doRetrieveByKey(utente))!= null && utente.getEmail().equals(email)) {
				modelUtente.doDelete(utente);
				request.setAttribute("completamentoEliminazioneUtente", "Utente eliminato");
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Admin/OperazioniAdmin.jsp");
				d.forward(request, response);
			}
			else {
				request.setAttribute("erroreEliminazioneUtente", "Impossibilre eliminare l'utente");
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Admin/OperazioniAdmin.jsp");
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
