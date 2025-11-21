<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Portal Bancario UTN - Iniciar Sesión</title>
		<link rel="stylesheet" href="css/login.css">
	</head>
	<body>
		<div class="container">
			<div class="item"></div>
			<div class="item"></div>
			<div class="item">
				<div class="login-container">
					<h2>Iniciar Sesión</h2>
					<form method="post" action="ServletLogin" >
						<div class="form-group">
							<label for="username">Usuario</label>
							<input type="text" name="inputUsr" placeholder="Tu usuario" required>
						</div>
						<div class="form-group">
							<label for="password">Contraseña</label>
							<input type="password" name="inputPwd" placeholder="Tu contraseña" required>
						</div>
						<input type="submit" class="login-btn" name="inputIngresar"></input>
						<div class="extra-links">
						<a href="#">¿Olvidaste tu contraseña?</a>
					</div>
					</form>
				</div>
			</div>
			<div class="item"></div>
			<div class="item"></div>
		</div>
	</body>
</html>