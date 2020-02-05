package it.unisa.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.model.Annuncio;
import it.unisa.model.AnnuncioModel;
import it.unisa.model.Categoria;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.Universita;
import it.unisa.model.UniversitaModel;

/**
 * Servlet implementation class PrelevaAnnunciServlet
 */
@WebServlet("/Tutti/PrelevaAnnunciServlet")
public class PrelevaAnnunciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String siglaUni = request.getParameter("universita");
		String categoria = request.getParameter("categorie");
		String titolo = request.getParameter("search");
		String regione = request.getParameter("regione");
		String tutti =  request.getParameter("tutti");
		String email =  request.getParameter("email");
		System.out.println(siglaUni);
		System.out.println(categoria);
		System.out.println(titolo);
		
		Categoria c = new Categoria();
		DriverManagerConnectionPool dmcp = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		AnnuncioModel modelAnnuncio = new AnnuncioModel(dmcp);
		if(tutti!=null) {
			try {
				ArrayList<Annuncio> annunci=modelAnnuncio.doRetrieveAll("titolo");
				ArrayList<Annuncio> annunciEffettivi = new ArrayList<Annuncio>(); 
				for(Annuncio a: annunci) {
					if(tutti.equals(a.getUtente().getEmail())) {
						annunciEffettivi.add(a);
					}
				}
				request.setAttribute("numeroAnnunciEffettivi", annunciEffettivi.size());
				request.setAttribute("numeroAnnunci", annunci.size());
				request.setAttribute("annunci", annunci);
				request.setAttribute("annunciEffettivi", annunciEffettivi);
				request.setAttribute("annunciJsonEffettivi", new Gson().toJson(annunciEffettivi));
				
				request.setAttribute("annunciJson", new Gson().toJson(annunci));
				RequestDispatcher d = getServletContext().getRequestDispatcher("/User/AnnunciCreati.jsp");
				d.forward(request, response);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
		else if(siglaUni!=null && categoria != null && titolo!=null && !siglaUni.equals("0") && !categoria.equals("0") && !titolo.equals("")) {
			try {
				ArrayList<Annuncio> annunci=modelAnnuncio.doRetrieveAll("titolo");
				ArrayList<Annuncio> annunciView = new ArrayList<Annuncio>();
				c.setNome(categoria);
				for(Annuncio a : annunci) {
					if(this.regex(siglaUni,a.getSiglaUni()) && this.regex(categoria,a.getCategoria().getNome()) && this.regex(titolo,a.getTitolo())) {
						annunciView.add(a);
					}
				}
				request.setAttribute("numeroAnnunci", annunciView.size());
				request.setAttribute("annunci", annunciView);
				request.setAttribute("annunciJson", new Gson().toJson(annunciView));
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Tutti/PrendiPreferiti?email="+email);
				d.forward(request, response);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(siglaUni!=null && categoria != null && !siglaUni.equals("0") && !categoria.equals("0")) {
			try {
				ArrayList<Annuncio> annunci=modelAnnuncio.doRetrieveAll("titolo");
				ArrayList<Annuncio> annunciView = new ArrayList<Annuncio>();
				c.setNome(categoria);
				for(Annuncio a : annunci) {
					if(this.regex(siglaUni, a.getSiglaUni()) && this.regex(categoria, a.getCategoria().getNome())) {
						annunciView.add(a);
					}
				}
				request.setAttribute("numeroAnnunci", annunciView.size());
				request.setAttribute("annunciJson", new Gson().toJson(annunciView));
				request.setAttribute("annunci", annunciView);
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Tutti/PrendiPreferiti?email="+email);
				d.forward(request, response);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		else if(siglaUni!=null && titolo!=null && !siglaUni.equals("0") && !titolo.equals("")) {
			try {
				ArrayList<Annuncio> annunci=modelAnnuncio.doRetrieveAll("titolo");
				ArrayList<Annuncio> annunciView = new ArrayList<Annuncio>();
				
				for(Annuncio a : annunci) {
					if(this.regex(siglaUni, a.getSiglaUni()) && this.regex(titolo, a.getTitolo())) {
						annunciView.add(a);
					}
				}
				request.setAttribute("numeroAnnunci", annunciView.size());
				request.setAttribute("annunciJson", new Gson().toJson(annunciView));
				request.setAttribute("annunci", annunciView);
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Tutti/PrendiPreferiti?email="+email);
				d.forward(request, response);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		else if(categoria != null && titolo!=null && !categoria.equals("0") && !titolo.equals("")) {
			try {
				ArrayList<Annuncio> annunci=modelAnnuncio.doRetrieveAll("titolo");
				ArrayList<Annuncio> annunciView = new ArrayList<Annuncio>();
				c.setNome(categoria);
				for(Annuncio a : annunci) {
					if(this.regex(titolo, a.getTitolo()) && this.regex(categoria, a.getCategoria().getNome())) {
						annunciView.add(a);
					}
				}

				request.setAttribute("annunciJson", new Gson().toJson(annunciView));
				request.setAttribute("numeroAnnunci", annunciView.size());
				request.setAttribute("annunci", annunciView);
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Tutti/PrendiPreferiti?email="+email);
				d.forward(request, response);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		else if(siglaUni!=null && !siglaUni.equals("0")) {
			try {
				ArrayList<Annuncio> annunci=modelAnnuncio.doRetrieveAll("titolo");
				ArrayList<Annuncio> annunciView = new ArrayList<Annuncio>();
				for(Annuncio a : annunci) {
					if(this.regex(siglaUni, a.getSiglaUni())) {
						annunciView.add(a);
					}
				}

				request.setAttribute("annunciJson", new Gson().toJson(annunciView));
				request.setAttribute("numeroAnnunci", annunciView.size());
				request.setAttribute("annunci", annunciView);
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Tutti/PrendiPreferiti?email="+email);
				d.forward(request, response);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		else if(categoria != null && !categoria.equals("0")) {
			try {
				ArrayList<Annuncio> annunci=modelAnnuncio.doRetrieveAll("titolo");
				ArrayList<Annuncio> annunciView = new ArrayList<Annuncio>();
				c.setNome(categoria);
				for(Annuncio a : annunci) {
					if(this.regex(categoria, a.getCategoria().getNome())) {
						annunciView.add(a);
					}
				}

				request.setAttribute("annunciJson", new Gson().toJson(annunciView));
				request.setAttribute("numeroAnnunci", annunciView.size());
				request.setAttribute("annunci", annunciView);
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Tutti/PrendiPreferiti?email="+email);
				d.forward(request, response);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		else if(titolo!=null && !titolo.equals("") && !titolo.equals("0")) {
			try {
				ArrayList<Annuncio> annunci=modelAnnuncio.doRetrieveAll("titolo");
				ArrayList<Annuncio> annunciView = new ArrayList<Annuncio>();
			
				for(Annuncio a : annunci) {
					if(this.regex(titolo, a.getTitolo())) {
						annunciView.add(a);
					}
				}
				request.setAttribute("numeroAnnunci", annunciView.size());

				request.setAttribute("annunciJson", new Gson().toJson(annunciView));
				request.setAttribute("annunci", annunciView);
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Tutti/PrendiPreferiti?email="+email);
				d.forward(request, response);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		else if(regione!=null) {
			try {
				ArrayList<Annuncio> annunci=modelAnnuncio.doRetrieveAll("titolo");
				ArrayList<Annuncio> annunciView = new ArrayList<Annuncio>();
				UniversitaModel modelUniversita = new UniversitaModel(dmcp);
				
				ArrayList<Universita> universita = modelUniversita.doRetrieveAll("localita");
				ArrayList<Universita> uni = new ArrayList<Universita>();
				
				for(Universita u: universita) {
					if(this.regex(regione, u.getLocalita())){
						uni.add(u);
					}
				}
				
				for(int i = 0 ; i < annunci.size();i++) {
					for(int j=0;j<uni.size();j++)
						if(this.regex(uni.get(j).getSigla(), annunci.get(i).getSiglaUni())) {
							annunciView.add(annunci.get(i));
						}
				}
				request.setAttribute("numeroAnnunci", annunciView.size());
				
				request.setAttribute("annunciJson", new Gson().toJson(annunciView));
				request.setAttribute("annunci", annunciView);
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Tutti/PrendiPreferiti?email="+email);
				d.forward(request, response);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 

		}
		else if(siglaUni!=null && categoria != null && titolo!=null && siglaUni.equals("0") && categoria.equals("0") && titolo.equals("0")) {
			try {
				ArrayList<Annuncio> annunci=modelAnnuncio.doRetrieveAll("titolo");
				request.setAttribute("numeroAnnunci", annunci.size());
				request.setAttribute("annunci", annunci);
				request.setAttribute("annunciJson", new Gson().toJson(annunci));
				RequestDispatcher d = getServletContext().getRequestDispatcher("/Tutti/PrendiPreferiti?email="+email);
				d.forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		else {
			ArrayList<Annuncio> annunci = new ArrayList<Annuncio>();
			request.setAttribute("erroreRicerca", "Annunci non trovati");
			request.setAttribute("numeroAnnunci", 0);
			request.setAttribute("annunci", annunci);
			
			RequestDispatcher d = getServletContext().getRequestDispatcher("/Tutti/PrendiPreferiti?email="+email);
			d.forward(request, response);

		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	private boolean regex(String testoDaCercare,String testoSuCuiCercare) {
		Pattern pattern= Pattern.compile("\\.*"+testoDaCercare+"\\.*");
		Matcher matcher = pattern.matcher(testoSuCuiCercare);

		return matcher.find();
	}
}
