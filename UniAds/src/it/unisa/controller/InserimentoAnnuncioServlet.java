package it.unisa.controller;
//ciao
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.Annuncio;
import it.unisa.model.AnnuncioModel;
import it.unisa.model.Categoria;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.Utente;

/**
 * Servlet implementation class InserimentoAnnuncioServlet
 */
@WebServlet(name = "/User/InserimentoAnnuncioServlet", 
urlPatterns = { "/User/InserimentoAnnuncioServlet" }, 
initParams = {@WebInitParam(name = "file-upload", value = "tmpDir") })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be 
										  // temporarily stored on disk
	 maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
	 maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files

public class InserimentoAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		out.write("Error: GET method is used but POST method is required");
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String titoloAnnuncio = request.getParameter("titoloAnnuncio");
		String categoria = request.getParameter("categorie");
		String universita = request.getParameter("universita");
		String tipo = request.getParameter("tipo");
		String acquistoOnline = request.getParameter("acquistoOnline"); 
		String descrizione = request.getParameter("descrizione");
		int valutazione = Integer.parseInt(request.getParameter("valutazione"));
		
//		System.out.println(email);
//		System.out.println(valutazione);
//		System.out.println(titoloAnnuncio);
//		System.out.println(categoria);
//		System.out.println(universita);
//		System.out.println(tipo);
//		System.out.println(descrizione);
//		System.out.println(acquistoOnline);
	
		DriverManagerConnectionPool dmcp=(DriverManagerConnectionPool)getServletContext().getAttribute("DriverManager"); 
		AnnuncioModel modelAnnuncio = new AnnuncioModel(dmcp);
		Annuncio annuncio = new Annuncio();
		
		Categoria c = new Categoria();
		c.setNome(categoria);
		Utente utente = new Utente();
		utente.setEmail(email);
		
		annuncio.setUtente(utente);
		annuncio.setTitolo(titoloAnnuncio);
		if(acquistoOnline!=null && acquistoOnline.equals("acquistoOnline"))
			annuncio.setAcquistoOnline(acquistoOnline.equals("acquistoOnline"));
		else 
			annuncio.setAcquistoOnline(false);
		
		annuncio.setCategoria(c);
		annuncio.setSiglaUni(universita);
		annuncio.setDescrizione(descrizione);
		annuncio.setTipo(tipo);
		annuncio.setValutazione(valutazione);
		
		//Fai codice
		if(categoria.equals("0") || universita.equals("0")) {
			request.setAttribute("errore", "Impossibile aggiungere annuncio");
			RequestDispatcher d = getServletContext().getRequestDispatcher("/User/InserimentoAnnuncio.jsp");
			d.forward(request, response);
		}
		else {	
			try {
			
				if(modelAnnuncio.doRetrieveByKey(annuncio).getTitolo().equals("")) {
					modelAnnuncio.doSave(annuncio);
					RequestDispatcher d = getServletContext().getRequestDispatcher("/CaricamentoImmaginiServlet");
					d.forward(request, response);
				}
			} 
			catch (SQLException e) {
		
				e.printStackTrace();
			}
		}
	}

}
