
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<title>Chi Siamo</title>

<link rel="stylesheet" href="/UniAds/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
</head>

	<body onload=mostraCategorie();mostraUniversita();mostraListaRegioni()>
		<%@include file="/Tutti/Header.jsp" %>
		<%@include file="/Tutti/BarraNavigazione.jsp" %>
		<div class="containerRule">
			<h2 style="color:#188aff"><strong>Chi siamo</strong></h2>
			<p>UniAds nasce dall&rsquo;idea di poter aiutare e agevolare la vita universitaria
			L&rsquo;applicazione si rivolge quindi in particolar modo agli studenti iscritti alle varie Università italiane 
			ma anche a tutti coloro che forniscono servizi
			in ambito universitario con lo scopo di facilitare gli studenti nel loro percorso di studi.</p>
			 
			<p>Il sito permette di: </p>
			<ul>
				<li>Inserire, visualizzare ed eventualmente cancellare annunci di vario genere, dalla condivisione di appunti alla vendita di libri usati,
				 passando per la richiesta/offerta di ripetizioni etc.; </li>
				<li>Memorizzare gli annunci che l&rsquo;utente trova interessanti in un&rsquo;apposita lista desideri; </li>
				<li>Contattare privatamente l&rsquo;utente che ha caricato l&rsquo;annuncio per richiedere informazioni aggiuntive sull&rsquo;annuncio 
				stesso (per esempio riguardo le condizioni di un libro venduto, sul grado di preparazione di chi offre ripetizioni etc.)
				 ed eventualmente, in caso di annunci relativi a prodotti in vendita, di concordare una consegna fisica del prodotto; </li>
				<li>Non &egrave; possibile inserire annunci legati a beni o servizi vietati dalla legislazione italiana( es. qualsiasi tipo di arma o strumento atto ad offendere)</li>
				<li>In casi particolari, ossia per esempio quando un libro è disponibile in zone non
				 direttamente raggiungibili dall&rsquo;utente, è previsto un sistema di acquisto online, con annesso sistema di pagamento e spedizione convenzionati;</li>
				<li>In caso di acquisto online, l&rsquo;utente può decidere di effettuare la spedizione con il corriere che ritiene più opportuno; </li>
				<li>Effettuare l&rsquo;autenticazione e, precedentemente, la registrazione.</li>
			</ul><br/>

	<p>&nbsp;Necessari ulteriori chiarimenti<br/>
	<a style="text-decoration: none;color: inherit;" href="mailto:uniads@gmail.com">Contattaci</a></p>

			


		</div>
		<%@include file="/Tutti/Footer.jsp" %>
		<script src="/UniAds/js/jquery.js"></script>
		<script src="/UniAds/js/funzioni.js"></script>
	</body>
</html>