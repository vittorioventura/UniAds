package it.unisa.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.Immagine;
import it.unisa.model.ImmagineModel;

@WebServlet("/PrelevaImmagine")
public class PrelevaImmagine extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("image/jpeg");
   		
		int numeroImg = Integer.parseInt(request.getParameter("numeroImg"));
		String email = request.getParameter("email");
		String titolo = request.getParameter("titolo");
		
		DriverManagerConnectionPool dmcp = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		ImmagineModel modelImmagine = new ImmagineModel(dmcp);
		
		try {
			ArrayList<Immagine> immagini = modelImmagine.doRetrieveImmagini(email, titolo);
			
			if(immagini.size()>0) {
				System.out.println(numeroImg);
				System.out.println(immagini.size());
				
				OutputStream streamOutput = response.getOutputStream();
				streamOutput.write(immagini.get(numeroImg%immagini.size()).getImg());
				streamOutput.flush();
				streamOutput.close();
			} 
		}


		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
