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
import dominio.entity.BankAccount;
import dominio.entity.Client;

@WebServlet("/ServletClientPersonalInformation")
public class ServletClientPersonalInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletClientPersonalInformation() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ServletClientPersonalInformation - Ingresando a seccion clientes informacion personal");
		
		RequestDispatcher requestDispatcher = null;
		Client client;
		ArrayList<BankAccount> bankAccountList;
		BankAccountDAO bankAccountDAO;

		try {
			
			client = new Client((Client)request.getSession().getAttribute("client")); 
			bankAccountDAO = new BankAccountDAO();
			bankAccountList = bankAccountDAO.ListWithClientId(client.getId());
			client.setBankAccounts(bankAccountList);
			request.getSession().setAttribute("client", client);
			
			requestDispatcher = request.getRequestDispatcher("/home.jsp");		
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("ServletClientPersonalInformation - Fallo algo... mira el log...");
			e.printStackTrace();
		}
		
	}

}
