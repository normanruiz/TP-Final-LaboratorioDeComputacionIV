
<div class="container">
	
	<div class="abmClientsItems">
  		<div class="container-footer-usuario"> 
  			<span> AREA: <%= session.getAttribute("workAreaLabel") %> </span> 
  		</div>
  	</div>
  	
  	<div class="item">

			<jsp:include page="admBankAccountsList.jsp" />

	</div>
	
</div>