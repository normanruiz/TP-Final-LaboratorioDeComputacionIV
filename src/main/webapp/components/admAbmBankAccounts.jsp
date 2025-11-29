<%@ page import="dominio.entity.Client" %>
<%@ page import="dominio.entity.BankAccount" %>
<%@ page import="java.util.ArrayList" %>

<div class="contenedor-cuentas-administrador">

	<% 
		ArrayList<Client> clientslist = (ArrayList<Client>)session.getAttribute("clientsList");
		for ( Client client : clientslist ) { 
	%>
			<details class="details-cuentas-administrador">
			
				<summary class="summary-cuentas-administrador"> Cliente Numero: <%= client.getId() %> - Nombre: <%= client.getFullName() %> - DNI: <%= client.getDNI() %> </summary>

				<div class="contenedor-cuentas-adicion-administrador">
					<form method="post" action="ServletAdminBankAccounts" class="formulario-cuentas-administrador">
						<div class="contenedor-label-cuentas-adicion-administrador">
							<label for="bankAccountTypeInput" class="label-general">Tipo de cuenta</label>
						</div>
						<div class="contenedor-label-cuentas-adicion-administrador">
							<label for="SAVINGSBANK" class="label-input-radio">Caja de ahorro</label>
							<input type="radio" id="SAVINGSBANK" name="bankAccountTypeInput" value="SAVINGSBANK" checked>
							<label for="CURRENTACCOUNT" class="label-input-radio">Cuenta corriente</label>
							<input type="radio" id="CURRENTACCOUNT" name="bankAccountTypeInput" value="CURRENTACCOUNT">
						</div>
						
						<input type="submit" class="boton-cuentas-admonistrador" name="inputBankAccountsAdd" value="Adicionar cuenta bancaria" <%= client.getBankAccounts().size() == 3 ? "DISABLED" : "" %> ></input>
						<input type="hidden" name="idClientBankAccountsAdd" value="<%= client.getId() %>"></input>

						
					</form>
				</div>
				
				<div class="contenedor-cuentas-detalle-administrador">
					<% 
						ArrayList<BankAccount> BankAccountslist = client.getBankAccounts();
						for ( BankAccount bankAccount : BankAccountslist ) { 
					%>
							<hr/>
							<p class="datos-cuentas-detalle-administrador">	Numero de cuenta: <%= bankAccount.getAccountNumber() %> - Tipo de cuenta: <%= bankAccount.getBankAccountType().toString().equals("SAVINGSBANK") ? "Caja de ahorro" : "Cuenta corriente" %> - Fecha de alta: <%= bankAccount.getCreatedAt() %></p>
					<% } %>
				
				</div>

			</details>

	<% } %>

</div>