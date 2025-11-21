package dominio.dao;

import java.util.ArrayList;

import dominio.entity.BankMovement;

public interface IBankMovementDAO {

	public boolean Create( BankMovement newBankMovement );
	
	public ArrayList<BankMovement> List(int clientId, int bankAccountId );
	
}
