package dominio.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dominio.entity.BankAccount;
import dominio.entity.BankMovement;
import dominio.entity.Client;
import dominio.entity.Enums.MOVEMENTSTYPE;;

public class BankMovementDAO implements IBankMovementDAO {
	
	private IDatabaseConnection connection;
	
	public BankMovementDAO() {
		super();
		this.connection = new DatabaseConnectionMySQL();
	}

	@Override
	public boolean Create(BankMovement newBankMovement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<BankMovement> List(int clientId, int bankAccountId) {

		String query;
		ArrayList<BankMovement> bankBankMovementList = new ArrayList<BankMovement>();
		BankMovement bankMovement;
		Client client;
		ClientDAO clientDAO;
		BankAccount bankAccount;
		BankAccountDAO bankAccountDAO;
		
		try {
			System.out.println("Obteniendo lista de movimientos bancarios de la cuenta para el usuario ...");
			
			query = "SELECT id, clientId, bankAccountsId, amount, typeMovements, detail, createdAt\n"
					+ "	FROM TPFinalLaboratorioDeComputacionIV.movements\n"
					+ "	WHERE clientId = " + clientId + " AND bankAccountsId = " + bankAccountId + " ORDER BY id DESC;";
			
			this.connection.Connect();
			ResultSet result = this.connection.executeQuery(query);
			while(result.next()) {
		
				bankMovement = new BankMovement();
				bankMovement.setId(result.getInt("id"));
				
				client = new Client();
				clientDAO = new ClientDAO();
				client = clientDAO.FindWithId(result.getInt("clientId"));
				bankMovement.setClient(client);
				
				bankAccount = new BankAccount();
				bankAccountDAO = new BankAccountDAO();
				bankAccount = bankAccountDAO.FindWithId(result.getInt("bankAccountsId"));
				bankMovement.setBankAccount(bankAccount);
				
				bankMovement.setAmount(result.getBigDecimal("Amount"));
				
				String typeMovements = result.getString("typeMovements");
				if (typeMovements.equals("CREATEDBANKACCOUNT")) {
					bankMovement.setTypeMovements(MOVEMENTSTYPE.CREATEDBANKACCOUNT);					
				} else if (typeMovements.equals("CREATEDBANKLOAN")) {
					bankMovement.setTypeMovements(MOVEMENTSTYPE.CREATEDBANKLOAN);					
				} else if (typeMovements.equals("PAYMENTBANKLOAN")) {
					bankMovement.setTypeMovements(MOVEMENTSTYPE.PAYMENTBANKLOAN);
				} else if (typeMovements.equals("TRANSFER")) {
					bankMovement.setTypeMovements(MOVEMENTSTYPE.TRANSFER);			
				} else {
					bankMovement.setTypeMovements(null);
				}
				
				bankMovement.setDetail(result.getString("detail"));
				bankMovement.setCreatedAt(result.getTimestamp("createdAt").toLocalDateTime());
				
				bankBankMovementList.add(bankMovement);
				
			}
			return bankBankMovementList;
			
		} catch (Exception e) {
			System.out.println("Falló al intentar obtener los movimientos bancarios...");
			System.out.println(e.getMessage());
			return null;
		} finally {
			this.connection.Disconnect();
		}
		
	}

}
