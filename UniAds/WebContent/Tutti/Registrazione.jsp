<%@page import="it.unisa.model.GenericUser"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html lang="ita">

<head>

<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>UniAds.it - Registrazione</title>

<link rel="stylesheet" type="text/css" href="/UniAds/css/style.css">
<link rel="stylesheet" type="text/css" href="/UniAds/css/LoginRegistrazione.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">

</head>

<body onload=mostraCategorie();mostraUniversita();mostraListaRegioni()>

	<%@include file="Header.jsp"%>
	<%@include file="BarraNavigazione.jsp"%>
	
	<%
		String erroreEmail = (String) request.getAttribute("erroreEmail");

		String erroreNome = (String) request.getAttribute("erroreNome");
		
		String erroreCognome = (String) request.getAttribute("erroreCognome");
		
		String errorePassword = (String) request.getAttribute("errorePassword");
		
		String nome = "";

		String cognome = "";

		String indirizzo = "";

		if ((!(erroreNome == null || erroreNome.equals(""))) && (!(erroreEmail== null || erroreEmail.equals(""))) &&  (!(erroreCognome== null || erroreCognome.equals(""))) && (!(errorePassword== null || errorePassword.equals("")))) {

			utente = (Utente) request.getAttribute("utente");

			nome = utente.getNome();

			cognome = utente.getCognome();

			indirizzo = utente.getIndirizzo();

		}
		
	%>

	<div class="container">
		<fieldset style="width: 300px; margin-left: auto; margin-right: auto;">
					<p class="scritte">Registrazione</p>
		<form action="<%=response.encodeURL("/UniAds/Registrazione")%>" method="POST" name="formReg"  onsubmit="return validazione(this)">

				

					<div class="form-riga" id="emailRegistrazione">

						<label class="dettagli" for="e-mail">E-mail</label><br /> <input class="input-text" type="email" value="" name="emailRegistrazione" placeholder="e-mail" required="required">

					</div>

					<%
						if (!(erroreEmail == null || erroreEmail.equals(""))) {
					%>

					<span class="errore">E-mail non valida.</span>

					<%
						}
					%>

					<div class="form-riga" id="nome">

						<label class="dettagli" for="nome">Nome</label><br/> <input class="input-text" type="text" value="<%=nome%>" name="nome" placeholder="nome" required="required" onblur="primaLetteraMaiuscola(this.form,'nome')">

					</div>
					<%
						if (!(erroreNome == null || erroreNome.equals(""))) {
					%>

					<span class="errore">Nome non valido</span>

					<%
						}
					%>
				

					<div class="form-riga" id="cognome">

		<label class="dettagli" for="cognome">Cognome</label><br/>
<input class="input-text" type="text" value="<%=cognome%>" name="cognome" placeholder="cognome" required="required" onblur="primaLetteraMaiuscola(this.form,'cognome')">

					</div>
					<%
						if (!(erroreCognome == null || erroreCognome.equals(""))) {
					%>

					<span class="errore">Cognome non valido</span>

					<%
						}
					%>


					<div class="form-riga" id="indirizzo">

			<label class="dettagli" for="cognome">Indirizzo</label><br/><input class="input-text" type="text" value="<%=indirizzo%>"name="indirizzo" placeholder="indirizzo" required="required">

					</div>

					<div class="form-riga" class="password">

						<label class="dettagli" for="password">Password</label><br /> <input class="input-text" type="password" value="" name="password" placeholder="password" required="required" onkeyup="checkPassword()">

					</div>
					
					<%
						if (!(errorePassword == null || errorePassword.equals(""))) {
					%>

					<span class="errore">Password non valida</span>

					<%
						}
					%>

					<div class="form-riga" id="password2">

						<label class="dettagli" for="password">Conferma password</label><br /> <input class="input-text" type="password" value="" name="password2" placeholder="password" required="required" onkeyup="checkPassword()">

					</div>

					<br/>

					<div class="form-riga" id="ruolo">

						<fieldset style="display:none;">

							<legend>Ruolo</legend>

							<label style="display:none;" class="dettagli" for="select"><br/>Utente<input type="radio" name="ruolo" value="Utente" checked="checked" onclick="unDisplayAgenzia()"> 
							Corriere<input type="radio" name="ruolo" value="Corriere" onclick="displayAgenzia()"></label>

						</fieldset>

					</div>

					<br> <label for="login"></label><input type="submit" value="Registrazione" id="registrazione" class="pulsantilogin-registrazione-add">
	
		</form>

	
 </fieldset>
 
</div>

	<%@include file="Footer.jsp"%>

	
	<script src="/UniAds/js/jquery.js"></script>
	<script src="/UniAds/js/funzioni.js"></script>
</body>

</html>