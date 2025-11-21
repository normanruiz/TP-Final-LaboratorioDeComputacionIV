package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import dominio.dao.BankAccountDAO;
import dominio.dao.BankMovementDAO;
import dominio.entity.BankAccount;
import dominio.entity.BankMovement;
import dominio.entity.Client;

@WebServlet("/ServletClientAccountMovements")
public class ServletClientAccountMovements extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletClientAccountMovements() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ServletClientAccountMovements - Ingresando a seccion clientes movimientos de cuentas");
		
		RequestDispatcher requestDispatcher = null;
		Client client;
		BankAccountDAO bankAccountDAO;
		ArrayList<BankAccount> bankAccountslist = null;
		int bankAccountid;
		ArrayList<BankMovement> bankMovementslist = null;
		BankMovementDAO bankMovementDAO;
		
		try {
			
			client = new Client((Client)request.getSession().getAttribute("client"));
			bankAccountDAO = new BankAccountDAO();
			bankAccountslist = bankAccountDAO.ListWithClientId(client.getId()); 
			request.getSession().setAttribute("bankAccountslist", bankAccountslist);
			
			if(request.getParameter("inputBankAccountSelected") != null ) {
				
				bankAccountid = Integer.parseInt(request.getParameter("idBankAccountSelected"));
				bankMovementDAO = new BankMovementDAO();
				bankMovementslist = bankMovementDAO.List(client.getId(), bankAccountid);
				request.getSession().setAttribute("bankMovementslist", bankMovementslist);
			}
			
			requestDispatcher = request.getRequestDispatcher("/home.jsp");		
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("ServletClientAccountMovements - Fallo algo... mira el log...");
			e.printStackTrace();
		}
	}

}
