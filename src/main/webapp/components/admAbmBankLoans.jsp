<%@ page import="dominio.entity.BankLoans" %>
<%@ page import="java.util.ArrayList" %> 	
<div class="contenedor-prestamos-administrador">
	<table>
		<thead>
			<tr>
				<th>#</th>
				<th>Cliente</th>
				<th>Monto Solictado</th>
				<th>Plazo</th>
				<th>Interes</th>
				<th>Monto con interes</th>
				<th>Importe de la cuota</th>
				<th>Cuenta destino</th>				
				<th>Estado</th>
				<th>Fecha de solicitud</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<% 
				ArrayList<BankLoans> bankLoansList = (ArrayList<BankLoans>)session.getAttribute("bankLoansList");
				for ( BankLoans bankLoans : bankLoansList ) { %>
					<tr>
						<td> <%= bankLoans.getId() %> </td>
						<td> <%= bankLoans.getClient().getFullName() %> </td>
						<td> <%= bankLoans.getRequestedAmount() %> </td>
						<td> <%= bankLoans.getQuotas() %> </td>
						<td> <%= bankLoans.getBankInterest() %> </td>
						<td> <%= bankLoans.getAmountWithInterest() %> </td>
						<td> <%= bankLoans.getAmountQuota() %> </td>
						<td> <%= bankLoans.getBankAccount().getAccountNumber() %> </td>
						<td> <%
								switch (bankLoans.getStatus().toString()) {
								 case "PENDING":
							            out.print("Pendiente");
							            break;
							        case "AUTHORIZED":
							            out.print("Autorizado");
							            break;

							        case "CLOSE":
							            out.print("Finalizado");
							            break;

							        default:
							            out.print("Rechazado");
							            break;
							} %> </td>
						<td> <%= bankLoans.getApplyDate() %> </td>
						<td>
							<form method="post" action="ServletAdminBankLoans" class="form-prestamos-boton-admonistrador">
								<input type="submit" class="boton-prestamos-admonistrador" name="inputBankLoansAuthorized" value="Aprobar"></input>
								<input type="hidden" name="idBankLoansAuthorized" value="<%= bankLoans.getId() %>"></input>
							</form>
						</td>
						<td>
							<form method="post" action="ServletAdminBankLoans" class="form-prestamos-boton-admonistrador">
								<input type="submit" class="boton-prestamos-admonistrador" name="inputBankLoansRefused" value="Rechazar"></input>
								<input type="hidden" name="idBankLoansRefused" value="<%= bankLoans.getId() %>"></input>
							</form>
						</td>
					</tr>
			<% } %>
		</tbody>
	</table>

</div>
	  	
	  	
	  	