<%@page import="it.unisa.model.Preferiti"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="it.unisa.model.Annuncio"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Preferiti-UniAds</title>
		<link type="text/css" rel="stylesheet" href="/UniAds/css/simplePagination.css"/>
		<link rel="stylesheet" href="/UniAds/css/style.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
  		
	</head>
<body onload="mostraCategorie();mostraUniversita();paginazionePreferiti(0,0 ,0,0)">	
	
		<%@include file="/Tutti/Header.jsp" %>
		<%@include file="/Tutti/BarraNavigazione.jsp" %>
		
		
		<%
		String gEmailUtente="";
		if(isLog!=null && isLog.equals(true) && object!=null && object instanceof Amministratore ){
			amministratore = (Amministratore) request.getSession().getAttribute("utente");
			gEmailUtente = new Gson().toJson(amministratore.getEmail());
		}
		if(isLog!=null && isLog.equals(true) && object!=null && object instanceof Utente ){
			utente = (Utente) request.getSession().getAttribute("utente");
			gEmailUtente = new Gson().toJson(utente.getEmail());
		}	
		if(isLog!=null && isLog.equals(true) && object!=null && object instanceof Corriere ){
			corriere = (Corriere) request.getSession().getAttribute("utente");
			gEmailUtente = new Gson().toJson(corriere.getEmail());
			
		}	
			String errore = (String)request.getAttribute("erroreRicerca");
			Object objNumAnnunciPreferiti =  request.getAttribute("numeroAnnunciPreferiti");	
			if(errore==null && objNumAnnunciPreferiti!=null){				
				
		%>
					<input type="hidden" value="0" id="selettorePagine" name="selettorePagine">
		<%
				int numeroAnnunciPreferiti = (Integer) request.getAttribute("numeroAnnunciPreferiti");
				ArrayList<Annuncio> annunciPreferiti = (ArrayList<Annuncio>) request.getAttribute("annunciPreferiti");
				String annunciJsonPreferiti = (String) request.getAttribute("annunciJsonPreferiti");
				System.out.println(annunciJsonPreferiti);
				if(annunciPreferiti.size()>0){
		%>
		
		
		
		<div class="containerAds">
			<ul class="listAds">
					<li id="ricerca">Annunci trovati</li>
					<%
					for(int i = 1; i <= 5; i++) {
						if(annunciPreferiti.size()>i-1 && annunciPreferiti.get(i-1)!=null){
							String gtitolo = new Gson().toJson(annunciPreferiti.get(i-1).getTitolo());
							String gEmail = new Gson().toJson(annunciPreferiti.get(i-1).getUtente().getEmail());
					%> 
							<li class="everyAds" id="div<%=i%>">
								<img class="adImage" onerror="this.onerror=null; this.src='/UniAds/img/error.png'" src="/UniAds/PrelevaImmaginiServlet?email=<%=annunciPreferiti.get(i-1).getUtente().getEmail()%>&titolo=<%=annunciPreferiti.get(i-1).getTitolo()%>">
		     						<div class="adBody">
		     						<span>
		     						<%Boolean tipoPreferti=true; %>
		     							<img onclick='aggiungiPreferitiLista(<%=gEmailUtente%>,<%=gEmail%>,<%=gtitolo%>,<%=tipoPreferti%>)' class="preferitiIcon" src="/UniAds/img/heartHover.png">
		     						
										<a onclick='selezionaAnnuncio(<%=gtitolo%>,<%=gEmail%>)'>
		     							<span class="titoloAds"> 
		     								<%=annunciPreferiti.get(i-1).getTitolo()%> <br>
		     							</span>
		     							<span class="descrizioneAds"><%=annunciPreferiti.get(i-1).getDescrizione()%></span>
		     						</a>
		     							</span>
		     						</div>
		     				</li>
							<%}%>        		
   					<%} %>
   					</ul>
   					<div class="pageButton">
     					<%for(int i=0; i < numeroAnnunciPreferiti;i=i+5) {%>	  
    	 		 			<a class="active" id="bottone<%=i%>"  onclick='paginazionePreferiti(<%=i/5+1%>,<%=annunciJsonPreferiti%>,<%=i%>,<%=numeroAnnunciPreferiti%>,<%=annunciJsonPreferiti%>,<%=numeroAnnunciPreferiti%>,<%=gEmailUtente%>)'><%=i/5+1 %></a>
    	 				<%} %>
    	 			</div>
     			</div>
       
     	
     
		<% 		}
      
				else{
		%>
				<div class="containerAds">
					<div id="notFound"><img src="/UniAds/img/adsNotFound.png"></div>
				</div>
		<%
				}
		%>
      			
		<%}%>
				
		<%@include file="/Tutti/Footer.jsp" %>
			
		<script src="/UniAds/js/jquery.js"></script>
		<script src="/UniAds/js/funzioni.js"></script>	
	</body>

	

</html>