
<div class="contenedor-barra-estado">
	<div class="container-footer-usuario"> <span> { USUARIO: <%= session.getAttribute("user") %> } </span> </div>
	<div class="container-footer-boton">
		<form method="get" action="ServletLogin" class="container-footer-form">
			<input type="submit" class="input-disconect" name="inputDesconectarse" value="Desconectarse"></input>
		</form>
	</div>
</div>