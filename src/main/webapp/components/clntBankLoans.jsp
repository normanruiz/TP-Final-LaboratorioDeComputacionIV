
<div class="contenedor-cliente-prestamos-bancarios">
	
	<div class="contenedor-cliente-prestamos-bancarios-boton-solicitar">
		<form method="post" action="ServletClientBankLoans" class="cliente-prestamos-bancarios-formulario-boton-solicitar">
			<input type="submit" class="boton-solicitar-prestamos-bancarios" name="inputApplyBankLoans" value="Solicitar prestamo"></input>
		</form>
	</div>

  	<% if (session.getAttribute("applyBankLoansOperation") == "applyBankLoans") { %>
		<jsp:include page="clntBankLoansApply.jsp" />
	<% } else { %>
		<jsp:include page="clntBankLoansList.jsp" />
	<% } %>

</div>
