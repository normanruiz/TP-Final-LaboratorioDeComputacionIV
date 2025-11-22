<%@ page import="dominio.entity.BankAccount" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dominio.entity.BankMovement" %>

<div class="container">
	
	<div class="abmClientsItems">
  		<div class="container-footer-usuario"> 
  			<span> AREA: <%= session.getAttribute("workAreaLabel") %> </span> 
  		</div>
		<div class="container-footer-boton">
			<ul class="list-barra-menu">
				<% 			
					ArrayList<BankAccount> bankAccountslist = (ArrayList<BankAccount>)session.getAttribute("bankAccountslist");
					for ( BankAccount bankAccounts : bankAccountslist ) { %>
					<li>
						<form method="post" action="ServletClientAccountMovements" class="container-footer-form">
							<input type="submit" class="input-detalle" name="inputBankAccountSelected" value="<%= bankAccounts.getAccountNumber() %>"></input>
							<input type="hidden" name="idBankAccountSelected" value="<%= bankAccounts.getId() %>"></input>
						</form>
					</li>
				<% } %>
			</ul>
		</div>
  	</div>
  	
  	<div class="item">
	  	<% if (session.getAttribute("bankMovementslist") != null) { %>
	  		<div class="item">
				<table>
					<thead>
						<tr>
							<th>#</th>
							<th>Cliente</th>
							<th>Cuenta</th>
							<th>Monto</th>
							<th>Tipo de movimiento</th>
							<th>Fecha</th>
							<th>Detalle</th>
						</tr>
					</thead>
					<tbody>
						<% 
							ArrayList<BankMovement> bankMovementslist = (ArrayList<BankMovement>)session.getAttribute("bankMovementslist");
							for ( BankMovement bankMovement : bankMovementslist ) { %>
								<tr>
									<td> <%= bankMovement.getId() %> </td>
									<td> <%= bankMovement.getClient().getName() %> </td>
									<td> <%= bankMovement.getBankAccount().getAccountNumber() %> </td>
									<td> <%= bankMovement.getAmount() %> </td>
									<td> <%= bankMovement.getTypeMovements().toString() %> </td>
									<td> <%= bankMovement.getCreatedAt() %> </td>
									<td> <%= bankMovement.getDetail() %> </td>
								</tr>
						<% } %>
					</tbody>
				</table>
			</div>
		<% } else { %>
			<h3>No se han registrado movimientos para la selección actual.</h3>
		<% } %>
	</div>
	
</div>
