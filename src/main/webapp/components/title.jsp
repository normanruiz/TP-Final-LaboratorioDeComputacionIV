
<% if (session.getAttribute("profile") == "admin") { %>
	<div class="contenedor-titulo">
		<p class="titulo">SECCION ADMINISTRADORES</p>
	</div>
<% } else { %>
	<div class="contenedor-titulo">
		<p class="titulo">SECCION CLIENTES</p>
	</div>
<% } %>
