package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dominio.entity.Client;

/**
 * Servlet implementation class ServletPortalClient
 */
@WebServlet("/ServletHomeClient")
public class ServletHomeClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletHomeClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ServletHomeClient - Ingresando a seccion de administracion para clientes");
		
		Client client;
		
		RequestDispatcher requestDispatcher;

		try {
			
			if ( request.getSession().getAttribute("client") == null ) {
				requestDispatcher = request.getRequestDispatcher("/login.jsp");	
				requestDispatcher.forward(request, response);
			}
		
			client = new Client((Client)request.getSession().getAttribute("client"));
			request.getSession().setAttribute("user", client.getFullName());
			
			
			if(request.getParameter("input-abm-account-movements") != null ) {
				request.getSession().setAttribute("workArea", "clnt-account-movements");
				request.getSession().setAttribute("workAreaLabel", "Movimientos de cuenta");
				requestDispatcher = request.getRequestDispatcher("/ServletClientAccountMovements");
			} else if(request.getParameter("input-abm-bank-transfers") != null) {
				request.getSession().setAttribute("workArea", "clnt-transfers");
				request.getSession().setAttribute("workAreaLabel", "Transferencias");
				requestDispatcher = request.getRequestDispatcher("/ServletClientTransfers");
			} else if(request.getParameter("input-abm-bankloans") != null) {
				request.getSession().setAttribute("workArea", "clnt-bank-loans");
				request.getSession().setAttribute("workAreaLabel", "Administracion de Prestamos");
				requestDispatcher = request.getRequestDispatcher("/ServletClientBankLoans");
			} else if(request.getParameter("input-abm-payment-bankloans") != null) {
				request.getSession().setAttribute("workArea", "clnt-payment-of-bank-loans");
				request.getSession().setAttribute("workAreaLabel", "Pago de Prestamos");
				requestDispatcher = request.getRequestDispatcher("/ServletClientPaymentBankLoans");
			} else if(request.getParameter("input-personal-information") != null) {
				request.getSession().setAttribute("workArea", "clnt-personal-information");
				request.getSession().setAttribute("workAreaLabel", "Informacion Personal");
				requestDispatcher = request.getRequestDispatcher("/ServletClientPersonalInformation");
			} else {
				request.getSession().setAttribute("workArea", "clnt-personal-information");
				request.getSession().setAttribute("workAreaLabel", "Informacion Personal");
				requestDispatcher = request.getRequestDispatcher("/ServletClientPersonalInformation");
			}
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("servletHomeClient - Fallo algo... mira el log...");
			e.printStackTrace();
		}
	}

}
