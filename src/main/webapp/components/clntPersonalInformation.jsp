<%@ page import="dominio.entity.Client" %>

<% Client clientDetail = (Client)session.getAttribute("client"); %>

<div class="contenedor-informacion-personal">

	<div class="contenedor-informacion-personal-titulo"> 
		<span> AREA: <%= session.getAttribute("workAreaLabel") %> </span> 
	</div>

	<div>
	
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
		
	</div>

</div>
