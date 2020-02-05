<%@page import="it.unisa.model.Preferiti"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.model.Annuncio"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
    
		<title>Visualizza Annunci</title>
		<link type="text/css" rel="stylesheet" href="/UniAds/css/simplePagination.css"/>
		<link rel="stylesheet" href="/UniAds/css/style.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
  		

	</head>
	
	<body onload="mostraCategorie();mostraUniversita();paginazione(0,0 ,0,0)">	
	
		<%@include file="Header.jsp" %>
		<%@include file="BarraNavigazione.jsp" %>
		
		
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
			Object objNumAnnunci =  request.getAttribute("numeroAnnunci");	
			Object objNumAnnunciPreferiti =  request.getAttribute("numeroAnnunciPreferiti");	
			
			if(errore==null && objNumAnnunci!=null){				
		%>
					<input type="hidden" value="0" id="selettorePagine" name="selettorePagine">
		<%
				int numeroAnnunciPreferiti=0;
				int numeroAnnunci = (Integer) request.getAttribute("numeroAnnunci");
				ArrayList<Annuncio> annunci = (ArrayList<Annuncio>) request.getAttribute("annunci");
				String annunciJson = (String) request.getAttribute("annunciJson");
				ArrayList<Preferiti> annunciPreferiti = (ArrayList<Preferiti>) request.getAttribute("annunciPreferiti");
				String annunciJsonPreferiti = (String) request.getAttribute("annunciJsonPreferiti");
				System.out.println(annunciJsonPreferiti);
				System.out.println(annunciJson);
				if(annunci!=null && annunci.size()>0 && numeroAnnunci>0){
		%>
		
		
		
		<div class="containerAds">
			<ul class="listAds">
					<li id="ricerca">Annunci trovati</li>
					<%
					for(int i = 1; i <= 5; i++) {
						if(annunci.size()>i-1 && annunci.get(i-1)!=null){
							String gtitolo = new Gson().toJson(annunci.get(i-1).getTitolo());
							String gEmail = new Gson().toJson(annunci.get(i-1).getUtente().getEmail());
					%> 
							<li class="everyAds" id="div<%=i%>">
								<img class="adImage" onerror="this.onerror=null; this.src='/UniAds/img/error.png'" src="/UniAds/PrelevaImmaginiServlet?email=<%=annunci.get(i-1).getUtente().getEmail()%>&titolo=<%=annunci.get(i-1).getTitolo()%>">
		     						<div class="adBody">
		     						<span >
		     						<span id="span<%=i%>"></span>
		     						<%
		     							
		     						if(objNumAnnunciPreferiti!=null){
		     							numeroAnnunciPreferiti = (Integer) objNumAnnunciPreferiti;
		     							Boolean tipo = false;
		     							for(int j = 0; j<annunciPreferiti.size();j++){
		     								if(annunciPreferiti.get(j).getEmailUtenteAnnuncio().equals(annunci.get(i-1).getUtente().getEmail()) && annunciPreferiti.get(j).getTitoloAnnuncio().equals(annunci.get(i-1).getTitolo())){
		     									tipo= true;
		     									break;
		     								}
		     							}
		     						
		     							if(tipo.equals(false) && isLog.equals(true)){
		     					%>	
		     								<img id="img<%=i%>" onclick='aggiungiPreferiti(<%=gEmailUtente%>,<%=gEmail%>,<%=gtitolo%>,<%=tipo%>,<%=i %>)' class="preferitiIcon" src="/UniAds/img/heart.png">
		     					<%	
		     							}
		     							if(tipo.equals(true) && isLog.equals(true)){
		     					%>
		     								<img id="img<%=i%>" onclick='aggiungiPreferiti(<%=gEmailUtente%>,<%=gEmail%>,<%=gtitolo%>,<%=tipo%>,<%=i%>)' class="preferitiIcon" src="/UniAds/img/heartHover.png">
		     					<%
		     							}
		     						}
		     					%>	
		     					
										<a onclick='selezionaAnnuncio(<%=gtitolo%>,<%=gEmail%>)'>
		     							<span class="titoloAds"> 
		     								<%=annunci.get(i-1).getTitolo()%> <br>
		     							</span>
		     							<span class="descrizioneAds"><%=annunci.get(i-1).getDescrizione()%></span>
		     						</a>
		     						</span>
		     						</div>
		     				</li>
							<%}%>        		
   					<%} %>
   					</ul>
   					<div class="pageButton">
     					<%for(int i=0; i < numeroAnnunci;i=i+5) {%>	  
    	 		 			<a class="active" id="bottone<%=i%>"  onclick='paginazione(<%=i/5+1%>,<%=annunciJson%>,<%=i%>,<%=numeroAnnunci%>,<%=annunciJsonPreferiti%>,<%=numeroAnnunciPreferiti%>,<%=gEmailUtente%>)'><%=i/5+1 %></a>
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
      			
		<%}
		else{
		%>
			<div class="containerAds">
				<div id="notFound"><img src="/UniAds/img/adsNotFound.png"></div>
			</div>
		<%
		}	
		%>
		
				
		<%@include file="Footer.jsp" %>
			
		<script src="/UniAds/js/jquery.js"></script>
		<script src="/UniAds/js/funzioni.js"></script>	
	</body>
	
	
</html>