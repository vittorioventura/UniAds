<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.model.Annuncio"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
	Annuncio annuncio = (Annuncio) request.getAttribute("Annuncio");
%>
		
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>Annuncio <%=annuncio.getTitolo()%></title> 
		<link type="text/css" rel="stylesheet" href="/UniAds/css/simplePagination.css"/>
		<link rel="stylesheet" href="/UniAds/css/style.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
		<link rel="stylesheet" href="/UniAds/css/Annuncio.css">
	
  	</head>
	
	<body onload=mostraCategorie();mostraUniversita()>	
		<%@include file="Header.jsp" %>
		<%@include file="BarraNavigazione.jsp" %>
		<!-- TITOLO-IMMAGINI (PrelevaImmagini, arraylist)- DESCRIZIONE - BOTTONEACQUISTA (SE C'è) - BOTTONECONTATTA -->	
		<div class="containerAnnuncio">
			<div id="TitoloAnnuncio"> <%= annuncio.getTitolo() %> </div>
			<div id="fakeTable">
				<div id="slideshow">
					<img id="imgAnnuncio0" onerror="this.onerror=null; this.src='/UniAds/img/error.png'" src="PrelevaImmaginiServlet?email=<%=annuncio.getUtente().getEmail()%>&titolo=<%=annuncio.getTitolo()%>" class="slides" onclick="changeImage()" >
					<br/>
					<button id="indietro" onclick="visualizzaImgIndietro('<%=annuncio.getTitolo()%>','<%=annuncio.getUtente().getEmail() %>')">&lt;</button><button onclick="visualizzaImgAvanti('<%=annuncio.getTitolo()%>','<%=annuncio.getUtente().getEmail() %>')" id="avanti">&gt;</button>
				</div>
				<div>
					<div id="descrizione"><%= annuncio.getDescrizione() %> </div>
					<div id="elenco">
						<span class="proprieta">Rilasciato da: </span> 
						<%= annuncio.getUtente().getEmail() %>	<br>
						<span class="proprieta">Appartenente a: </span>
						<%= annuncio.getSiglaUni() %> <br>
						<span class="proprieta">Categoria: </span>
						<%= annuncio.getCategoria().getNome() %> <br>
						<% isLog = (Boolean) request.getSession().getAttribute("login"); 
						if(isLog!=null && isLog.equals(true) && object!=null){ %>
						<div>
							<form action="mailto: <%= annuncio.getUtente().getEmail() %>" method="GET" >
								<button class="btnAnnuncio"> Contatta </button>
							</form>
							<% if (annuncio.isAcquistoOnline()) { %>
								<button id="acquisto" class="btnAnnuncio" onclick="displaySelect()"> Acquista Online </button> 
							<% } %>
							<div id="corriere"></div>
							<div id="response"></div>
						</div>
						<% } %>
					</div>
				</div>
			</div>
		</div> 
		
			
  
		<%@include file="Footer.jsp" %>
			
		<script src="/UniAds/js/jquery.js"></script>
		<script src="/UniAds/js/funzioni.js"></script>	
		
	</body>
</html>