package it.unisa.controller;
//ciao
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.Categoria;
import it.unisa.model.CategoriaModel;
import it.unisa.model.DriverManagerConnectionPool;

/**
 * Servlet implementation class EliminaCategoriaServlet
 */
@WebServlet("/Admin/EliminaCategoriaServlet")
public class EliminaCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EliminaCategoriaServlet() {
        super();
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String categoria = request.getParameter("categorie");
    	System.out.println(categoria);
    	if(!categoria.equals("0")) {
    		DriverManagerConnectionPool dmcp = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
    		CategoriaModel modelCategoria = new CategoriaModel(dmcp);
    		Categoria c = new Categoria();
    		c.setNome(categoria);
    		try {
				modelCategoria.doDelete(c);
				request.getSession().setAttribute("completamentoEliminazioneCategoria", "Categoria eliminata");
				response.sendRedirect("/UniAds/Admin/OperazioniAdmin.jsp");
    		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else {
    		request.setAttribute("erroreEliminazioneCategoria", "Impossibile cancellare categoria");
    		RequestDispatcher d = getServletContext().getRequestDispatcher("/Admin/OperazioniAdmin.jsp");
    		d.forward(request, response);
    	}
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
