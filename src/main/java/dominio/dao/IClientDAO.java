package dominio.dao;

import java.util.ArrayList;

import dominio.entity.Client;

public interface IClientDAO {
	
	public boolean Create(Client newClient);
	
	public boolean Update(Client modifyClient);
	
	public boolean Delete(int id);
	
	public ArrayList<Client> Listar();
	
	public Client FindWithId(int id);
	
	public Client FindWithDNI(String client);
	
	public Client FindWithUsr(String usr);

}
