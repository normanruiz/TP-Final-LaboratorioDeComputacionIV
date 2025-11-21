<%@ page import="dominio.entity.Client" %>
<%@ page import="dominio.entity.BankAccount" %>
<%@ page import="java.util.ArrayList" %> 	
<div class="item">
	<ul>

			<hr/>
			<% 
				ArrayList<Client> clientslist = (ArrayList<Client>)session.getAttribute("clientsList");
				for ( Client client : clientslist ) { 
			%>
					<li>
						<div> <%= client.getId() %> - <%= client.getName() %> <%= client.getLastName() %> - <%= client.getDNI() %> </div>

						<div>
							<form method="post" action="ServletAdminBankAccounts" class="container-footer-form">
								<input type="submit" class="input-detalle" name="inputBankAccountsAdd" value="Adicionar cuenta bancaria"></input>
								
								<label for="SAVINGSBANK">Caja de ahorro</label>
								<input type="radio" id="SAVINGSBANK" name="bankAccountTypeInput" value="SAVINGSBANK" checked>
								<label for="CURRENTACCOUNT">Cuenta corriente</label>
								<input type="radio" id="CURRENTACCOUNT" name="bankAccountTypeInput" value="CURRENTACCOUNT">
								<label for="bankAccountTypeInput">Tipo de cuenta</label>
		
								<input type="hidden" name="idClientBankAccountsAdd" value="<%= client.getId() %>"></input>
							</form>
						</div>
						
						<div>
							<% 
								ArrayList<BankAccount> BankAccountslist = client.getBankAccounts();
								for ( BankAccount bankAccount : BankAccountslist ) { 
							%>
								<div>
									<%= bankAccount.getId() %> - <%= bankAccount.getAccountNumber() %> - <%= bankAccount.getBankAccountType() %>
								</div>
							<% } %>
						
						</div>

					</li>
					<hr/>
			<% } %>

	
	</ul>

</div>
