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

/**
 * Servlet implementation class PrelevaImmaginiServlet
 */
@WebServlet("/PrelevaImmaginiServlet")
public class PrelevaImmaginiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		response.setContentType("image/png");
   		String email = request.getParameter("email");
   		String titolo = request.getParameter("titolo");
   		OutputStream streamOutput = response.getOutputStream();
   		DriverManagerConnectionPool dmcp = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
   		ImmagineModel modelImmagini = new ImmagineModel(dmcp);
   		
   		System.out.println("Ci sono");
   		
   		if(email!=null && titolo!=null) {
   			try {
				ArrayList<Immagine> immagini = modelImmagini.doRetrieveImmagini(email,titolo);
				if(immagini.size()>0) {
					
					streamOutput.write(immagini.get(0).getImg());
					streamOutput.flush();
					streamOutput.close();
				} 
			}
   			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
