
<% if (session.getAttribute("profile") == "admin") { %>
	<div class="contenedor-area-trabajo">
		<div class="contenedor-area-trabajo-titulo"> 
			<p class="area-trabajo-titulo"> AREA: <%= session.getAttribute("workAreaLabel") %> </p> 
		</div>
		<% if (session.getAttribute("workArea") == "adm-abm-client") {%>
			<jsp:include page="admAbmClients.jsp" />
		<% } %>
		<% if (session.getAttribute("workArea") == "adm-abm-bank-accounts") {%>
			<jsp:include page="admAbmBankAccounts.jsp" />
		<% } %>
		<% if (session.getAttribute("workArea") == "adm-abm-bank-loans") {%>
			<jsp:include page="admAbmBankLoans.jsp" />
		<% } %>
		<% if (session.getAttribute("workArea") == "adm-reports") {%>
			<jsp:include page="admReports.jsp" />
		<% } %>
	</div>
<% } else { %>
	<div class="contenedor-area-trabajo">
		<div class="contenedor-area-trabajo-titulo"> 
			<p class="area-trabajo-titulo"> AREA: <%= session.getAttribute("workAreaLabel") %> </p> 
		</div>
		<% if (session.getAttribute("workArea") == "clnt-account-movements") {%>
			<jsp:include page="clntAccountMovements.jsp" />
		<% } %>
		<% if (session.getAttribute("workArea") == "clnt-transfers") {%>
			<jsp:include page="clntTransfers.jsp" />
		<% } %>
		<% if (session.getAttribute("workArea") == "clnt-bank-loans") {%>
			<jsp:include page="clntBankLoans.jsp" />
		<% } %>
		<% if (session.getAttribute("workArea") == "clnt-payment-of-bank-loans") {%>
			<jsp:include page="clntPaymentBankLoans.jsp" />
		<% } %>
		<% if (session.getAttribute("workArea") == "clnt-personal-information") {%>
			<jsp:include page="clntPersonalInformation.jsp" />
		<% } %>
	</div>
<% } %>