<%@ page import="dominio.entity.BankAccount" %>
<%@ page import="java.util.ArrayList" %>

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
							<th>Nombre</th>
							<th>Apellido</th>
							<th>Estado</th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
		<% } else { %>
			<h3>No se han registrado movimientos para la selección actual.</h3>
		<% } %>
	</div>
	
</div>
