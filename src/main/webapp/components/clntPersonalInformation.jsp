<%@ page import="dominio.entity.Client" %>
<%@ page import="dominio.entity.BankAccount" %>
<%@ page import="java.util.ArrayList" %>

<% Client clientDetail = (Client)session.getAttribute("client"); %>

<div class="contenedor-informacion-personal">

	<div class="contenedor-informacion-personal-detalle">
	

			<p class="dato dato-informacion-personal">Id: <%= clientDetail.getId() %> </p>
			<p class="dato dato-informacion-personal">Usuario: <%= clientDetail.getUsr() %> </p>
			<p class="dato dato-informacion-personal">Contraseña: <%= clientDetail.getPwd() %> </p>
			<p class="dato dato-informacion-personal">Estado: <%= clientDetail.getStatus() == true ? "Activo" : "Bloqueado" %> </p>		

			<p class="dato dato-informacion-personal">DNI: <%= clientDetail.getDNI() %> </p>
			<p class="dato dato-informacion-personal">CUIL: <%= clientDetail.getCUIL() %> </p>
			<p class="dato dato-informacion-personal">Nombre: <%= clientDetail.getName() %> </p>
			<p class="dato dato-informacion-personal">Apellido: <%= clientDetail.getLastName() %> </p>
			<p class="dato dato-informacion-personal">Genero: <%= clientDetail.getSex().toString().equals("MALE") ? "Masculino" : "Femenino" %> </p>
			<p class="dato dato-informacion-personal">Fecha de nacimiento: <%= clientDetail.getBirthdate() %> </p>

			<p class="dato dato-informacion-personal">Nacionalidad: <%= clientDetail.getNationality() %> </p>
			<p class="dato dato-informacion-personal">Direccion: <%= clientDetail.getAddress() %> </p>
			<p class="dato dato-informacion-personal">Ciudad: <%= clientDetail.getCity() %> </p>
			<p class="dato dato-informacion-personal">Estado: <%= clientDetail.getState() %> </p>
			<p class="dato dato-informacion-personal">Telefono: <%= clientDetail.getPhone() %> </p>
			<p class="dato dato-informacion-personal">Correo electronico: <%= clientDetail.geteMail() %> </p>

			<p class="dato dato-informacion-personal">Fecha de alta: <%= clientDetail.getCreatedAt() %> </p>
			<p class="dato dato-informacion-personal">Fecha de ultima actualizacion: <%= clientDetail.getUpdatedAt() %> </p>

		
	</div>

	<div class="contenedor-informacion-personal-cuentas">
			<% for ( BankAccount bankAccount : clientDetail.getBankAccounts() ) { %>

				<div class="contenedor-informacion-personal-cuentas-detalle">
					<p class="dato dato-cuenta">Id: <%= bankAccount.getId() %> </p>
					<p class="dato dato-cuenta">Numero: <%= bankAccount.getAccountNumber() %> </p>
					<p class="dato dato-cuenta">Tipo: <%= bankAccount.getBankAccountType().toString().equals("SAVINGSBANK") ? "Caja de ahorro" : "Cuenta corriente" %> </p>
					<p class="dato dato-cuenta">CBU: <%= bankAccount.getCBU() %> </p>
					<p class="dato dato-cuenta">Saldo: <%= bankAccount.getSaldo() %> </p>
					<p class="dato dato-cuenta">Ultima actualizacion: <%= bankAccount.getUpdatedAt() %> </p>
					<p class="dato dato-cuenta">Alta: <%= bankAccount.getCreatedAt() %> </p>
				</div>

			<% } %>
	</div>

</div>
