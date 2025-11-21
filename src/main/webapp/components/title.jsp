
<% if (session.getAttribute("profile") == "admin") { %>
	<div class="contenedor-titulo">
		<p class="title">SECCION ADMINISTRADORES</p>
	</div>
<% } else { %>
	<div class="contenedor-titulo">
		<p class="title">SECCION CLIENTES</p>
	</div>
<% } %>
