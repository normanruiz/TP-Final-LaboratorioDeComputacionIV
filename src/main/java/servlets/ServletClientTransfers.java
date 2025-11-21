package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ServletClientTransfers")
public class ServletClientTransfers extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletClientTransfers() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ServletClientTransfers - Ingresando a seccion clientes transferencias bancarias");
		
		RequestDispatcher requestDispatcher = null;
		
		try {
			
			requestDispatcher = request.getRequestDispatcher("/home.jsp");		
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("ServletClientTransfers - Fallo algo... mira el log...");
			e.printStackTrace();
		}
	}

}
