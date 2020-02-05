<%@page import="it.unisa.model.GenericUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>UniAds - Inserisci Annunci</title>
		
		<link rel="stylesheet" type="text/css" href="/UniAds/css/style.css">
		<link rel="stylesheet" type="text/css" href="/UniAds/css/InserimentoAnnuncio.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
		
	</head>
	
	<body onload=mostraCategorie();mostraUniversita();>
		<%@include file="/Tutti/Header.jsp"%>
		<%@include file="/Tutti/BarraNavigazione.jsp"%>
		<% isLog = (Boolean) request.getSession().getAttribute("login"); 
			if(isLog!=null && isLog.equals(true) && object!=null){
				String email="";
				String error="";
				if(object instanceof Amministratore){
					amministratore = (Amministratore)request.getSession().getAttribute("utente");					
					email = amministratore.getEmail();
					error = (String)request.getAttribute("errore");
				}
				if(object instanceof Utente){
					utente = (Utente) request.getSession().getAttribute("utente");
					email = utente.getEmail();
  					error = (String)request.getAttribute("errore");
				}
				if(object instanceof Corriere){
					corriere = (Corriere) request.getSession().getAttribute("utente");
					email = corriere.getEmail();
  					error = (String)request.getAttribute("errore");
				}
				
				
		%>
		
	
   	<div class="container_insert">
	<fieldset class="fieldVit">

		<form action="<%=response.encodeURL("/UniAds/User/InserimentoAnnuncioServlet")%>" method="POST" id="formInserisciAnnuncio" enctype="multipart/form-data">
			
				
			<input type = "hidden" value="<%=email%>" name="email">
			<input type ="hidden" value="0" name="valutazione">
			<p class="dettagli">Titolo Annuncio:</p>
			<input class="input-text" type = "text" value="" name="titoloAnnuncio" placeholder="Titolo" onblur="primaLetteraMaiuscola(this.form,'titoloAnnuncio')" required="required"><br/>
			<br/>
			<fieldset style="border: 2px dashed #838383;" id="fieldsetImg">
				<legend class="dettagli">Seleziona immagini(facoltativo)</legend>
				<div class="img" id="divImg">
					<div>
						<input type="file"  value="Scegli immagine" name="img" size="200" id="uploadImg0" multiple="multiple" >
					
						<input type ="button" onclick="aggiungiImmagine()" value="+">
					</div>
				</div>
				
				
				<br/>				
			</fieldset><br/>
			<select name="categorie" id="selectCategoria2">
				<option value="0" selected="selected">Seleziona categoria:</option>
			</select>
			
			
			<select name="universita" id="selectUniversita2">
				<option value="0" selected="selected">Seleziona università:</option>
			</select>
			
			<br/>
			<br/>
			
			<textarea name = "descrizione" placeholder="Descrizione annuncio" onblur="primaLetteraMaiuscola(this.form,'descrizione')"></textarea>
			
			<br/>
			
			<input type = "radio"  name="tipo" value="vendita" checked="checked">Vendita
			<input type = "radio"  name="tipo" value="cercasi">Cercasi
			<input type = "radio"  name="tipo" value="offerta">Offerta
			<br/>
			
			<br/>
			
			<% if(error != null && !error.equals("")) { %>	
				<span class="errore"> <%=error %> </span>
			<% } %>
			<br/>
			<input type="submit" class="pulsantilogin-registrazione-add" value="Aggiungi">
				
		</form>	
		<%
			}
		%>	
			
  </fieldset>
  </div>
			<%@include file="/Tutti/Footer.jsp"%>

		<script src="/UniAds/js/jquery.js"></script>
		<script src="/UniAds/js/funzioni.js"></script>
		
		
		
	</body>

</html>