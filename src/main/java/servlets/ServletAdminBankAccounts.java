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
import dominio.entity.Enums.BANKACCOUNTTYPE;
import dominio.dao.ClientDAO;
import dominio.entity.Client;


@WebServlet("/ServletAdminBankAccounts")
public class ServletAdminBankAccounts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAdminBankAccounts() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClientDAO clientDAO;
		BankAccountDAO bankAccountDAO;
		RequestDispatcher requestDispatcher = null;
		
		try {
			
			System.out.println("ServletAdminABMBankAccounts - Ingresando a seccion administracion de cuentas bancarias");
			clientDAO = new ClientDAO();
			bankAccountDAO = new BankAccountDAO();
			
			if(request.getParameter("inputBankAccountsAdd") != null ) {
				int clientId = Integer.parseInt(request.getParameter("idClientBankAccountsAdd"));
				BANKACCOUNTTYPE type = request.getParameter("bankAccountTypeInput").equals("SAVINGSBANK") ? BANKACCOUNTTYPE.SAVINGSBANK : BANKACCOUNTTYPE.CURRENTACCOUNT;
				bankAccountDAO.Create( clientId, type);
			}
			
			ArrayList<Client> clientsList = clientDAO.Listar();
			request.getSession().setAttribute("clientsList", clientsList);			
			
			requestDispatcher = request.getRequestDispatcher("/home.jsp");		
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("ServletAdminBankAccounts - Fallo algo... mira el log...");
			e.printStackTrace();
		}
	}

}
