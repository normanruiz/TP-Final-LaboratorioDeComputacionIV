
<div class="">
	
	<div class="">
  		<div class=""> 
  			<span> AREA: <%= session.getAttribute("workAreaLabel") %> </span> 
  		</div>
		<div class="">
			<form method="post" action="ServletClientBankLoans" class="container-footer-form">
				<input type="submit" class="input-disconect" name="inputApplyBankLoans" value="Solictar prestamo"></input>
			</form>
		</div>
  	</div>
  	
  	<div class="item">
	  	<% if (session.getAttribute("applyBankLoansOperation") == "applyBankLoans") { %>
			<jsp:include page="clntBankLoansApply.jsp" />
		<% } else { %>
			<jsp:include page="clntBankLoansList.jsp" />
		<% } %>
	</div>
	
</div>
