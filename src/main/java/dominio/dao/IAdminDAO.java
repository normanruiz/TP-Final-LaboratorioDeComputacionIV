package dominio.dao;

import java.util.Set;

import dominio.entity.Admin;

public interface IAdminDAO {
	
	public boolean Create(Admin newAdmin);
	
	public boolean Update(Admin admin);
	
	public boolean Delete(int id);
	
	public Set<Admin> Listar();
	
	public Admin FindWithId(int id);
	
	public Admin FindWithUsr(String usr);
	
	public Admin FindWithName(String name);

}
