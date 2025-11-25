<%@ page import="dominio.entity.BankLoans" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dominio.entity.BankLoansPayments" %>
<%@ page import="dominio.entity.BankAccount" %>

<div class="item">

			<% 
				ArrayList<BankLoans> bankLoansAuthorizedList = (ArrayList<BankLoans>)session.getAttribute("bankLoansAuthorizedList");
				for ( BankLoans bankLoans : bankLoansAuthorizedList ) { %>
					<div class="item">
						<h4><%= bankLoans.getId() %> - <%= bankLoans.getRequestedAmount() %> - <%= bankLoans.getApplyDate() %></h4>
						<table>
							<thead>
								<tr>
									<th>Numero de cuota</th>
									<th>Importe</th>
									<th>Estado</th>
									<th>Fecha de pago</th>
									<th>Cuenta origen</th>
									<th></th>
									
								</tr>
							</thead>
							<tbody>
								<% for ( BankLoansPayments bankLoansPayments : bankLoans.getBankLoansPayments() ) { %>

									<tr>
									
										<td> <%= bankLoansPayments.getQuotaNumber() %> </td>
										<td> <%= bankLoansPayments.getAmountQuota() %> </td>
										<td> <%= bankLoansPayments.getPaid() == false ? "Pendiente" : "Pagado" %> </td>
										<td> <%= bankLoansPayments.getPaymentDate() %> </td>
				
										<form method="post" action="ServletClientPaymentBankLoans" class="container-footer-form">

											<td>
												<select  name="inputAccountsBank" <%= bankLoansPayments.getPaid() == true ? "DISABLED" : "" %>>
													<% 
														ArrayList<BankAccount> bankAccountList = (ArrayList<BankAccount>)session.getAttribute("bankAccountList");
														for ( BankAccount bankAccount : bankAccountList ) { %>
										    				<option value="<%= bankAccount.getId() %>"><%= bankAccount.getAccountNumber() %> - <%= bankAccount.getSaldo() %></option>
													<% } %>
									  			</select>
									  		</td>
									  		<td>
												<input type="submit" class="input-detalle" name="inputBankLoansPay" value="Pagar" <%= bankLoansPayments.getPaid() == true ? "DISABLED" : "" %>></input>
												<input type="hidden" name="idBankLoansPayments" value="<%= bankLoansPayments.getId() %>"></input>
											</td>
										</form>
										
									</tr>
									
								<% } %>
							</tbody>
						</table>
					</div>
			<% } %>

</div>
	  	
	  	
	  	