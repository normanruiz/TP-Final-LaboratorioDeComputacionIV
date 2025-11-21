
<% if (session.getAttribute("profile") == "admin") { %>
	<div class="contenedor-barra-menu">
		<ul class="list-barra-menu">
			<li class="item-barra-menu">
				<form method="post" action="ServletHomeAdmin" class="form-barra-menu">
					<input type="submit" name="input-abm-clients" value="Administracion de Clientes" class="input-barra-menu"></input>
				</form>
			</li>
			<li class="item-barra-menu">
				<form method="post" action="ServletHomeAdmin" class="form-barra-menu">
					<input type="submit" name="input-abm-bankaccounts" value="Administracion de Cuentas" class="input-barra-menu"></input>
				</form>
			</li>
			<li class="item-barra-menu">
				<form method="post" action="ServletHomeAdmin" class="form-barra-menu">
					<input type="submit" name="input-abm-bankloans" value="Administracion de Prestamos" class="input-barra-menu"></input>
				</form>
			</li>
			<li class="item-barra-menu">
				<form method="post" action="ServletHomeAdmin" class="form-barra-menu">
					<input type="submit" name="input-reports" value="Informes y Reportes" class="input-barra-menu"></input>
				</form>
			</li>
		</ul>
	</div>
<% } else { %>
	<div class="contenedor-barra-menu">
		<ul class="list-barra-menu">
			<li class="item-barra-menu">
				<form method="post" action="ServletHomeClient" class="form-barra-menu">
					<input type="submit" name="input-abm-account-movements" value="Movimientos de cuenta" class="input-barra-menu"></input>
				</form>
			</li>
			<li class="item-barra-menu">
				<form method="post" action="ServletHomeClient" class="form-barra-menu">
					<input type="submit" name="input-abm-bank-transfers" value="Transferencias" class="input-barra-menu"></input>
				</form>
			</li>
			<li class="item-barra-menu">
				<form method="post" action="ServletHomeClient" class="form-barra-menu">
					<input type="submit" name="input-abm-bankloans" value="Administracion de Prestamos" class="input-barra-menu"></input>
				</form>
			</li>
			<li class="item-barra-menu">
				<form method="post" action="ServletHomeClient" class="form-barra-menu">
					<input type="submit" name="input-abm-payment-bankloans" value="Pago de Prestamos" class="input-barra-menu"></input>
				</form>
			</li>
			<li class="item-barra-menu">
				<form method="post" action="ServletHomeClient" class="form-barra-menu">
					<input type="submit" name="input-personal-information" value="Informacion Personal" class="input-barra-menu"></input>
				</form>
			</li>
		</ul>
	</div>
<% } %>