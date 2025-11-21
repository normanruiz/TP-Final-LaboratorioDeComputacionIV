<%@ page import="dominio.entity.Client" %>

<div>

	<div>
		<h1> <%= session.getAttribute("abmClientOperation") == "new" ? "Alta" : "Modidificacion" %> de Cliente</h1>
	</div>

	<% if ( session.getAttribute("abmClientOperation") == "new" ) { %>
		<div>
			<form method="post" action="ServletAdminClients">
			
				<div>
					
					<label for="usrClientInput">Usuario</label>
					<input type="text" name="usrClientInput" placeholder="Ingrese el usuario">
					
					<label for="pwdClientInput">Contraseña</label>
					<input type="password" name="pwdClientInput" placeholder="Ingrese la contraseña">
  	
				</div>
		
				<div>
				
					<label for="dniClientInput">DNI</label>
					<input type="text" name="dniClientInput" placeholder="Ingrese el DNI">
									
					<label for="cuilClientInput">CUIL</label>
					<input type="text" name="cuilClientInput" placeholder="Ingrese el CUIL">
									
					<label for="nameClientInput">Nombre</label>
					<input type="text" name="nameClientInput" placeholder="Ingrese el nombre">
									
					<label for="lastNameClientInput">Apellido</label>
					<input type="text" name="lastNameClientInput" placeholder="Ingrese el apellido">
					
					<label for="sexClientInput">Genero</label>
					<input type="radio" id="male" name="sexClientInput" value="MALE" checked>
					<label for="male">Masculino</label>
					<input type="radio" id="female" name="sexClientInput" value="FEMALE">
					<label for="female">Femenino</label>

					<label for="birthdateClientInput">Fecha de nacimiento</label>
					<input type="date" name="birthdateClientInput">

				</div>
				
				<div>
				
					<label for="nationalityClientInput">Nacionalidad</label>
					<input type="text" name="nationalityClientInput" placeholder="Ingrese la nacionalidad">
					
					<label for="addressClientInput">Direccion</label>
					<input type="text" name="addressClientInput" placeholder="Ingrese la direccion">
					
					<label for="cityClientInput">Ciudad</label>
					<input type="text" name="cityClientInput" placeholder="Ingrese la ciudad">
					
					<label for="stateClientInput">Estado</label>
					<input type="text" name="stateClientInput" placeholder="Ingrese el estado">
					
					<label for="phoneClientInput">Telefono</label>
					<input type="text" name="phoneClientInput" placeholder="Ingrese el telefono">
					
					<label for="emailClientInput">Correo electronico</label>
					<input type="text" name="emailClientInput" placeholder="Ingrese el correo electronico">
			
				</div>
		
				<div>
					<input type="submit" class="input-disconect" name="inputClientNewModifyCancel" value="Cancelar"></input>
					<input type="submit" class="input-disconect" name="inputClientNewSave" value="Guardar"></input>
				</div>
	
			</form>
		</div>
	<% 
		} else { 
			Client clientModify = (Client)session.getAttribute("clientModify");
	%>
	
			<div>
			<form method="post" action="ServletAdminClients">
			
				<div>
					<label for="idClientInput">Id de cliente</label>
					<input type="text" name="idClientInput" disabled value="<%= clientModify.getId() %>">
					
					<label for="usrClientInput">Usuario</label>
					<input type="text" name="usrClientInput" placeholder="Ingrese el usuario" value="<%= clientModify.getUsr() %>">
					
					<label for="pwdClientInput">Contraseña</label>
					<input type="password" name="pwdClientInput" placeholder="Ingrese la contraseña" value="<%= clientModify.getLastName() %>">
					
					<label for="statusClientInput">Estado</label>
					<input type="radio" id="active" name="statusClientInput" value="true" <%= clientModify.getStatus() == true ? "checked" : "" %>>
					<label for="active">Activo</label>
					<input type="radio" id="inactive" name="statusClientInput" value="false" <%= clientModify.getStatus() == false ? "checked" : "" %>>
					<label for="inactive">Bloqueado</label>
  	
				</div>
		
				<div>
				
					<label for="dniClientInput">DNI</label>
					<input type="text" name="dniClientInput" placeholder="Ingrese el DNI" value="<%= clientModify.getDNI() %>">
									
					<label for="cuilClientInput">CUIL</label>
					<input type="text" name="cuilClientInput" placeholder="Ingrese el CUIL" value="<%= clientModify.getCUIL() %>">
									
					<label for="nameClientInput">Nombre</label>
					<input type="text" name="nameClientInput" placeholder="Ingrese el nombre" value="<%= clientModify.getName() %>">
									
					<label for="lastNameClientInput">Apellido</label>
					<input type="text" name="lastNameClientInput" placeholder="Ingrese el apellido" value="<%= clientModify.getLastName() %>">
					
					<label for="sexClientInput">Genero</label>
					<input type="radio" id="male" name="sexClientInput" value="MALE" <%= clientModify.getSex().toString().equals("MALE") ? "checked" : "" %>>
					<label for="male">Masculino</label>
					<input type="radio" id="female" name="sexClientInput" value="FEMALE" <%= clientModify.getSex().toString().equals("FEMALE") ? "checked" : "" %>>
					<label for="female">Femenino</label>

					<label for="birthdateClientInput">Fecha de nacimiento</label>
					<input type="date" name="birthdateClientInput" value="<%= clientModify.getBirthdate().toString() %>">
					
				</div>
				
				<div>
				
					<label for="nationalityClientInput">Nacionalidad</label>
					<input type="text" name="nationalityClientInput" placeholder="Ingrese la nacionalidad" value="<%= clientModify.getNationality() %>">
					
					<label for="addressClientInput">Direccion</label>
					<input type="text" name="addressClientInput" placeholder="Ingrese la direccion" value="<%= clientModify.getAddress() %>">
					
					<label for="cityClientInput">Ciudad</label>
					<input type="text" name="cityClientInput" placeholder="Ingrese la ciudad" value="<%= clientModify.getCity() %>">
					
					<label for="stateClientInput">Estado</label>
					<input type="text" name="stateClientInput" placeholder="Ingrese el estado" value="<%= clientModify.getState() %>">
					
					<label for="phoneClientInput">Telefono</label>
					<input type="text" name="phoneClientInput" placeholder="Ingrese el telefono" value="<%= clientModify.getPhone() %>">
					
					<label for="emailClientInput">Correo electronico</label>
					<input type="text" name="emailClientInput" placeholder="Ingrese el correo electronico" value="<%= clientModify.geteMail() %>">
			
				</div>
				
				<div>
				
					<label for="createdAtClientInput">Fecha de creacion</label>
					<input type="text" name="createdAtClientInput" value="<%= clientModify.getCreatedAt().toString() %>" disabled>
					
					<label for="updatedAtClientInput">Fecha de ultima actualizacion</label>
					<input type="text" name="updatedAtClientInput" value="<%= clientModify.getUpdatedAt().toString() %>" disabled>
					
				</div>			
		
				<div>
					<input type="submit" class="input-disconect" name="inputClientNewModifyCancel" value="Cancelar"></input>
					<input type="submit" class="input-disconect" name="inputClientModifySave" value="Guardar"></input>
				</div>
	
			</form>
		</div>
	
	<% } %>
	
</div>