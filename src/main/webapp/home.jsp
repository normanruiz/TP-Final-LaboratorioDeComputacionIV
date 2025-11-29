<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Portal Bancario UTN</title>
		<link rel="stylesheet" href="css/home.css">
		<link rel="stylesheet" href="components/css/title.css">
		<link rel="stylesheet" href="components/css/menuBar.css">
		<link rel="stylesheet" href="components/css/workArea.css">
		<link rel="stylesheet" href="components/css/statusBar.css">
		<link rel="stylesheet" href="components/css/clntPersonalInformation.css">
		<link rel="stylesheet" href="components/css/clntBankLoans.css">
		<link rel="stylesheet" href="components/css/clntAccountMovements.css">
		<link rel="stylesheet" href="components/css/clntPaymentBankLoans.css">
		<link rel="stylesheet" href="components/css/admAbmBankLoans.css">
		<link rel="stylesheet" href="components/css/admAbmBankAccounts.css">
	</head>
	<body>
		<% 
			if (session.getAttribute("user") == null) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");	
				requestDispatcher.forward(request, response);
			}
		%>
		<div class="contenedor-general">
			
			<jsp:include page="components/title.jsp" />
			
			<jsp:include page="components/menuBar.jsp" />
			
			<jsp:include page="components/workArea.jsp" />
			
			<jsp:include page="components/statusBar.jsp" />
			
		</div>
	</body>
</html>




