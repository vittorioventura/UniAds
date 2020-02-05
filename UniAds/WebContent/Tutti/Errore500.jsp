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
	
	<body onload=mostraCategorie();mostraUniversita()>	
	
		<%@include file="Header.jsp" %>
		<%@include file="BarraNavigazione.jsp" %>
		
		
		
		<div class="containerAds"><br><br>
		<div id="notFound"><img src="/UniAds/img/Errore500.png"><br/>
		<h3 class="text-align-center">&nbsp;</h3>
		<p style="text-align: center;color:#0091df;">Ci dispiace molto che non hai trovato quello che cercavi <br/>ma potremmo consigliarti di 
		Tornare alla&nbsp;<a class="contattaci" style="text-decoration: none;"href="/UniAds/Tutti/HomePage.jsp">home</a><br/>
		Altrimenti <a class="contattaci" style="text-decoration: none;" href="mailto:uniads@gmail.com">Contattaci</a> per richiedere Supporto</p>
		</div>
		
		
			</div>
		<%@include file="Footer.jsp" %>
			
		<script src="/UniAds/js/jquery.js"></script>
		<script src="/UniAds/js/funzioni.js"></script>	
	</body>
	
	
</html>