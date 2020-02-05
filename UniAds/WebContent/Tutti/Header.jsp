<%@page import="it.unisa.model.DriverManagerConnectionPool"%>
<%@page import="it.unisa.model.UtenteModel"%>
<%@page import="it.unisa.model.GenericUser"%>
<%@page import="it.unisa.model.Corriere"%>
<%@page import="it.unisa.model.Amministratore"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="it.unisa.model.Utente"%>
<header>
	<div id="logo"><a href="/UniAds/Tutti/HomePage.jsp" title="UniAds"><img src="/UniAds/img/logo.png"></a></div>
		<nav>  
			<%
				Amministratore amministratore = new Amministratore();
				Utente utente = new Utente();
				Corriere corriere = new Corriere();
				Boolean isLog = (Boolean) request.getSession().getAttribute("login");	
				Object object = request.getSession().getAttribute("utente");
				if(isLog!=null && isLog.equals(true) && object!=null && object instanceof Amministratore ){
					amministratore = (Amministratore) request.getSession().getAttribute("utente");
			%>	
			<ul>
				<li class="selectUtente">
				    <span class="prova">
				    <select onchange="selectOperazioni(value,'<%=amministratore.getEmail()%>')">
				    	<option><%=amministratore.getNome()%></option>
				    	<option value="1" >Gestione Sito</option>
				    	<option value="2">I miei annunci</option>
				        <option value="3">Preferiti</option> 
				    </select>
				    <span class="select_arrow"></span>
				    </span>
				</li>
    			<li><a href="/UniAds/User/InserimentoAnnuncio.jsp"><nobr>Crea Annuncio</nobr></a></li>
				<li><a href="/UniAds/Logout">Esci</a></li>
				<!-- aggiungi il select dell utente -->
			</ul>
			<%}%>
			
			<%
				if(isLog!=null && isLog.equals(true) && object!=null && object instanceof Utente ){
					utente = (Utente) request.getSession().getAttribute("utente");			
			%>
			<ul>
				<li class="selectUtente">
				    <span class="prova">
				    <select onchange="selectOperazioni(value,'<%=utente.getEmail()%>')">
				        <option><%=utente.getNome()%></option>
				        <option value="5">I miei annunci</option>
				        <option value="6">Preferiti</option>
				     </select>
				    <span class="select_arrow"></span>
				    </span>
				</li>
    			<li><a href="/UniAds/User/InserimentoAnnuncio.jsp"><nobr>Crea Annuncio</nobr></a></li>
				<li><a href="/UniAds/Logout">Esci</a></li>
				<!-- aggiungi il select dell utente -->
			</ul>
			<%
				}
			%>
			<%
				if(isLog!=null && isLog.equals(true) && object!=null && object instanceof Corriere ){
					corriere = (Corriere) request.getSession().getAttribute("utente");
			%>
			<ul>
				<li class="selectUtente">
				    <span class="prova">
				    <select onchange="selectOperazioni(value,'<%=corriere.getEmail()%>')">
				        <option><%=corriere.getNome()%></option>
				        <option value="9">Gestione consegna</option>
				    </select>
				    <span class="select_arrow"></span>
				    </span>
				</li>
    			<li><a href="/UniAds/User/InserimentoAnnuncio.jsp"><nobr>Crea Annuncio</nobr></a></li>
				<li><a href="/UniAds/Logout">Esci</a></li>
				<!-- aggiungi il select dell utente -->
			</ul>
			<%
				}
			%>
		
			<%	
				if(isLog==null || isLog.equals(false) || object==null){
			%>
			
			<ul>
				<li><a href="/UniAds/Tutti/Login.jsp">Accedi</a></li>
				<li><a href="/UniAds/Tutti/Registrazione.jsp">Registrati</a></li>
				<li><a href="/UniAds/Tutti/Login.jsp"><nobr>Crea Annuncio</nobr></a></li>
			</ul>
			<%}%>
				
		</nav>

</header>

