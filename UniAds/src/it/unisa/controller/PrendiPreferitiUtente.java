package it.unisa.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.model.Annuncio;
import it.unisa.model.AnnuncioModel;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.Preferiti;
import it.unisa.model.PreferitiModel;

/**
 * Servlet implementation class PrendiPreferitiUtente
 */
@WebServlet("/User/PrendiPreferitiUtente")
public class PrendiPreferitiUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailUtente = request.getParameter("email");
		DriverManagerConnectionPool dmcp = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		PreferitiModel modelPreferiti = new PreferitiModel(dmcp);
		AnnuncioModel modelAnnuncio = new AnnuncioModel(dmcp);
		if(emailUtente!=null && !emailUtente.equals("") && !emailUtente.equals("null")) {
			try {
				ArrayList<Preferiti> preferiti=modelPreferiti.doRetrieveAll("titolo_annuncio");
				ArrayList<Preferiti> p= new ArrayList<Preferiti>();
				
				ArrayList<Annuncio> annunciPreferiti = new ArrayList<Annuncio>(); 
				ArrayList<Annuncio> annunci = modelAnnuncio.doRetrieveAll("titolo");
				System.out.println(preferiti.size());
				
				for(int j = 0 ; j< preferiti.size();j++) {
					if(preferiti.get(j).getEmailUtente().equals(emailUtente)) {
						p.add(preferiti.get(j));
					}
				}
				
				for(int i = 0; i<p.size();i++) {
					for(int j=0; j < annunci.size(); j++) {
						if(p.get(i).getEmailUtenteAnnuncio().equals(annunci.get(j).getUtente().getEmail())&& p.get(i).getTitoloAnnuncio().equals(annunci.get(j).getTitolo())) {
							annunciPreferiti.add(annunci.get(j));
							
						}
					}
				}
				System.out.println("preferiti:"+preferiti.size()+"array:"+annunciPreferiti.size());
				request.setAttribute("numeroAnnunciPreferiti", annunciPreferiti.size());
				request.setAttribute("annunciPreferiti", annunciPreferiti);
				request.setAttribute("annunciJsonPreferiti", new Gson().toJson(annunciPreferiti));
				
				RequestDispatcher d = getServletContext().getRequestDispatcher("/User/Preferiti.jsp");
				d.forward(request, response);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
		else {
			RequestDispatcher d = getServletContext().getRequestDispatcher("/User/Preferiti.jsp");
			d.forward(request, response);
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
