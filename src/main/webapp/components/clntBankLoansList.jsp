<%@ page import="dominio.entity.BankLoans" %>
<%@ page import="java.util.ArrayList" %>

<div class="contenedor-cliente-listado-prestamos-bancarios">
	<% ArrayList<BankLoans> bankLoansList = (ArrayList<BankLoans>)session.getAttribute("bankLoansList"); 
	if ( bankLoansList.size() > 0 ) { %>
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
					<th>Fecha de solicitud</th>
				</tr>
			</thead>
			<tbody>
				<% for ( BankLoans bankLoans : bankLoansList ) { %>
					<tr>
						<td> <%= bankLoans.getId() %> </td>
						
						<td> <%= bankLoans.getRequestedAmount() %> </td>
						<td> <%= bankLoans.getQuotas() %> </td>
						<td> <%= bankLoans.getBankInterest() %> </td>
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
					</tr>
				<% } %>
			</tbody>
		</table>
	<% } else { %>
		<p class="cliente-listado-prestamos-bancarios-aviso">No se encontraron prestamos bancarios para usted.</p>
	<% } %>
</div>
	  	
	  	
	  	