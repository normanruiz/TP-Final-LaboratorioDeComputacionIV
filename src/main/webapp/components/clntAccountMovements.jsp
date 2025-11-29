<%@ page import="dominio.entity.BankAccount" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dominio.entity.BankMovement" %>

<div class="contenedor-movimientos-cliente">

	<div class="contenedor-lista-cuenta-movimientos-cliente">
		<ul class="lista-cuenta-movimientos-cliente">
			<% 			
				ArrayList<BankAccount> bankAccountslist = (ArrayList<BankAccount>)session.getAttribute("bankAccountslist");
				for ( BankAccount bankAccounts : bankAccountslist ) { %>
				<li class="item-cuenta-movimientos-cliente">
					<form method="post" action="ServletClientAccountMovements" class="formulario-cuenta-movimientos-cliente">
						<input type="submit" class="boton-cuenta-movimientos-cliente" name="inputBankAccountSelected" value="<%= bankAccounts.getAccountNumber() %>"></input>
						<input type="hidden" name="idBankAccountSelected" value="<%= bankAccounts.getId() %>"></input>
					</form>
				</li>
			<% } %>
		</ul>
	</div>

  	
  	<div class="contendor-movimientos-cliente-detalle">
	  	<% if (session.getAttribute("bankMovementslist") != null) { %>

			<table>
				<thead>
					<tr>
						<th>#</th>
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
								<td> <%= bankMovement.getBankAccount().getAccountNumber() %> </td>
								<td> <%= bankMovement.getAmount() %> </td>
								<td> <%
								switch (bankMovement.getTypeMovements().toString()) {
								 case "CREATEDBANKACCOUNT":
							            out.print("Alta de cuenta");
							            break;
							        case "CREATEDBANKLOAN":
							            out.print("Alta de préstamo");
							            break;

							        case "PAYMENTBANKLOAN":
							            out.print("Pago de préstamo");
							            break;

							        default:
							            out.print("Transferencia");
							            break;
							} %> </td>
								<td> <%= bankMovement.getCreatedAt() %> </td>
								<td> <%= bankMovement.getDetail() %> </td>
							</tr>
					<% } %>
				</tbody>
			</table>

		<% } else { %>
		
			<p class="cliente-listado-prestamos-bancarios-aviso" >No se han registrado movimientos para la selección actual.</p>
		
		<% } %>
	</div>
	
</div>
