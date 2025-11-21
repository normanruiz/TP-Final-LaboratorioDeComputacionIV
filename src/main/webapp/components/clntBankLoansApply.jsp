<%@ page import="dominio.entity.BankAccount" %>
<%@ page import="java.util.ArrayList" %> 	
<div>
	<h3> Solicitar de prestamo</h3>
			<form method="post" action="ServletClientBankLoans">
			
				<div>
					
					<label for="inputRequestedAmount">Monto</label>
					<input type="number" name="inputRequestedAmount" min="10000" max="1000000000" value="10000">
					
					<label for="inputQuotas">Cantidad de cuotas</label>
					<input type="number" name="inputQuotas" min="12" max="72" value="12">
  	
					<label for="inputAccountsBank">Cuenta destino</label>
					  <select  name="inputAccountsBank">
						<% 
							ArrayList<BankAccount> bankAccountList = (ArrayList<BankAccount>)session.getAttribute("bankAccountList");
							for ( BankAccount bankAccount : bankAccountList ) { %>
					    	<option value="<%= bankAccount.getId() %>"><%= bankAccount.getAccountNumber() %></option>
						<% } %>
					  </select>
														
			
				</div>
		
				<div>
					<input type="submit" class="input-disconect" name="inputApplyBankLoansCancel" value="Cancelar"></input>
					<input type="submit" class="input-disconect" name="inputApplyBankLoansSave" value="Solicitar"></input>
				</div>
	
			</form>
</div>