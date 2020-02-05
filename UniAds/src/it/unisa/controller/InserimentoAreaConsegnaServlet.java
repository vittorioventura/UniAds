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

@WebServlet("/Corriere/InserimentoAreaConsegnaServlet")
public class InserimentoAreaConsegnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		double prezzo = Double.parseDouble(request.getParameter("prezzo")); 
   		String regione = request.getParameter("regione");
   		String email = request.getParameter("email");
   		String agenzia = request.getParameter("agenzia");
   	   	DriverManagerConnectionPool dmcp = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
   		if(regione!=null) {
   			try {
   				AreaCompetenzaModel modelArea = new AreaCompetenzaModel(dmcp);
				AreaCompetenza area = new AreaCompetenza();
				area.setAgenzia(agenzia);
				area.setEmailCorriere(email);
				area.setPrezzo(prezzo);
				area.setNomeRegione(regione);
				area=modelArea.doRetrieveByKey(area);

				System.out.println(area.getNomeRegione());
				if(area.getNomeRegione()==null) {
					area.setAgenzia(agenzia);
					area.setEmailCorriere(email);
					area.setPrezzo(prezzo);
					area.setNomeRegione(regione);
					modelArea.doSave(area);
					request.setAttribute("completamentoInserimentoArea", "Area consegna inserita");
					RequestDispatcher d = getServletContext().getRequestDispatcher("/Corriere/OperazioniCorriere.jsp");
					d.forward(request, response);
				}
				else {
					request.setAttribute("erroreRegione", "Regione non presente o area già inserita");
					RequestDispatcher d = getServletContext().getRequestDispatcher("/Corriere/OperazioniCorriere.jsp");
					d.forward(request, response);
				}
			} 
   			catch (SQLException e) {
				e.printStackTrace();
			}
   		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
