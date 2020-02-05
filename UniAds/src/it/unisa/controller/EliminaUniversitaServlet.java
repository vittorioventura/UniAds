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
import it.unisa.model.Universita;
import it.unisa.model.UniversitaModel;

/**
 * Servlet implementation class EliminaUniversitaServlet
 */
@WebServlet("/Admin/EliminaUniversitaServlet")
public class EliminaUniversitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String siglaUniversita = request.getParameter("universita");
     	DriverManagerConnectionPool dmcp = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
    	UniversitaModel modelUniversita = new UniversitaModel(dmcp);
    	Universita universita = new Universita();
    	universita.setSigla(siglaUniversita);
    	try {
    		universita=modelUniversita.doRetrieveByKey(universita);
    		System.out.println(universita.getSigla());
    		if(universita.getSigla().equals(siglaUniversita)){
			
				modelUniversita.doDelete(universita);
				request.setAttribute("completamentoEliminazioneUniversita","Universita eliminata");
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Admin/OperazioniAdmin.jsp");
				d.forward(request, response);
			}
			else {
				request.setAttribute("erroreEliminazioneUniversita","Impossibile cancellare universita");
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Admin/OperazioniAdmin.jsp");
				d.forward(request, response);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
