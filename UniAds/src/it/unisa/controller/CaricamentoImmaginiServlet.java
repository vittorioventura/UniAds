package it.unisa.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.unisa.model.Annuncio;
import it.unisa.model.AnnuncioModel;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.Immagine;
import it.unisa.model.ImmagineModel;
import it.unisa.model.Utente;

@WebServlet(name = "/CaricamentoImmaginiServlet", 
urlPatterns = { "/CaricamentoImmaginiServlet" }, 
initParams = {@WebInitParam(name = "file-upload", value = "tmpDir") })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be 
										  // temporarily stored on disk
	 maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
	 maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files

public class CaricamentoImmaginiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private String extractFileName(Part part) {
		//content-disposition: form-data; name="file"; filename="file.txt"
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		out.write("Error: GET method is used but POST method is required");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Part> parts = (ArrayList<Part>) request.getParts();
		String email = request.getParameter("email");
		String titoloAnnuncio = request.getParameter("titoloAnnuncio");		
		Utente utente = new Utente();
		utente.setEmail(email);
				
		Annuncio annuncio = new Annuncio();
		DriverManagerConnectionPool dmcp=(DriverManagerConnectionPool)getServletContext().getAttribute("DriverManager");
		AnnuncioModel modelAnnuncio = new AnnuncioModel(dmcp);
		annuncio.setUtente(utente);
		annuncio.setTitolo(titoloAnnuncio);
		
		try {
			while(annuncio==null)
				annuncio = modelAnnuncio.doRetrieveByKey(annuncio);
			System.out.println(annuncio.getUtente().getEmail());
			for(Part part : parts) {
				String fileName = extractFileName(part);
				InputStream stream = part.getInputStream();
				Immagine immagine = new Immagine();
				if(annuncio!=null && !fileName.equals("")) {
					immagine.setAnnuncio(annuncio);
					immagine.setNomeImmagine(""+fileName);
					immagine.setImg(stream.readAllBytes());
					ImmagineModel modelImmagine = new ImmagineModel(dmcp);
					modelImmagine.doSave(immagine);
				} 
			}
			response.sendRedirect("/UniAds/Tutti/HomePage.jsp");
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}
	