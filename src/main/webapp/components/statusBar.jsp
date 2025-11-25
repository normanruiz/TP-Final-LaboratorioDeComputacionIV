
<div class="contenedor-barra-estado">
	<div class="contenedor-datos-usuario"> 
		<p class="datos-usuario"> USUARIO: <%= session.getAttribute("user") %> - Inicio de session: <%= session.getAttribute("loginTime") %> </p> 
	</div>
	<div class="contenedor-boton-desconectar">
		<form method="get" action="ServletLogin" class="form-boton-desconectar">
			<input type="submit" class="boton-desconectar" name="inputDesconectarse" value="Desconectarse"></input>
		</form>
	</div>
</div>