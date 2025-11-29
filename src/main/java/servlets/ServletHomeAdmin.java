package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dominio.entity.Admin;

@WebServlet("/ServletHomeAdmin")
public class ServletHomeAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletHomeAdmin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ServletHomeAdmin - Ingresando a seccion de administracion para usuarios del banco");

		Admin admin;

		RequestDispatcher requestDispatcher = null;

		try {
			
			if ( request.getSession().getAttribute("admin") == null ) {
				requestDispatcher = request.getRequestDispatcher("/login.jsp");	
				requestDispatcher.forward(request, response);
			}
			
			admin = new Admin((Admin)request.getSession().getAttribute("admin"));
			request.getSession().setAttribute("user", admin.getFullName());
			
			if(request.getParameter("input-abm-clients") != null ) {
				request.getSession().setAttribute("workArea", "adm-abm-client");
				request.getSession().setAttribute("workAreaLabel", "Administracion de clientes");
				requestDispatcher = request.getRequestDispatcher("/ServletAdminClients");
			} else if(request.getParameter("input-abm-bankaccounts") != null) {
				request.getSession().setAttribute("workArea", "adm-abm-bank-accounts");
				request.getSession().setAttribute("workAreaLabel", "Administracion de cuentas");
				requestDispatcher = request.getRequestDispatcher("/ServletAdminBankAccounts");
			} else if(request.getParameter("input-abm-bankloans") != null) {
				request.getSession().setAttribute("workArea", "adm-abm-bank-loans");
				request.getSession().setAttribute("workAreaLabel", "Administracion de prestamos");
				requestDispatcher = request.getRequestDispatcher("/ServletAdminBankLoans");
			} else if(request.getParameter("input-reports") != null) {
				request.getSession().setAttribute("workArea", "adm-reports");
				request.getSession().setAttribute("workAreaLabel", "Reportes");
				requestDispatcher = request.getRequestDispatcher("/ServletAdminReports");
			} else {
				requestDispatcher = request.getRequestDispatcher("/home.jsp");					
			}
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("servletHomeAdmin - Fallo algo... mira el log...");
			e.printStackTrace();
		}
	}

}
