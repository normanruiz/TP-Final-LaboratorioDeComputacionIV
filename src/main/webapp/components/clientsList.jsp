<%@ page import="dominio.entity.Client" %>
<%@ page import="java.util.ArrayList" %> 	
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
			<% 
				ArrayList<Client> clientslist = (ArrayList<Client>)session.getAttribute("clientsList");
				for ( Client client : clientslist ) { %>
					<tr>
						<td> <%= client.getId() %> </td>
						<td> <%= client.getName() %> </td>
						<td> <%= client.getLastName() %> </td>
						<td> <%= client.getStatus() == true ? "Activo" : "Bloqueado" %> </td>

						<td>
							<form method="post" action="ServletAdminClients" class="container-footer-form">
								<input type="submit" class="input-detalle" name="inputClientDetail" value="Detalle"></input>
								<input type="hidden" name="idClientDetail" value="<%= client.getId() %>"></input>
							</form>
						</td>
						<td>
							<form method="post" action="ServletAdminClients" class="container-footer-form">
								<input type="submit" name="inputClientModify" value="Modificar" class="input-detalle" ></input>
								<input type="hidden" name="idClientModify" value="<%= client.getId() %>"></input>
							</form>
						</td>
						<td>
							<form method="post" action="ServletAdminClients" class="container-footer-form">
								<input type="submit" class="input-detalle" name="inputClientDelete" value="Eliminar"></input>
								<input type="hidden" name="idClientDelete" value="<%= client.getId() %>"></input>
							</form>
						</td>
					</tr>
			<% } %>
		</tbody>
	</table>

</div>
	  	
	  	
	  	