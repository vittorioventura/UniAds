<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!doctype html>
<html lang="en" class="no-js">
	<head>
    	<meta charset="utf-8">
    	<meta http-equiv="x-ua-compatible" content="ie=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<link rel="stylesheet" href="/UniAds/css/style.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
  		<title>UniAds</title>
	</head>
	<%@include file="/Tutti/Header.jsp" %>
	<%@include file="/Tutti/BarraNavigazione.jsp" %>
		
	<body onload="mostraCategorie();mostraUniversita();mostraListaRegioni('<%=emailParam%>')">
		<div class="container">
			<article id="grigliaCategorie">
				<a href="/UniAds/Tutti/PrelevaAnnunciServlet?universita=0&categorie=Appunti&search=&email=<%=emailParam%>" style="height: 123px"><img id="appunti" class="icon-categorie" ></a>
				<a href="/UniAds/Tutti/PrelevaAnnunciServlet?universita=0&categorie=Car Sharing&search=&email=<%=emailParam%>" style="height: 123px"><img id="car" class="icon-categorie" ></a>
				<a href="/UniAds/Tutti/PrelevaAnnunciServlet?universita=0&categorie=Offerte di Lavoro&search=&email=<%=emailParam%>" style="height: 123px"><img id="lavoro" class="icon-categorie" ></a>
				<a href="/UniAds/Tutti/PrelevaAnnunciServlet?universita=0&categorie=Ripetizioni&search=&email=<%=emailParam%>" style="height: 123px"><img id="ripetizioni" class="icon-categorie"></a>  
				<a href="/UniAds/Tutti/PrelevaAnnunciServlet?universita=0&categorie=Libri&search=&email=<%=emailParam%>" style="height: 123px"><img id="libri" class="icon-categorie"></a>
				<a href="/UniAds/Tutti/PrelevaAnnunciServlet?universita=0&categorie=Affitti&search=&email=<%=emailParam%>" style="height: 123px"><img id="affitti" class="icon-categorie"></a>
			</article>
			<article id="regioni">
				<ul class="regione-lista" id="regione-lista1"></ul>
				<ul class="regione-lista" id="regione-lista2"></ul>
			</article>
		</div>
		<%@include file="/Tutti/Footer.jsp" %>
		<script src="/UniAds/js/jquery.js"></script>
		<script src="/UniAds/js/funzioni.js"></script>
	</body>
</html>

