<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<title>Operazioni Corriere-UniAds</title>
		<link rel="stylesheet" type="text/css" href="/UniAds/css/style.css">
		<link rel="stylesheet" type="text/css" href="/UniAds/css/LoginRegistrazione.css">
		<link rel="stylesheet" type="text/css" href="/UniAds/css/InserimentoAnnuncio.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
			
	</head>
	<body onload=mostraCategorie();mostraUniversita()>
		<!--INSERIMENTO AREA DI CONSEGNA-->
		<%@include file="/Tutti/Header.jsp"%>
		<%@include file="/Tutti/BarraNavigazione.jsp"%>
		<div class="container">
	
			<fieldset class="fieldVit">
				<legend style="font-weight:bold; color: #2f2f2f;">Aggiungi area di consegna</legend>
				<form action="<%=response.encodeURL("/UniAds/Corriere/InserimentoAreaConsegnaServlet")%>" method="GET" id="formAreaConsegna">
					<input type="hidden" id="agenzia" name="agenzia" value="<%=corriere.getNomeAgenzia()%>">
					<input type="hidden" id="email" name="email" value="<%=corriere.getEmail()%>">
			
					Prezzo per consegna<br/>
					<input class="input-text" type = "number" id="prezzo" name = "prezzo" value="" placeholder="Prezzo" required> <br/>
					
					Inserire regione<br/>
					<input class="input-text" type = "text" id="regione" name = "regione" value="" placeholder="Regione" required onblur="primaLetteraMaiuscola(this.form,'regione')"> <br/>
				<%
 					String erroreRegione = (String)request.getAttribute("erroreRegione");
 					if(erroreRegione!=null){
 				%>
					<span class="errore"><%=erroreRegione%></span><br/>
				<%
						request.removeAttribute("erroreRegione");
			
 					}	
				%>
			
		
				<%
 					String completamentoInserimentoArea = (String)request.getAttribute("completamentoInserimentoArea");
 					if(completamentoInserimentoArea!=null){
 				%>
					<span class="successo"><%=completamentoInserimentoArea%></span><br/>
				<%
					request.getSession().setAttribute("completamentoInserimentoArea",null);
 					}  
 				%>
			
			
				<br/><input class="pulsantilogin-registrazione-add" type = "submit" value="Aggiungi">
						 
 			</form>
 			<br/>
		</fieldset>
		<!--ELIMINA AREA DI CONSEGNA-->
		<fieldset class="fieldVit">
			<legend style="font-weight:bold; color: #2f2f2f;">Elimina area di consegna</legend>
			<form action="<%=response.encodeURL("/UniAds/Corriere/EliminazioneAreaConsegnaServlet")%>" method="GET" id="formAreaConsegna">
				<input type="hidden" id="agenzia" name="agenzia" value="<%=corriere.getNomeAgenzia()%>">
				<input type="hidden" id="email" name="email" value="<%=corriere.getEmail()%>">
				
				Elimina area consegna<br/>
				<input class="input-text" type = "text" id="regione" name = "regione" value="" placeholder="Regione" onblur="primaLetteraMaiuscola(this.form,'regione')"> <br/>
				<%
 					String erroreRegioneEliminazione = (String)request.getAttribute("erroreRegioneEliminazione");
 					if(erroreRegioneEliminazione!=null){
 				%>
					<span class="errore"><%=erroreRegioneEliminazione%></span><br/>
				<%
						request.removeAttribute("erroreRegioneEliminazione");
			
 					}	
				%>
			
		
				<%
 					String completamentoEliminazioneArea = (String)request.getAttribute("completamentoEliminazioneArea");
 					if(completamentoEliminazioneArea!=null){
 				%>
					<span class="successo"><%=completamentoEliminazioneArea%></span><br/>
				<%
					request.getSession().setAttribute("completamentoEliminazioneArea",null);
 					}  
 				%>
			
			
				<br/><input class="pulsantilogin-registrazione-add" type = "submit" value="Aggiungi">
						 
 			</form>
 			<br/>
		</fieldset>
		
 	</div>	


		<%@include file="/Tutti/Footer.jsp"%>
		<script src="/UniAds/js/jquery.js"></script>

		<script src="/UniAds/js/funzioni.js"></script>
	
	</body>

</html>