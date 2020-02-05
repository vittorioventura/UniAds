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
import it.unisa.model.Regione;
import it.unisa.model.RegioneModel;
import it.unisa.model.Universita;
import it.unisa.model.UniversitaModel;

/**
 * Servlet implementation class InserimentoUniversitaServlet
 */
@WebServlet("/Admin/InserimentoUniversitaServlet")
public class InserimentoUniversitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		String siglaUniversita = request.getParameter("siglaUniversita");
    	String localita = request.getParameter("nomeLocalita");
   	
    	DriverManagerConnectionPool dmcp = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
   		UniversitaModel modelUniversita = new UniversitaModel(dmcp);
   		Universita tempUniversita = new Universita();
   		Universita universita = new Universita();
   		tempUniversita.setLocalita(localita);
   		tempUniversita.setSigla(siglaUniversita);
   		
   		RegioneModel modelRegione = new RegioneModel(dmcp);
   		Regione tempRegione = new Regione();
   		Regione regione = new Regione();
   		tempRegione.setNome(localita);
   		try {
			if((regione=modelRegione.doRetrieveByKey(tempRegione))==null  || !regione.getNome().equals(localita)) {
				request.setAttribute("erroreRegione", "Regione non valida");
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Admin/OperazioniAdmin.jsp");
				d.forward(request, response);
				return;
				
			}
			else {
				
				if(((universita=modelUniversita.doRetrieveByKey(tempUniversita))==null) || (universita.getSigla().equals(""))) {
					universita.setLocalita(localita);
					universita.setSigla(siglaUniversita);
					modelUniversita.doSave(universita);
					request.getSession().setAttribute("completamentoInserimentoUniversita","Univerista inserita");
					response.sendRedirect("/UniAds/Admin/OperazioniAdmin.jsp");
				}
				else {
					request.setAttribute("erroreSigla", "Sigla esistente");
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
