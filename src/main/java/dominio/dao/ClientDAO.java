package dominio.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dominio.entity.Client;
import dominio.entity.Enums.SEX;

public class ClientDAO implements IClientDAO{
	
	private IDatabaseConnection connection;

	public ClientDAO() {
		super();
		this.connection = new DatabaseConnectionMySQL();
	}

	@Override
	public boolean Create(Client newClient) {
		String insert;		
		try {
			System.out.println("Creando un nuevo cliente...");
			
			insert = "INSERT INTO TPFinalLaboratorioDeComputacionIV.clients\n"
					+ "(usr, pwd, profile, status, DNI, CUIL, name, lastName, sex, nationality, birthdate, address, city, state, email, phone, createdAt, updatedAt)\n"
					+ "VALUES(?, ?, 'CLIENT', 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW());";
			
			this.connection.Connect();
			this.connection.setPreparedStatement(insert);
			
			this.connection.setParameter(1, newClient.getUsr());
			this.connection.setParameter(2, newClient.getPwd());
			this.connection.setParameter(3, newClient.getDNI());
			this.connection.setParameter(4, newClient.getCUIL());
			this.connection.setParameter(5, newClient.getName());
			this.connection.setParameter(6, newClient.getLastName());
			System.out.println("Creando un nuevo cliente: " + newClient.getSex().toString());	
			this.connection.setParameter(7, newClient.getSex().toString());
			this.connection.setParameter(8, newClient.getNationality());
			this.connection.setParameter(9, newClient.getBirthdate());
			this.connection.setParameter(10, newClient.getAddress());
			this.connection.setParameter(11, newClient.getCity());
			this.connection.setParameter(12, newClient.getState());
			this.connection.setParameter(13, newClient.geteMail());
			this.connection.setParameter(14, newClient.getPhone());
			
			
			int result = this.connection.executeUpdate();
			if (result == 1) {
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			System.out.println("Falló al intentar crear el cliente...");
			System.out.println(e.getMessage());
			return false;
		} finally {
			this.connection.Disconnect();
		}
	}

	@Override
	public boolean Update(Client modifyClient) {
		String update;		
		try {
			System.out.println("Actualizando un cliente existente...");
			
			update = "UPDATE TPFinalLaboratorioDeComputacionIV.clients\n"
					+ "SET usr=?,\n"
					+ "	pwd=?,\n"
					+ "	status=?,\n"
					+ "	DNI=?,\n"
					+ "	CUIL=?,\n"
					+ "	name=?,\n"
					+ "	lastName=?,\n"
					+ "	sex=?,\n"
					+ "	nationality=?,\n"
					+ "	birthdate=?,\n"
					+ "	address=?,\n"
					+ "	city=?,\n"
					+ "	state=?,\n"
					+ "	email=?,\n"
					+ "	phone=?,\n"
					+ "	updatedAt=NOW()\n"
					+ "WHERE id=?;";
			
			this.connection.Connect();
			this.connection.setPreparedStatement(update);
			
			this.connection.setParameter(1, modifyClient.getUsr());
			this.connection.setParameter(2, modifyClient.getPwd());
			this.connection.setParameter(3, modifyClient.getStatus());
			this.connection.setParameter(4, modifyClient.getDNI());
			this.connection.setParameter(5, modifyClient.getCUIL());
			this.connection.setParameter(6, modifyClient.getName());
			this.connection.setParameter(7, modifyClient.getLastName());
			this.connection.setParameter(8, modifyClient.getSex().toString());
			this.connection.setParameter(9, modifyClient.getNationality());
			this.connection.setParameter(10, modifyClient.getBirthdate());
			this.connection.setParameter(11, modifyClient.getAddress());
			this.connection.setParameter(12, modifyClient.getCity());
			this.connection.setParameter(13, modifyClient.getState());
			this.connection.setParameter(14, modifyClient.geteMail());
			this.connection.setParameter(15, modifyClient.getPhone());
			this.connection.setParameter(16, modifyClient.getId());
			
			int result = this.connection.executeUpdate();
			if (result == 1) {
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			System.out.println("Falló al intentar actualizar el cliente...");
			System.out.println(e.getMessage());
			return false;
		} finally {
			this.connection.Disconnect();
		}
	}

	@Override
	public boolean Delete(int id) {
		String query;		
		try {
			System.out.println("Eliminando cliente por id...");
			query = "UPDATE TPFinalLaboratorioDeComputacionIV.clients SET status=0, updatedAt=NOW() WHERE id = " + id + ";";
			this.connection.Connect();
			
			int result = this.connection.executeUpdate(query);
			if (result == 1) {
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			System.out.println("Falló al eliminar el cliente por id...");
			System.out.println(e.getMessage());
			return false;
		} finally {
			this.connection.Disconnect();
		}
	}

	@Override
	public ArrayList<Client> Listar() {
		String query;
		Client client;
		BankAccountDAO bankAccountDAO;
		ArrayList<Client> clientsList = new ArrayList<Client>();
		
		try {
			System.out.println("Listando clientes...");
			query = "SELECT id, usr, pwd, status, DNI, CUIL, name, lastName, sex, nationality, birthdate, address, city, state, email, phone, createdAt, updatedAt\n"
					+ "FROM TPFinalLaboratorioDeComputacionIV.clients;";
			this.connection.Connect();
			ResultSet result = this.connection.executeQuery(query);
			while(result.next()) {
		
				client = new Client();
				client.setId(result.getInt("id"));
				client.setUsr(result.getString("usr"));
				client.setPwd(result.getString("pwd"));
				client.setStatus(result.getBoolean("status"));
				client.setDNI(result.getString("DNI"));
				client.setCUIL(result.getString("CUIL"));
				client.setName(result.getString("name"));
				client.setLastName(result.getString("lastName"));
				client.setSex(result.getString("sex").equals("MALE") ? SEX.MALE : SEX.FEMALE );
				client.setBirthdate(result.getDate("birthdate").toLocalDate());
				client.setNationality(result.getString("nationality"));
				client.setAddress(result.getString("address"));
				client.setCity(result.getString("city"));
				client.setState(result.getString("state"));
				client.seteMail(result.getString("email"));
				client.setPhone(result.getString("phone"));
				bankAccountDAO = new BankAccountDAO();
				client.setBankAccounts(bankAccountDAO.ListWithClientId(client.getId()));
				client.setCreatedAt(result.getTimestamp("createdAt").toLocalDateTime());
				client.setUpdatedAt(result.getTimestamp("updatedAt").toLocalDateTime());
				clientsList.add(client);
				
			}
			return clientsList;
			
		} catch (Exception e) {
			System.out.println("Falló al lista los cliente ...");
			System.out.println(e.getMessage());
			return null;
		} finally {
			this.connection.Disconnect();
		}
	}

	@Override
	public Client FindWithId(int id) {
		String query;
		Client client;
		BankAccountDAO bankAccountDAO;
		try {
			System.out.println("Buscando cliente por id...");
			query = "SELECT id, usr, pwd, status, DNI, CUIL, name, lastName, sex, nationality, birthdate, address, city, state, email, phone, createdAt, updatedAt\n"
					+ "FROM TPFinalLaboratorioDeComputacionIV.clients\n"
					+ "WHERE id = '" + id + "';";
			this.connection.Connect();
			ResultSet result = this.connection.executeQuery(query);
			if (result.next()) {
				
				client = new Client();
				client.setId(result.getInt("id"));
				client.setUsr(result.getString("usr"));
				client.setPwd(result.getString("pwd"));
				client.setStatus(result.getBoolean("status"));
				client.setDNI(result.getString("DNI"));
				client.setCUIL(result.getString("CUIL"));
				client.setName(result.getString("name"));
				client.setLastName(result.getString("lastName"));
				client.setSex(result.getString("sex").equals("MALE") ? SEX.MALE : SEX.FEMALE );
				client.setBirthdate(result.getDate("birthdate").toLocalDate());
				client.setNationality(result.getString("nationality"));
				client.setAddress(result.getString("address"));
				client.setCity(result.getString("city"));
				client.setState(result.getString("state"));
				client.seteMail(result.getString("email"));
				client.setPhone(result.getString("phone"));
				bankAccountDAO = new BankAccountDAO();
				client.setBankAccounts(bankAccountDAO.ListWithClientId(client.getId()));
				client.setCreatedAt(result.getTimestamp("createdAt").toLocalDateTime());
				client.setUpdatedAt(result.getTimestamp("updatedAt").toLocalDateTime());
			} else {
				client = null;
			}
			return client;
			
		} catch (Exception e) {
			System.out.println("Falló al buscar el cliente por id...");
			System.out.println(e.getMessage());
			return null;
		} finally {
			this.connection.Disconnect();
		}
	}

	@Override
	public Client FindWithDNI(String client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client FindWithUsr(String usr) {
		String query;
		Client client;
		
		try {
			System.out.println("Buscando cliente por usuario...");
			query = "SELECT id, usr, pwd, status, DNI, CUIL, name, lastName, sex, nationality, birthdate, address, city, state, email, phone, createdAt, updatedAt\n"
					+ "FROM TPFinalLaboratorioDeComputacionIV.clients\n"
					+ "WHERE usr = '" + usr + "' and status = 1;";
			this.connection.Connect();
			ResultSet result = this.connection.executeQuery(query);
			if (result.next()) {
				
				client = new Client();
				client.setId(result.getInt("id"));
				client.setUsr(result.getString("usr"));
				client.setPwd(result.getString("pwd"));
				client.setStatus(result.getBoolean("status"));
				client.setDNI(result.getString("DNI"));
				client.setCUIL(result.getString("CUIL"));
				client.setName(result.getString("name"));
				client.setLastName(result.getString("lastName"));
				client.setSex(result.getString("sex").equals("MALE") ? SEX.MALE : SEX.FEMALE );
				client.setBirthdate(result.getDate("birthdate").toLocalDate());
				client.setNationality(result.getString("nationality"));
				client.setAddress(result.getString("address"));
				client.setCity(result.getString("city"));
				client.setState(result.getString("state"));
				client.seteMail(result.getString("email"));
				client.setPhone(result.getString("phone"));			
				client.setCreatedAt(result.getTimestamp("createdAt").toLocalDateTime());
				client.setUpdatedAt(result.getTimestamp("updatedAt").toLocalDateTime());
			} else {
				client = null;
			}
			return client;
			
		} catch (Exception e) {
			System.out.println("Falló al buscar el cliente por usuario...");
			System.out.println(e.getMessage());
			return null;
		} finally {
			this.connection.Disconnect();
		}
	}
	
	

}
