package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dominio.dao.AdminDAO;
import dominio.dao.ClientDAO;
import dominio.entity.Admin;
import dominio.entity.Client;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher;
		try {
			if(request.getParameter("inputDesconectarse") != null) {
				request.getSession().removeAttribute("user");
				request.getSession().removeAttribute("profile");
				request.getSession().removeAttribute("workArea");
				request.getSession().removeAttribute("workAreaLabel");
				requestDispatcher = request.getRequestDispatcher("/login.jsp");	
				requestDispatcher.forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Admin admin;
		Client client;
		AdminDAO adminDAO;
		ClientDAO clientDAO;
		
		RequestDispatcher requestDispatcher;
		
		try {
			System.out.println("ServletLogin - Validando credenciales");
			if(request.getParameter("inputIngresar") != null) {
				adminDAO = new AdminDAO();
				admin = adminDAO.FindWithUsr(request.getParameter("inputUsr"));
				if (admin != null) {
					System.out.println("ServletLogin - Administrador detectado");
					if (admin.getPwd().equals(request.getParameter("inputPwd"))) {
						request.getSession().setAttribute("admin", admin);
						request.getSession().setAttribute("profile", "admin");
						System.out.println("ServletLogin - Credenciales correctas");
						requestDispatcher = request.getRequestDispatcher("/ServletHomeAdmin");
					}  else {
						System.out.println("ServletLogin - Contraseña invalida");
						requestDispatcher = request.getRequestDispatcher("/login.jsp");
					}
				} else {
					clientDAO = new ClientDAO();
					client = clientDAO.FindWithUsr(request.getParameter("inputUsr"));
					if (client != null) {
				
						System.out.println("ServletLogin - Cliente detectado");
						if (client.getPwd().equals(request.getParameter("inputPwd"))) {
							request.getSession().setAttribute("client", client);
							request.getSession().setAttribute("profile", "client");
							System.out.println("ServletLogin - Credenciales correctas");
							requestDispatcher = request.getRequestDispatcher("/ServletHomeClient");
						}  else {
							System.out.println("ServletLogin - Contraseña invalida");
							requestDispatcher = request.getRequestDispatcher("/login.jsp");
						}
					} else {
						System.out.println("ServletLogin - Usuario invalido");
						requestDispatcher = request.getRequestDispatcher("/login.jsp");
					}
				}
			} else {
				requestDispatcher = request.getRequestDispatcher("/login.jsp");
			}
			
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("servletLogin - Fallo algo... mira el log...");
			e.printStackTrace();
		}
	}

}
