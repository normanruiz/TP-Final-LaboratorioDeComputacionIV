package dominio.dao;

import java.sql.ResultSet;
import java.util.Set;

import dominio.entity.Admin;

public class AdminDAO implements IAdminDAO{
	
	private IDatabaseConnection connection;

	public AdminDAO() {
		super();
		this.connection = new DatabaseConnectionMySQL();
	}

	@Override
	public boolean Create(Admin newAdmin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Update(Admin admin) {
		// TODO Auto-generated methoUserDAOd stub
		return false;
	}

	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stubr
		return false;
	}

	@Override
	public Set<Admin> Listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin FindWithId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Admin FindWithUsr(String usr) {
		String query;
		Admin admin;
		try {
			System.out.println("Buscando administrador por usuario...");
			
			query = "SELECT id, usr, pwd, status, name, lastName, email, createdAt, updatedAt FROM TPFinalLaboratorioDeComputacionIV.admins WHERE usr = '" + usr + "' and status = 1;";
			this.connection.Connect();
			ResultSet result = this.connection.executeQuery(query);
			if ( result.next() ) {
				admin = new Admin();
				admin.setId(result.getInt("Id"));
				admin.setUsr(result.getString("usr"));
				admin.setPwd(result.getString("pwd"));
				admin.setStatus(result.getBoolean("status"));
				admin.setName(result.getString("name"));
				admin.setLastName(result.getString("lastName"));
				admin.setEmail(result.getString("pwd"));
				admin.setCreatedAt(result.getTimestamp("createdAt").toLocalDateTime());
				admin.setUpdatedAt(result.getTimestamp("updatedAt").toLocalDateTime());
			} else {
				admin = null;
			}
			return admin; 
			
		} catch (Exception e) {
			System.out.println("Falló al buscar el administrador por usuario...");
			e.printStackTrace();
			return null;
		} finally {
			this.connection.Disconnect();
		}
	}

	@Override
	public Admin FindWithName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
