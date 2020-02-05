package it.unisa.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.AreaCompetenza;
import it.unisa.model.AreaCompetenzaModel;
import it.unisa.model.DriverManagerConnectionPool;

/**
 * Servlet implementation class EliminazioneAreaConsegnaServlet
 */
@WebServlet("/Corriere/EliminazioneAreaConsegnaServlet")
public class EliminazioneAreaConsegnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regione = request.getParameter("regione");
   		String email = request.getParameter("email");
   		String agenzia = request.getParameter("agenzia");
   	   	DriverManagerConnectionPool dmcp = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
   		if(regione!=null) {
   			AreaCompetenzaModel modelArea = new AreaCompetenzaModel(dmcp);
			AreaCompetenza area = new AreaCompetenza();
			area.setAgenzia(agenzia);
			area.setEmailCorriere(email);
			area.setNomeRegione(regione);;
			try {
				area= modelArea.doRetrieveByKey(area);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
   			try {
				if(area != null && area.getNomeRegione()!=null && area.getNomeRegione().equals(regione) ) {
				
					modelArea.doDelete(area);
					request.setAttribute("completamentoEliminazioneArea", "Area consegna eliminata");
					RequestDispatcher d = getServletContext().getRequestDispatcher("/OperazioniCorriere.jsp");
					d.forward(request, response);

				}
				else {
					request.setAttribute("erroreRegioneEliminazione", "Regione non presente");
					RequestDispatcher d = getServletContext().getRequestDispatcher("/OperazioniCorriere.jsp");
					d.forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   			
   		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
