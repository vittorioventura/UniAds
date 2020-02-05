<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="ita">
	<head>
	
	<meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>UniAds.it - Login</title>
	
	<link rel="stylesheet" type="text/css" href="/UniAds/css/style.css">
	<link rel="stylesheet" type="text/css" href="/UniAds/css/LoginRegistrazione.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
	 
	</head>
	
	<body onload=mostraCategorie();mostraUniversita();mostraListaRegioni()>
	
		<%@include file="Header.jsp"%>
	
		<%@include file="BarraNavigazione.jsp"%>
	
		<div class="container">
			<fieldset style="width: 300px; margin-left: auto; margin-right: auto;">
				<p class="scritte">Accedi</p>

				<form action="<%=response.encodeURL("/UniAds/Login")%>" method="POST" name="formLogin">

					<div class="form-riga">

						<label class="dettagli" for="nome">E-mail</label><br /> <input class="input-text"
							type="text" value="" name="email" placeholder="e-mail"
							required="required">

					</div>

					<div class="form-riga">

						<label class="dettagli" for="password">Password</label><br/>
						<input class="input-text" id="getPassword" type="password" value="" name="password" placeholder="password" required="required"><br/>
						<%
							String errore = (String) request.getAttribute("errore");
	
							if (!(errore == null || errore.equals(""))) {
						%>
	
							<span class='errore'>Email o password errate</span><br/>
						<%
							}
						%>
						
						<input type="checkbox" onclick="mostraPassword()">Mostra Password

					</div>

					<div class="form-riga" id="ruoloLogin">

						<fieldset>

							<legend>Ruolo</legend>

							<label for="select"></label><br/>
							Utente<input  type="radio" name="ruolo" value="Utente" checked="checked"> 
							
							Admin<input  type="radio" name="ruolo" value="Amministratore">

						</fieldset> 

					</div>

					<br> <label for="login"></label>
					<input type="submit" value="Login" class="pulsantilogin-registrazione-add">

				</form>

				<p class="dettagli">
					Sei nuovo su UniAds? <br>
					<a href="Registrazione.jsp">
					<button class="pulsantilogin-registrazione-add">Registrazione</button></a>
				</p>

			</fieldset>
		</div>
			
		<%@include file="Footer.jsp"%>
		
		<script src="/UniAds/js/jquery.js"></script>
		<script src="/UniAds/js/funzioni.js"></script>
	</body>
</html>