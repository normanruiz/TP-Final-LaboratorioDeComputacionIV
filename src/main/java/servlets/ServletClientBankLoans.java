package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import dominio.dao.BankAccountDAO;
import dominio.dao.BankLoansDAO;
import dominio.entity.BankAccount;
import dominio.entity.BankLoans;
import dominio.entity.Client;

@WebServlet("/ServletClientBankLoans")
public class ServletClientBankLoans extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletClientBankLoans() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ServletClientBankLoans - Ingresando a seccion clientes solicitud de prestamos");
		
		RequestDispatcher requestDispatcher = null;
		Client client;
		BankLoans bankLoans;
		BankAccount bankAccount;
		BankLoansDAO bankLoansDAO;
		ArrayList<BankAccount> bankAccountList;
		BankAccountDAO bankAccountDAO;
		ArrayList<BankLoans> bankLoansList;
		
		try {
			
			client = new Client((Client)request.getSession().getAttribute("client"));
			
			// solicitar pretamo
			
			if(request.getParameter("inputApplyBankLoansSave") != null ) {
				bankLoans = new BankLoans();
				bankLoans.setRequestedAmount( new BigDecimal((request.getParameter("inputRequestedAmount"))));
				bankLoans.setQuotas( Integer.parseInt(request.getParameter("inputQuotas")));
				bankAccount = new BankAccount();
				bankAccount.setId( Integer.valueOf(request.getParameter("inputAccountsBank")));
				bankLoans.setBankAccount(bankAccount);
				bankLoansDAO = new BankLoansDAO();
				bankLoansDAO.Create(client.getId(), bankLoans);
				request.getSession().setAttribute("applyBankLoansOperation", "BankLoansList");
			}
			
			if(request.getParameter("inputApplyBankLoansCancel") != null ) {
				request.getSession().setAttribute("applyBankLoansOperation", "BankLoansList");
				request.getSession().removeAttribute("bankAccountList");
			}
			
			// Listado(pantalla general)
			
			if(request.getParameter("inputApplyBankLoans") != null ) {
				
				request.getSession().setAttribute("applyBankLoansOperation", "applyBankLoans");
				
				bankAccountDAO = new BankAccountDAO();
				bankAccountList = bankAccountDAO.ListWithClientId(client.getId());
				request.getSession().setAttribute("bankAccountList", bankAccountList);

			} else {
				
				request.getSession().setAttribute("applyBankLoansOperation", "BankLoansList");
				request.getSession().removeAttribute("bankAccountList");
			}
			
			bankLoansDAO = new BankLoansDAO();
			bankLoansList = bankLoansDAO.ListWithClientId(client.getId());
			request.getSession().setAttribute("bankLoansList", bankLoansList);
		
			requestDispatcher = request.getRequestDispatcher("/home.jsp");
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("ServletClientBankLoans - Fallo algo... mira el log...");
			e.printStackTrace();
		}
		
	}

}
