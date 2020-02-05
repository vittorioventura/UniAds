
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<title>Regolamento UniAds</title>

<link rel="stylesheet" href="/UniAds/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
</head>

	<body onload=mostraCategorie();mostraUniversita();mostraListaRegioni()>
		<%@include file="/Tutti/Header.jsp" %>
		<%@include file="/Tutti/BarraNavigazione.jsp" %>
		<div class="containerRule">
		
			<h2 style="color:#188aff"><strong>Regolamento UniAds</strong></h2><br>
			<ul>
				<li>Non &egrave; possibile inserire nel titolo annunci parole chiavi non rilevanti</li>
				<li>Non &egrave; possibile inserire link all'interno degli annunci.</li>
				<li>Non &egrave; possibile inserire annunci con termini razzisti e/o discriminatori e/o con contenuti offensivi per gruppi etnici, personaggi pubblici o singoli privati.</li>
				<li>Non &egrave; possibile inserire annunci legati a beni o servizi vietati dalla legislazione italiana( es. qualsiasi tipo di arma o strumento atto ad offendere)</li>
				<li>Documenti d'identit&agrave; o tesserini identificativi</li>
				<li>Materiale pornografico o volgare</li>
				<li>Prodotti falsi o contraffatti</li>
			</ul><br/>

<p>&nbsp;Necessari ulteriori chiarimenti?<br/>
<a style="text-decoration: none;color: inherit;" href="mailto:uniads@gmail.com">Contattaci</a></p>

			


		</div>
		<%@include file="/Tutti/Footer.jsp" %>
		<script src="/UniAds/js/jquery.js"></script>
		<script src="/UniAds/js/funzioni.js"></script>
	</body>
</html>
