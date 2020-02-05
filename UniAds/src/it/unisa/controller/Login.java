package it.unisa.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.model.Amministratore;
import it.unisa.model.AmministratoreModel;
import it.unisa.model.Corriere;
import it.unisa.model.CorriereModel;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.GenericUser;
import it.unisa.model.Utente;
import it.unisa.model.UtenteModel;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		out.write("Error: GET method is used but POST method is required");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String ruolo = request.getParameter("ruolo");
		DriverManagerConnectionPool dmcp=(DriverManagerConnectionPool)getServletContext().getAttribute("DriverManager"); 
//		System.out.println(email);
//		System.out.println(password);
//		System.out.println(ruolo);
		
		if(ruolo.equals("Utente")) {
			Utente utente = new Utente();
			utente.setEmail(email);
			utente.setPassword(password);
			UtenteModel modelUtente = new UtenteModel(dmcp);
			
			try {
				
				utente = modelUtente.doRetrieveByKey(utente);
				
				if(utente!=null && utente.getEmail().equals(email) && utente.getPassword().equals(password)) {
					if(utente.getRuolo().equals(GenericUser.Ruolo.UTENTE)) {
						session.setAttribute("login", true);		
						session.setAttribute("ruolo", "UTENTE");
						session.setAttribute("utente", utente);
						response.sendRedirect("/UniAds/Tutti/HomePage.jsp");
					}
					else {
						
						String errore="Email o password o ruolo errati ";
						request.setAttribute("errore", errore);
						RequestDispatcher  d = getServletContext().getRequestDispatcher("/Tutti/Login.jsp");
						d.forward(request, response);

					}
				}
				else {
					
					String errore="Email o password o ruolo errati";
					request.setAttribute("errore", errore);
					RequestDispatcher  d = getServletContext().getRequestDispatcher("/Tutti/Login.jsp");
					d.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(ruolo.equals("Corriere")) {
				Corriere corriere = new Corriere();
				corriere.setEmail(email);
				corriere.setPassword(password);
				
				CorriereModel modelCorriere = new CorriereModel(dmcp);
			try {
				corriere = modelCorriere.doRetrieveByKey(corriere);
				System.out.println(corriere.getEmail());
				System.out.println(corriere.getPassword());
				System.out.println(corriere.getRuolo());
				if(corriere!=null && corriere.getEmail().equals(email) && corriere.getPassword().equals(password)) {
					if(corriere.getRuolo().equals(GenericUser.Ruolo.CORRIERE)) {	
						session.setAttribute("login", true);
						session.setAttribute("utente", corriere);
						session.setAttribute("ruolo", "CORRIERE");
						response.sendRedirect("/UniAds/Tutti/HomePage.jsp");
					}
					else {
						String errore="Email o password o ruolo errati ";
						request.setAttribute("errore", errore);
						RequestDispatcher  d = getServletContext().getRequestDispatcher("/Tutti/Login.jsp");
						d.forward(request, response);
					}
				}
				else {
					String errore="Email o password o ruolo errati";
					request.setAttribute("errore", errore);
					RequestDispatcher  d = getServletContext().getRequestDispatcher("/Tutti/Login.jsp");
					d.forward(request, response);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			Amministratore amministratore = new Amministratore();
			AmministratoreModel modelAmministratore = new AmministratoreModel(dmcp);
		try {
			amministratore.setEmail(email);
			amministratore.setPassword(password);
			amministratore = modelAmministratore.doRetrieveByKey(amministratore);	
			if(amministratore!=null && amministratore.getEmail().equals(email) && amministratore.getPassword().equals(password)) {
				System.out.println(amministratore.getRuolo());
				System.out.println("CIAO");
				if(amministratore.getRuolo().equals(GenericUser.Ruolo.AMMINISTRATORE)) {	

					session.setAttribute("login", true);
					session.setAttribute("ruolo", "AMMINISTRATORE");
					session.setAttribute("utente", amministratore);
					response.sendRedirect("/UniAds/Tutti/HomePage.jsp");
				}
				else {
					String errore="Email o password o ruolo errati ";
					request.setAttribute("errore", errore);
					RequestDispatcher  d = getServletContext().getRequestDispatcher("/Tutti/Login.jsp");
					d.forward(request, response);
				}
			}
			else {
				String errore="Email o password o ruoli errati";
				request.setAttribute("errore", errore);
				RequestDispatcher  d = getServletContext().getRequestDispatcher("/Tutti/Login.jsp");
				d.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}

}
