<%@ page import="dominio.entity.BankAccount" %>
<%@ page import="java.util.ArrayList" %>

<div class="contenedor-cliente-solicitud-prestamos-bancarios">
	<h3 class="cliente-solicitud-prestamos-bancarios-titulo"> Solicitud de prestamo</h3>
	<form method="post" action="ServletClientBankLoans" class="formulario-cliente-solicitud-prestamos-bancarios">
		
		<div class="contenedor-cliente-inputs-solicitud-prestamos-bancarios">
			
			<label for="inputRequestedAmount" class="label-cliente-solicitud-prestamos-bancarios">Monto solicitado</label>
			<input type="number" id="inputRequestedAmount" name="inputRequestedAmount" class="inputnumber-cliente-solicitud-prestamos-bancarios" min="10000" max="1000000000" value="10000" required>
			<hr/>
			<label for="inputQuotas" class="label-cliente-solicitud-prestamos-bancarios">Cantidad de cuotas</label>
			<input type="number" id="inputQuotas" name="inputQuotas" class="inputnumber-cliente-solicitud-prestamos-bancarios" min="12" max="72" value="12" required>
			<hr/>
			<label for="inputAccountsBank" class="label-cliente-solicitud-prestamos-bancarios">Cuenta destino</label>
			  <select  name="inputAccountsBank" id="inputAccountsBank" class="select-cliente-solicitud-prestamos-bancarios">
				<% 
					ArrayList<BankAccount> bankAccountList = (ArrayList<BankAccount>)session.getAttribute("bankAccountList");
					for ( BankAccount bankAccount : bankAccountList ) { %>
			    	<option value="<%= bankAccount.getId() %>"><%= bankAccount.getAccountNumber() %></option>
				<% } %>
			  </select>
												
		</div>
	
		<div class="contenedor-cliente-botones-solicitud-prestamos-bancarios">
			<input type="submit" class="boton-cancelar-solicitud-prestamos-bancarios" name="inputApplyBankLoansCancel" value="Cancelar"></input>
			<input type="submit" class="boton-guardar-solicitud-prestamos-bancarios" name="inputApplyBankLoansSave" value="Solicitar"></input>
		</div>

	</form>
</div>