package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import dominio.dao.BankLoansDAO;
import dominio.entity.BankLoans;


@WebServlet("/ServletAdminBankLoans")
public class ServletAdminBankLoans extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAdminBankLoans() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ServletAdminBankLoans - Ingresando a seccion administracion de prestamos bancarios");
		
		RequestDispatcher requestDispatcher = null;
		
		BankLoansDAO bankLoansDAO;
		ArrayList<BankLoans> bankLoansList = new ArrayList<BankLoans>();
		
		try {
			
			if(request.getParameter("inputBankLoansAuthorized") != null ) {
				
				int idBankLoansAuthorized = Integer.parseInt(request.getParameter("inputBankLoansAuthorized"));
				bankLoansDAO = new BankLoansDAO();
				bankLoansDAO.Authorized(idBankLoansAuthorized);
				request.getSession().setAttribute("bankLoansList", bankLoansList);

			}
			
			if(request.getParameter("inputBankLoansRefused") != null ) {
				
				int idBankLoansRefused = Integer.parseInt(request.getParameter("idBankLoansRefused"));
				bankLoansDAO = new BankLoansDAO();
				bankLoansDAO.Refused(idBankLoansRefused);
				request.getSession().setAttribute("bankLoansList", bankLoansList);

			}
			
			if(request.getParameter("input-abm-bankloans") != null ) {
				
				bankLoansDAO = new BankLoansDAO();
				bankLoansList = bankLoansDAO.ListPending();
				request.getSession().setAttribute("bankLoansList", bankLoansList);

			}
			
			requestDispatcher = request.getRequestDispatcher("/home.jsp");		
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("ServletAdminBankLoans - Fallo algo... mira el log...");
			e.printStackTrace();
		}
	}

}
