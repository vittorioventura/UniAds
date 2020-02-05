<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Operazioni Admin-UniAds</title>
		
			
	<link rel="stylesheet" type="text/css" href="/UniAds/css/style.css">
	<link rel="stylesheet" type="text/css" href="/UniAds/css/LoginRegistrazione.css">
	<link rel="stylesheet" type="text/css" href="/UniAds/css/InserimentoAnnuncio.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
		
	 </head>
	 
	<body onload=mostraCategorie();mostraUniversita()>
		<%@include file="/Tutti/Header.jsp"%>
	
		<%@include file="/Tutti/BarraNavigazione.jsp"%>
	
	<div class="container">
	
		<!-- INSERIMENTO UNIVERSITA --> 
		<fieldset class="fieldVit"><legend style="font-weight:bold; color: #2f2f2f;">Aggiungi Università</legend>
		 <form action="<%=response.encodeURL("/UniAds/Admin/InserimentoUniversitaServlet")%>" method="GET" id="formCategoria">
			Sigla universita<br/>
			<input class="input-text" type = "text" id="siglaUniversita" name = "siglaUniversita" value="" required onkeyup= "maiuscolo(this.form,'siglaUniversita')"> <br/>
			<%
 				String erroreSigla = (String)request.getAttribute("erroreSigla");
 				if(erroreSigla != null){
 			%>
 				
				<span class="errore"><%=erroreSigla%></span><br/>
				
			<%
					request.removeAttribute("erroreSigla");
 				}
			%>
		
			Località
			<br/>
			<input class="input-text" type = "text" id="nomeLocalita" name = "nomeLocalita" value="" required onblur="primaLetteraMaiuscola(this.form,'nomeLocalita')"> <br/>
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
 				String completamentoInserimentoUniversita = (String)request.getSession().getAttribute("completamentoInserimentoUniversita");
 				if(completamentoInserimentoUniversita!=null){
 			%>
			<span class="successo"><%=completamentoInserimentoUniversita%></span><br/>
			<%
				request.getSession().setAttribute("completamentoInserimentoUniversita",null);
 				}   
 			%>
			
			
			<br/><input class="pulsantilogin-registrazione-add" type = "submit" value="Aggiungi">
						 
 		</form>
 		<br/>
 		
 		</fieldset>
 		
 		<!-- INSERIMENTO CATEGORIA-->
 		<fieldset class="fieldVit"><legend style="font-weight:bold; color: #2f2f2f;">Inserimento Categoria</legend>
 		<form action="<%=response.encodeURL("/UniAds/Admin/InserimentoCategoriaServlet")%>" method="GET" id="formCategoria">
			Nome categoria<br/>
			<input class="input-text" type = "text" id="nomeCategoria" name = "nomeCategoria" value="" required onblur="primaLetteraMaiuscola(this.form,'nomeCategoria')"> <br/>
			<%
 			String erroreInserimentoCateogira = (String)request.getAttribute("erroreInserimentoCateogira");
 			if(erroreInserimentoCateogira!=null){
 			%>
			<span class="errore"><%=erroreInserimentoCateogira%></span><br/>
			<%
 			}
			%>
			<%
 				String completamentoCategoria = (String)request.getSession().getAttribute("completamentoCategoria");
 				if(completamentoCategoria!=null){
 			%>
			<span class="successo"><%=completamentoCategoria%></span><br/>
			<%
				request.getSession().setAttribute("completamentoCategoria",null);
			}
 				
			%>
			
			<br/><input class="pulsantilogin-registrazione-add" type = "submit" value="Aggiungi" > 
 		</form>
 		
 		<br/>
 		</fieldset>
 		
 		<!-- ELIMINA CATEGORIE -->
		<fieldset class="fieldVit"><legend style="font-weight:bold; color: #2f2f2f;">Elimina Categoria:</legend>
		<form action = "<%=response.encodeURL("/UniAds/Admin/EliminaCategoriaServlet")%>" method = "GET" name = "formEliminaCategoria" >
			<select name="categorie" id="selectCategoria2"  class="selectVit" > <!-- C'era class="select" -->
				<option value="0" selected="selected">Elimina categoria:</option>
			</select>
			<%
				String erroreEliminazioneCategoria = (String)request.getAttribute("erroreEliminazioneCategoria");
				if(erroreEliminazioneCategoria!=null){	
			%>
				<br/>
				<span class="errore"><%=erroreEliminazioneCategoria%></span>
			<%
				}
			%>			<%
				String completamentoEliminazioneCategoria = (String)request.getSession().getAttribute("completamentoEliminazioneCategoria");
			
				if(completamentoEliminazioneCategoria!=null){	
			%>
				<br/>
				<span class="successo"><%=completamentoEliminazioneCategoria%></span>
			<%
				request.getSession().setAttribute("completamentoEliminazioneCategoria",null);
				}
			%>
			
			<br/>
			<br/><input class="pulsantilogin-registrazione-add" type ="submit" value="Elimina" >
		</form>
		<br/>
		</fieldset>
		
		<!-- ELIMINA UNIVERSITA -->
		<fieldset class="fieldVit"><legend style="font-weight:bold; color: #2f2f2f;">Elimina Università:</legend>
		<form action = "<%=response.encodeURL("/UniAds/Admin/EliminaUniversitaServlet")%>" method="GET">
			<select name="universita" id="selectUniversita2" class="selectVit">  <!-- C'era class="select" -->
					<option value="0" selected="selected">Seleziona Università:</option>
			</select>
			<br/>
			
			<%
				String erroreEliminazioneUniversita = (String)request.getAttribute("erroreEliminazioneUniversita");
				if(erroreEliminazioneUniversita!=null){	
			%>
				
				<span class="errore"><%=erroreEliminazioneUniversita%></span><br/>
			<%
				request.setAttribute("erroreEliminazioneUniversita", null);
				}
			%>		
			<%
				String completamentoEliminazioneUniversita = (String)request.getAttribute("completamentoEliminazioneUniversita");
			
				if(completamentoEliminazioneUniversita!=null){	
			%>
				<span class="successo"><%=completamentoEliminazioneUniversita%></span><br/>
			<%
				request.setAttribute("completamentoEliminazioneUniversita",null);
				}
			%>
			<br/><input class="pulsantilogin-registrazione-add" type ="submit" value="Elimina" >
		</form>
		<br/>
		</fieldset>
		
		<fieldset class="fieldVit"><legend style="font-weight:bold; color: #2f2f2f;">Elimina Annuncio:</legend>
		<form action = "<%=response.encodeURL("/UniAds/Admin/EliminaAnnuncioServlet")%>" method="GET">
			
			Email utente<br/>
			<input class="input-text" type="email" name="emailUtente" value="" required>
			<br/>
			Titolo annuncio<br/>
			<input class="input-text"  type="text" name="titoloAnnuncio" value="" required>
			
			
			<br/>
			
			<%
				String erroreEliminazioneAnnuncio = (String)request.getAttribute("erroreEliminazioneAnnuncio");
				if(erroreEliminazioneAnnuncio!=null){	
			%>
				
				<span class="errore"><%=erroreEliminazioneAnnuncio%></span><br/>
			<%
				request.setAttribute("erroreEliminazioneAnnuncio", null);
				}
			%>		
			<%
				String completamentoEliminazioneAnnuncio = (String)request.getAttribute("completamentoEliminazioneAnnuncio");
			
				if(completamentoEliminazioneAnnuncio!=null){	
			%>
				<span class="successo"><%=completamentoEliminazioneAnnuncio%></span><br/>
			<%
				request.setAttribute("completamentoEliminazioneAnnuncio",null);
				}
			%>
			<br/><input class="pulsantilogin-registrazione-add" type ="submit" value="Elimina" >
		</form>	
		<br/>	
		</fieldset>
		
		<fieldset class="fieldVit"><legend style="font-weight:bold; color: #2f2f2f;">Elimina Utente</legend>
		
		<form action = "<%=response.encodeURL("/UniAds/Admin/EliminaUtenteServlet")%>" method="GET">
			
			Email utente<br/>
			<input class="input-text" type="email" name="emailUtente" value="" required>
			<br/>			
			
			<%
				String erroreEliminazioneUtente = (String)request.getAttribute("erroreEliminazioneUtente");
				if(erroreEliminazioneUtente!=null){	
			%>
				
				<span class="errore"><%=erroreEliminazioneUtente%></span><br/>
			<%
				request.setAttribute("erroreEliminazioneUtente", null);
				}
			%>		
			<%
				String completamentoEliminazioneUtente = (String)request.getAttribute("completamentoEliminazioneUtente");
			
				if(completamentoEliminazioneUtente!=null){	
			%>
				<span class="successo"><%=completamentoEliminazioneUtente%></span><br/>
			<%
				request.setAttribute("completamentoEliminazioneUtente",null);
				}
			%>
			<br/><input class="pulsantilogin-registrazione-add" type ="submit" value="Elimina" >
		</form>		
		 
		<br/>
		</fieldset>
		
		<fieldset class="fieldVit"><legend style="font-weight:bold; color: #2f2f2f;">Modifica Ruolo:</legend>
			<form action="<%=response.encodeURL("/UniAds/Admin/ModificaRuoloServlet")%>" method="GET" id="formCategoria">
			Email Ruolo da Modificare<br/>
			<input class="input-text" type = "text" name = "emailUtente" value="" required ><br/> <br/>
			<%
 			String erroreModificaRuolo = (String)request.getAttribute("erroreModificaRuolo");
 			if(erroreModificaRuolo!=null){
 			%>
			<span class="errore"><%=erroreModificaRuolo%></span><br/>
			<%
 			}
			%>
			<%
 				String completamentoModifica = (String)request.getAttribute("completamentoModifica");
 				if(completamentoModifica!=null){
 			%>
			<span class="successo"><%=completamentoModifica%></span><br/>
			<%
				request.setAttribute("completamentoModifica",null);
			}
 				
			%>
			<input type="radio" name="ruolo" value="Utente" checked="checked">Utente
			<input type="radio" name="ruolo" value="Admin">Admin<br/><br/>
			<br/><input class="pulsantilogin-registrazione-add" type = "submit" value="Modifica" > 
 		</form>
 		</fieldset>
 		</div>
 		
		
			<%@include file="/Tutti/Footer.jsp"%>
				 		
 		<script src="/UniAds/js/jquery.js"></script>
		<script src="/UniAds/js/funzioni.js"></script>
			
 	</body>
</html>