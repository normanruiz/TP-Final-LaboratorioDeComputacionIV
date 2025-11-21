package dominio.dao;

import java.util.ArrayList;

import dominio.entity.BankAccount;
import dominio.entity.Enums.BANKACCOUNTTYPE;

public interface IBankAccountDAO {
	
	public boolean Create(int clientId, BANKACCOUNTTYPE type);
	
	public boolean Update(BankAccount modifyBankAccount);
	
	public boolean Delete(int id);
	
	public ArrayList<BankAccount> List();
	
	public ArrayList<BankAccount> ListWithClientId(int clientId);
	
	public BankAccount FindWithId(int id);

}
