<%@ page import="dominio.entity.Client" %>

<% Client clientDetail = (Client)session.getAttribute("clientDetail"); %>

<div>

	<div>
		<h1>Datos de Cliente</h1>
	</div>

	<div>
		<p>Id: <%= clientDetail.getId() %> </p>
		<p>Usuario: <%= clientDetail.getUsr() %> </p>
		<p>Contraseña: <%= clientDetail.getPwd() %> </p>
		<p>Estado: <%= clientDetail.getStatus() %> </p>		
	</div>
	
	<div>
		<p>DNI: <%= clientDetail.getDNI() %> </p>
		<p>CUIL: <%= clientDetail.getCUIL() %> </p>
		<p>Nombre: <%= clientDetail.getName() %> </p>
		<p>Apellido: <%= clientDetail.getLastName() %> </p>
		<p>Genero: <%= clientDetail.getSex() %> </p>
		<p>Fecha de nacimiento: <%= clientDetail.getBirthdate() %> </p>
	</div>
	
	<div>
		<p>Nacionalidad: <%= clientDetail.getNationality() %> </p>
		<p>Direccion: <%= clientDetail.getAddress() %> </p>
		<p>Ciudad: <%= clientDetail.getCity() %> </p>
		<p>Estado: <%= clientDetail.getState() %> </p>
		<p>Telefono: <%= clientDetail.getPhone() %> </p>
		<p>Correo electronico: <%= clientDetail.geteMail() %> </p>
	</div>
	
	<div>
		<p>Fecha de alta: <%= clientDetail.getCreatedAt() %> </p>
		<p>Fecha de ultima actualizacion: <%= clientDetail.getUpdatedAt() %> </p>
	</div>
	
	<div>
			<form method="post" action="ServletAdminClients" class="container-footer-form">
				<input type="submit" class="input-disconect" name="inputClientDetailBack" value="Volver"></input>
			</form>
	</div>
	
</div>
