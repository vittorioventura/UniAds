package it.unisa.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet Filter implementation class FiltroLogin
 */
@WebFilter(urlPatterns = {"/Admin/*","/User/*","/Corriere/*"})
public class FiltroLogin implements Filter {
    public FiltroLogin() {
        // TODO Auto-generated constructor stub
    }

    public void destroy() {
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
	
		String uri = hrequest.getRequestURI();
		
		if(uri.contains("/User/")) {
			HttpSession session = hrequest.getSession(false);
			if(session!=null) {
				String ruolo = (String) session.getAttribute("ruolo"); 
				if(ruolo!=null && (ruolo.equals("AMMINISTRATORE") ||ruolo.equals("UTENTE"))) {
					chain.doFilter(request, response);
				}
				else {
					hresponse.sendRedirect("/UniAds/Tutti/Login.jsp");
				}
			}
			else {
				hresponse.sendRedirect("/UniAds/Tutti/Login.jsp");
			}
		}
		else if(uri.contains("/Admin/")) {
			HttpSession session = hrequest.getSession(false);
			if(session!=null) {
				String ruolo = (String) session.getAttribute("ruolo"); 
				if(ruolo!=null && (ruolo.equals("AMMINISTRATORE"))) {
					chain.doFilter(request, response);
				}
				else {
					hresponse.sendRedirect("/UniAds/Tutti/Login.jsp");
				}
			}
			else {
				hresponse.sendRedirect("/UniAds/Tutti/Login.jsp");
			}	
		}
		else if(uri.contains("/Corriere/")) {
			HttpSession session = hrequest.getSession(false);
			if(session!=null) {
				String ruolo = (String) session.getAttribute("ruolo"); 
				System.out.println(ruolo);
				if(ruolo!=null && (ruolo.equals("CORRIERE"))) {
					chain.doFilter(request, response);
				}
				else {
					hresponse.sendRedirect("/UniAds/Tutti/Login.jsp");
				}
			}
			else {
				hresponse.sendRedirect("/UniAds/Tutti/Login.jsp");
			}	
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	} 

}
