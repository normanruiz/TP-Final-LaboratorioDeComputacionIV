
<div class="container">
	
	<div class="abmClientsItems">
  		<div class="container-footer-usuario"> 
  			<span> AREA: <%= session.getAttribute("workAreaLabel") %> </span> 
  		</div>
		<div class="container-footer-boton">
			<form method="post" action="ServletAdminClients" class="container-footer-form">
				<input type="submit" class="input-disconect" name="inputClientNew" value="Nuevo"></input>
			</form>
		</div>
  	</div>
  	
  	<div class="item">
	  	<% if (session.getAttribute("abmClientOperation") == "new") { %>
			<jsp:include page="clientNewModify.jsp" />
		<% } else if (session.getAttribute("abmClientOperation") == "modify") { %>
			<jsp:include page="clientNewModify.jsp" />
		<% } else if (session.getAttribute("abmClientOperation") == "detail") { %>
			<jsp:include page="clientDetail.jsp" />
		<% } else { %>
			<jsp:include page="clientsList.jsp" />
		<% } %>
	</div>
	
</div>
