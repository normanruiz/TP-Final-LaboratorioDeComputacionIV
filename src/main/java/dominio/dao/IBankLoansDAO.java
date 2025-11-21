package dominio.dao;

import java.util.ArrayList;

import dominio.entity.BankLoans;

public interface IBankLoansDAO {
	
	
	public boolean Create(int clientId, BankLoans newBankLoans);
	
	public boolean Authorized(int id);
	
	public boolean Refused(int id);
	
	public ArrayList<BankLoans> ListPending();
	
	public ArrayList<BankLoans> ListWithClientId(int clientId);
	
	public BankLoans FindWithId(int id);

}
