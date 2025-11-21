<%@ page import="dominio.entity.BankLoans" %>
<%@ page import="java.util.ArrayList" %> 	
<div class="item">
	<table>
		<thead>
			<tr>
				<th>#</th>
				<th>Monto Solictado</th>
				<th>Plazo</th>
				<th>Interes</th>
				<th>Importe de la cuota</th>
				<th>Cuenta destino</th>				
				<th>Estado</th>
			</tr>
		</thead>
		<tbody>
			<% 
				ArrayList<BankLoans> bankLoansList = (ArrayList<BankLoans>)session.getAttribute("bankLoansList");
				for ( BankLoans bankLoans : bankLoansList ) { %>
					<tr>
						<td> <%= bankLoans.getId() %> </td>
						
						<td> <%= bankLoans.getRequestedAmount() %> </td>
						<td> <%= bankLoans.getQuotas() %> </td>
						<td> <%= bankLoans.getBankInterest() %> </td>
						<td> <%= bankLoans.getAmountQuota() %> </td>
						<td> <%= bankLoans.getBankAccount().getAccountNumber() %> </td>
						<td> <%= bankLoans.getStatus() %> </td>

					</tr>
			<% } %>
		</tbody>
	</table>

</div>
	  	
	  	
	  	