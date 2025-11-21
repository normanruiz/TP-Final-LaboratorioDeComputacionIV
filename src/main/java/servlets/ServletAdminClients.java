package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import dominio.dao.ClientDAO;
import dominio.entity.Client;
import dominio.entity.Enums.SEX;
import java.time.LocalDate;

@WebServlet("/ServletAdminClients")
public class ServletAdminClients extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ServletAdminClients() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client client;
		Client clientNew;
		Client clientModify;
		ClientDAO clientDAO;
		RequestDispatcher requestDispatcher = null;
		
		try {
			
			System.out.println("ServletAdminABMClients - Ingresando a seccion administracion de clientes");
			clientDAO = new ClientDAO();
			
			if(request.getParameter("inputClientDetailBack") != null ) {
				request.getSession().setAttribute("abmClientOperation", "list");
			}
			
			if(request.getParameter("inputClientNewModifyCancel") != null ) {
				request.getSession().setAttribute("abmClientOperation", "list");
			}
			
			if(request.getParameter("inputClientNewSave") != null ) {
				clientNew = new Client();
				
				clientNew.setUsr(request.getParameter("usrClientInput"));
				clientNew.setPwd(request.getParameter("pwdClientInput"));

				clientNew.setDNI(request.getParameter("dniClientInput"));
				clientNew.setCUIL(request.getParameter("cuilClientInput"));
				clientNew.setName(request.getParameter("nameClientInput"));
				clientNew.setLastName(request.getParameter("lastNameClientInput"));
				clientNew.setSex(request.getParameter("sexClientInput").equals("MALE") ? SEX.MALE : SEX.FEMALE );
				clientNew.setBirthdate(LocalDate.parse(request.getParameter("birthdateClientInput")));
				
				clientNew.setNationality(request.getParameter("nationalityClientInput"));
				clientNew.setAddress(request.getParameter("addressClientInput"));
				clientNew.setCity(request.getParameter("cityClientInput"));
				clientNew.setState(request.getParameter("stateClientInput"));
				clientNew.setPhone(request.getParameter("phoneClientInput"));
				clientNew.seteMail(request.getParameter("emailClientInput"));
				
				System.out.println("ServletAdminClients - Antes de llamar a clientDAO: " + clientNew.getSex());
				clientDAO.Create(clientNew);
				ArrayList<Client> clientsList = clientDAO.Listar();
				request.getSession().setAttribute("clientsList", clientsList);			
				request.getSession().setAttribute("abmClientOperation", "list");
			}
			
			if(request.getParameter("inputClientModifySave") != null ) {
				clientModify = new Client((Client)request.getSession().getAttribute("clientModify"));
				
				clientModify.setUsr(request.getParameter("usrClientInput"));
				clientModify.setPwd(request.getParameter("pwdClientInput"));
				clientModify.setStatus(request.getParameter("statusClientInput").equals("true") ? true : false);

				clientModify.setDNI(request.getParameter("dniClientInput"));
				clientModify.setCUIL(request.getParameter("cuilClientInput"));
				clientModify.setName(request.getParameter("nameClientInput"));
				clientModify.setLastName(request.getParameter("lastNameClientInput"));
				clientModify.setSex(request.getParameter("sexClientInput").equals("MALE") ? SEX.MALE : SEX.FEMALE );
				clientModify.setBirthdate(LocalDate.parse(request.getParameter("birthdateClientInput")));
				
				clientModify.setNationality(request.getParameter("nationalityClientInput"));
				clientModify.setAddress(request.getParameter("addressClientInput"));
				clientModify.setCity(request.getParameter("cityClientInput"));
				clientModify.setState(request.getParameter("stateClientInput"));
				clientModify.setPhone(request.getParameter("phoneClientInput"));
				clientModify.seteMail(request.getParameter("emailClientInput"));
				
				clientDAO.Update(clientModify);
				request.getSession().setAttribute("abmClientOperation", "list");
			}
			
			if(request.getParameter("inputClientNew") != null ) {
				
				System.out.println("ServletAdminABMClients - Ingresando a la seccion alta de cliente");
				request.getSession().setAttribute("abmClientOperation", "new");
				
			} else if(request.getParameter("inputClientModify") != null) {
				
				System.out.println("ServletAdminABMClients - Ingresando a la seccion modificacion de cliente");
				request.getSession().setAttribute("abmClientOperation", "modify");
				int id = Integer.parseInt(request.getParameter("idClientModify"));
				client = clientDAO.FindWithId(id);
				request.getSession().setAttribute("clientModify", client);
				
			} else if(request.getParameter("inputClientDetail") != null) {
				
				System.out.println("ServletAdminABMClients - Ingresando a la seccion detalle de cliente");
				request.getSession().setAttribute("abmClientOperation", "detail");
				int id = Integer.parseInt(request.getParameter("idClientDetail"));
				client = clientDAO.FindWithId(id);
				request.getSession().setAttribute("clientDetail", client);
				
			} else if(request.getParameter("inputClientDelete") != null) {
				System.out.println("ServletAdminABMClients - Ingresando a la seccion eliminaciopn logica de cliente");
				request.getSession().setAttribute("abmClientOperation", "delete");
				int id = Integer.parseInt(request.getParameter("idClientDelete"));
				clientDAO.Delete(id);
				ArrayList<Client> clientsList = clientDAO.Listar();
				request.getSession().setAttribute("clientsList", clientsList);			
				request.getSession().setAttribute("abmClientOperation", "list");
			} else {
				
				ArrayList<Client> clientsList = clientDAO.Listar();
				request.getSession().setAttribute("clientsList", clientsList);			
				request.getSession().setAttribute("abmClientOperation", "list");
				
			}
			
			requestDispatcher = request.getRequestDispatcher("/home.jsp");		
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("ServletAdminABMClients - Fallo algo... mira el log...");
			e.printStackTrace();
		}
	}

}
