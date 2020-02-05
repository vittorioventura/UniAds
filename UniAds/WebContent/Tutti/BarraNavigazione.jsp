<%@page import="it.unisa.model.Corriere"%>
<%@page import="it.unisa.model.GenericUser"%>
<%@page import="it.unisa.model.Utente"%>
<%@page import="it.unisa.model.Amministratore"%>
<div class="navigator"> 
	<%	
		String emailParam="";
		Boolean isLogNav = (Boolean) request.getSession().getAttribute("login");	
		Object objectNav = request.getSession().getAttribute("utente");
		if(isLogNav!=null && isLogNav.equals(true) && objectNav!=null && (objectNav instanceof Amministratore || objectNav instanceof Utente)){
			GenericUser genericUser = (GenericUser) objectNav;
			emailParam = genericUser.getEmail();

		}
		if(isLogNav!=null && isLogNav.equals(true) && objectNav!=null && (objectNav instanceof Corriere)){
			Corriere c = (Corriere) objectNav;
			emailParam = c.getEmail();
		}
	%>
		
		<img src="/UniAds/img/cercare.png" id="search">
		<form action="<%=response.encodeURL("/UniAds/Tutti/PrelevaAnnunciServlet")%>" method="GET" id="ricerca">
			<input type= "hidden" value="<%=emailParam%>" name="email">
			<select name="universita" class="select" id="selectUniversita">
				<option value="0" selected="selected">Seleziona Università:</option>
			</select>
			<select name="categorie" class="select" id="selectCategoria">
				<option value="0" selected="selected">Seleziona Categoria:</option>
			</select>
			<span>
				<input id="research" type="text" name="search" placeholder="Titolo..">
				<i class="fas fa-search" id="search-icon" onclick="inviaForm()"></i>
			</span>
		</form>
</div>