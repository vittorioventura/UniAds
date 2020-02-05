package it.unisa.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.model.Corriere;
import it.unisa.model.CorriereModel;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.GenericUser.Ruolo;
import it.unisa.model.UtenteModel;
import it.unisa.model.Utente;

/**
 * Servlet implementation class Registrazione
 */
@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		out.write("Error: GET method is used but POST method is required");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email= request.getParameter("emailRegistrazione");
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String indirizzo = request.getParameter("indirizzo");
		String ruolo = request.getParameter("ruolo");
		String agenzia = request.getParameter("agenzia");
		HttpSession session = request.getSession();
		
		
		
		DriverManagerConnectionPool dmcp=(DriverManagerConnectionPool)getServletContext().getAttribute("DriverManager");
		if(ruolo.equals("Utente")) {
			
			Utente utente = new Utente();
			utente.setEmail(email);
			UtenteModel model = new UtenteModel(dmcp);
			String error = "";
			try {
			
				utente = model.doRetrieveByKey(utente);
				if(!(utente==null || utente.getEmail().equals(email))) {
					if(this.regex(nome)==false) {
						error+="Nome non valido";
						request.setAttribute("erroreNome", error);
						request.setAttribute("utente", utente);
						RequestDispatcher  d = getServletContext().getRequestDispatcher("/Tutti/Registrazione.jsp");
						d.forward(request, response);
						
					}
				
					else if(this.regex(cognome)==false) {
							error+="Cognome non valido";
							request.setAttribute("erroreCognome", error);
							request.setAttribute("utente", utente);
							RequestDispatcher  d = getServletContext().getRequestDispatcher("/Tutti/Registrazione.jsp");
							d.forward(request, response);
							
						}
					else if(this.regexPassword(password)==false) {
						error+="Password non valida";
						request.setAttribute("errorePassword", error);
						request.setAttribute("utente", utente);
						RequestDispatcher  d = getServletContext().getRequestDispatcher("/Tutti/Registrazione.jsp");
						d.forward(request, response);
						
					}
					
					
					
					else {
					utente.setEmail(email);
					utente.setNome(nome);
					utente.setCognome(cognome);
					utente.setIndirizzo(indirizzo);
					utente.setPassword(password);
					utente.setRuolo(Ruolo.UTENTE);
					model.doSave(utente);
					session.setAttribute("login", true);		
					session.setAttribute("ruolo", "UTENTE");
					session.setAttribute("utente", utente);
					RequestDispatcher  d = getServletContext().getRequestDispatcher("/Tutti/HomePage.jsp");
					d.forward(request, response);
					}

				}
				else {
					error+="E-mail non valida";
					request.setAttribute("erroreEmail", error);
//					request.setAttribute("nome", nome);
//					request.setAttribute("cognome", cognome);
//					request.setAttribute("indirizzo", indirizzo);
//					request.setAttribute("password", password);
//					request.setAttribute("ruolo", ruolo);
					request.setAttribute("utente", utente);
					RequestDispatcher  d = getServletContext().getRequestDispatcher("/Tutti/Registrazione.jsp");
					d.forward(request, response);
				}
				
		} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			Utente utente = new Utente();
			
			Corriere corriere = new Corriere();
			corriere.setEmail(email);
			corriere.setNomeAgenzia(agenzia);
			corriere.setNome(nome);
			corriere.setPassword(password);
			corriere.setRuolo(Ruolo.CORRIERE);
			UtenteModel modelUtente = new UtenteModel(dmcp);
			CorriereModel modelCorriere = new CorriereModel(dmcp);
			String error = "";
			try {
				utente.setEmail(email);
				utente = modelUtente.doRetrieveByKey(utente);
				corriere = modelCorriere.doRetrieveByKey(corriere);
				if(!(utente==null || utente.getEmail().equals(email))) {
					utente.setEmail(email);
					utente.setNome(nome);
					utente.setCognome(cognome);
					utente.setIndirizzo(indirizzo);
					utente.setPassword(password); 
					utente.setRuolo(Ruolo.CORRIERE);
				
					corriere.setEmail(email);
					corriere.setNomeAgenzia(agenzia);
					corriere.setPassword(password);
					corriere.setRuolo(Ruolo.CORRIERE);
					corriere.setNome(nome);
					
					modelUtente.doSave(utente);
					modelCorriere.doSave(corriere);
					session.setAttribute("login", true);		
					session.setAttribute("utente", corriere);
					session.setAttribute("ruolo", "CORRIERE");
					RequestDispatcher  d = getServletContext().getRequestDispatcher("/Tutti/HomePage.jsp");
					d.forward(request, response);
				}
				else {
					error+="E-mail non valida";
					request.setAttribute("errore", error);
//					request.setAttribute("nome", nome);
//					request.setAttribute("cognome", cognome);
//					request.setAttribute("indirizzo", indirizzo);
//					request.setAttribute("password", password);
//					request.setAttribute("ruolo", ruolo);
					request.setAttribute("utente", corriere);
					RequestDispatcher  d = getServletContext().getRequestDispatcher("/Registrazione.jsp");
					d.forward(request, response);
				}
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		}

	}


	private boolean regex(String testo) {
		Pattern pattern= Pattern.compile("^[A-Za-z]+$");
		Matcher matcher = pattern.matcher(testo);

		return matcher.find();
	}
	
	private boolean regexPassword(String testo) {
		Pattern pattern= Pattern.compile("^\\S*$");
		Matcher matcher = pattern.matcher(testo);

		return matcher.find();
	}
}
