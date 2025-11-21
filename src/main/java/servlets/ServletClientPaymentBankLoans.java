package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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
		
		try {
			
			requestDispatcher = request.getRequestDispatcher("/home.jsp");		
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("ServletClientPaymentBankLoans - Fallo algo... mira el log...");
			e.printStackTrace();
		}
		
	}

}
