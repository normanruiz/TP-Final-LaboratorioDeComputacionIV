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
import dominio.dao.BankLoansDAO;
import dominio.dao.BankLoansPaymentsDAO;
import dominio.entity.BankAccount;
import dominio.entity.BankLoans;
import dominio.entity.Client;

@WebServlet("/ServletClientPaymentBankLoans")
public class ServletClientPaymentBankLoans extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletClientPaymentBankLoans() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ServletClientPaymentBankLoans - Ingresando a seccion clientes pagos de prestamos");
		
		RequestDispatcher requestDispatcher = null;
		Client client;
		BankLoansDAO bankLoansDAO;
		ArrayList<BankLoans> bankLoansAuthorizedList = null;
		ArrayList<BankAccount> bankAccountList;
		BankAccountDAO bankAccountDAO;
		BankLoansPaymentsDAO bankLoansPaymentsDAO; 
		
		try {
			
			client = new Client((Client)request.getSession().getAttribute("client"));
			
			if ( request.getParameter("inputBankLoansPay") != null ) {
				int idBankLoansPayments = Integer.parseInt(request.getParameter("idBankLoansPayments"));
				int idBankAccount = Integer.parseInt(request.getParameter("inputAccountsBank"));
				bankLoansPaymentsDAO = new BankLoansPaymentsDAO();
				bankLoansPaymentsDAO.Pay(idBankLoansPayments, idBankAccount);
			}
			
			bankLoansDAO = new BankLoansDAO();
			bankLoansAuthorizedList = bankLoansDAO.ListAuthorized(client.getId());
			request.getSession().setAttribute("bankLoansAuthorizedList", bankLoansAuthorizedList);
			bankAccountDAO = new BankAccountDAO();
			bankAccountList = bankAccountDAO.ListWithClientId(client.getId());
			request.getSession().setAttribute("bankAccountList", bankAccountList);
			
			
			requestDispatcher = request.getRequestDispatcher("/home.jsp");		
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("ServletClientPaymentBankLoans - Fallo algo... mira el log...");
			e.printStackTrace();
		}
		
	}

}
