package dominio.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import dominio.entity.BankAccount;
import dominio.entity.Enums.BANKACCOUNTTYPE;
import exceptions.AcumulateExceededException;
import exceptions.AcumulatedException;

public class BankAccountDAO implements IBankAccountDAO{
	
	private IDatabaseConnection connection;

	public BankAccountDAO() {
		super();
		this.connection = new DatabaseConnectionMySQL();
	}

	@Override
	public boolean Create( int clientId, BANKACCOUNTTYPE type ) throws AcumulatedException, AcumulateExceededException {
		
		String call;
		int count;
		String CBU;
		String accountNumber;
		
		try {
			System.out.println("Creando cuenta bancaria...");
			
			count = accumulated(clientId);
			
			if ( count == -1 ) {
				throw new AcumulatedException();
			}
			if ( count >= 3 ) {
				throw new AcumulateExceededException();
			}
			
			call = "CALL TPFinalLaboratorioDeComputacionIV.AddBankAccount( ?, ?, ?, ? );";
				this.connection.Connect();
				this.connection.setPrepareCall(call);
				this.connection.setParameterPC(1, clientId);
				this.connection.setParameterPC(2, type.toString());
				accountNumber = accountNumberGenerate();
				this.connection.setParameterPC(3, accountNumber);
				CBU = CBUGenerate(accountNumber);
				this.connection.setParameterPC(4, CBU);
				this.connection.execute();
				return true;

		} catch (Exception e) {
			System.out.println("Falló al intentar crear la cuenta bancaria...");
			System.out.println(e.getMessage());
			return false;
		} finally {
			this.connection.Disconnect();
		}
	}

	@Override
	public boolean Update(BankAccount modifyBankAccount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<BankAccount> List() {
		System.out.println("Obteniendo todas las cuentas bancarias...");

		String query;
		BankAccount bankAccount;
		ArrayList<BankAccount> bankAccountsList = new ArrayList<BankAccount>();
		
		try {
			query = "SELECT id, clientId, bankAccountType, accountNumber, CBU, saldo, createdAt, updatedAt, status\n"
					+ "	FROM TPFinalLaboratorioDeComputacionIV.bankAccounts\n"
					+ "	WHERE status = 1;";
			this.connection.Connect();
			ResultSet result = this.connection.executeQuery(query);
			while(result.next()) {
		
				bankAccount = new BankAccount();
				bankAccount.setId(result.getInt("id"));
				bankAccount.setClientId(result.getInt("clientId"));
				bankAccount.setBankAccountType(result.getString("bankAccountType").equals("SAVINGSBANK") ? BANKACCOUNTTYPE.SAVINGSBANK : BANKACCOUNTTYPE.CURRENTACCOUNT );
				bankAccount.setAccountNumber(result.getString("accountNumber"));
				bankAccount.setCBU(result.getString("CBU"));
				bankAccount.setSaldo(result.getBigDecimal("saldo"));
				bankAccount.setStatus(result.getBoolean("status"));		
				bankAccount.setCreatedAt(result.getTimestamp("createdAt").toLocalDateTime());
				bankAccount.setUpdatedAt(result.getTimestamp("updatedAt").toLocalDateTime());
				bankAccountsList.add(bankAccount);
				
			}
			return bankAccountsList;
			
		} catch (Exception e) {
			System.out.println("Falló al lista las cuentas bancarias...");
			System.out.println(e.getMessage());
			return null;
		} finally {
			this.connection.Disconnect();
		}
	}
	
	@Override
	public ArrayList<BankAccount> ListWithClientId(int clientId) {
		System.out.println("Obteniendo las cuentas bancarias del cliente...");

		String query;
		BankAccount bankAccount;
		ArrayList<BankAccount> bankAccountsList = new ArrayList<BankAccount>();
		
		try {
			query = "SELECT id, clientId, bankAccountType, accountNumber, CBU, saldo, createdAt, updatedAt, status\n"
					+ "	FROM TPFinalLaboratorioDeComputacionIV.bankAccounts\n"
					+ "	WHERE status = 1 AND clientId = " + clientId + ";";
			this.connection.Connect();
			ResultSet result = this.connection.executeQuery(query);
			while(result.next()) {
		
				bankAccount = new BankAccount();
				bankAccount.setId(result.getInt("id"));
				bankAccount.setClientId(result.getInt("clientId"));
				bankAccount.setBankAccountType(result.getString("bankAccountType").equals("SAVINGSBANK") ? BANKACCOUNTTYPE.SAVINGSBANK : BANKACCOUNTTYPE.CURRENTACCOUNT );
				bankAccount.setAccountNumber(result.getString("accountNumber"));
				bankAccount.setCBU(result.getString("CBU"));
				bankAccount.setSaldo(result.getBigDecimal("saldo"));
				bankAccount.setStatus(result.getBoolean("status"));		
				bankAccount.setCreatedAt(result.getTimestamp("createdAt").toLocalDateTime());
				bankAccount.setUpdatedAt(result.getTimestamp("updatedAt").toLocalDateTime());
				bankAccountsList.add(bankAccount);
				
			}
			return bankAccountsList;
			
		} catch (Exception e) {
			System.out.println("Falló al lista las cuentas bancarias del cliente...");
			System.out.println(e.getMessage());
			return null;
		} finally {
			this.connection.Disconnect();
		}
	}

	@Override
	public BankAccount FindWithId(int id) {
		
		System.out.println("Obteniendo cuenta bancaria por id...");
		
		String query;
		BankAccount bankAccount;
		
		try {
			
			query = "SELECT id, clientId, bankAccountType, accountNumber, CBU, saldo, createdAt, updatedAt, status\n"
					+ "	FROM TPFinalLaboratorioDeComputacionIV.bankAccounts\n"
					+ "	WHERE id = " + id + ";";

			this.connection.Connect();
			ResultSet result = this.connection.executeQuery(query);
			result.next();
			bankAccount = new BankAccount();
			bankAccount.setId(result.getInt("id"));
			bankAccount.setClientId(result.getInt("clientId"));
			bankAccount.setBankAccountType(result.getString("bankAccountType").equals("SAVINGSBANK") ? BANKACCOUNTTYPE.SAVINGSBANK : BANKACCOUNTTYPE.CURRENTACCOUNT );
			bankAccount.setAccountNumber(result.getString("accountNumber"));
			bankAccount.setCBU(result.getString("CBU"));
			bankAccount.setSaldo(result.getBigDecimal("saldo"));
			bankAccount.setStatus(result.getBoolean("status"));		
			bankAccount.setCreatedAt(result.getTimestamp("createdAt").toLocalDateTime());
			bankAccount.setUpdatedAt(result.getTimestamp("updatedAt").toLocalDateTime());
				
			return bankAccount;
			
		} catch (Exception e) {
			
			System.out.println("Falló al intentar obtener cuenta bancaria por id...");
			System.out.println(e.getMessage());
			return null;
			
		} finally {
			
			this.connection.Disconnect();
		
		}
	}
	
	private int accumulated(int clientId) {
		String query;
		int accumulated;
		try {
			System.out.println("Contar las cuentas bancarias del cliente...");
			
			query = "SELECT COUNT(id) as 'accumulated'\n"
					+ "	FROM TPFinalLaboratorioDeComputacionIV.bankAccounts\n"
					+ "	WHERE status = 1 and clientId = " + clientId + ";";
			
			this.connection.Connect();
			ResultSet result = this.connection.executeQuery(query);
			if (result.next()) {
				
				accumulated = result.getInt("accumulated");

			} else {
				accumulated = -1;
			}
			return accumulated;
			
			
		} catch (Exception e) {
			System.out.println("Falló al intentar contar las cuentas bancarias del cliente...");
			System.out.println(e.getMessage());
			return -1;
		} finally {
			this.connection.Disconnect();
		}
	}
	
	private String accountNumberGenerate() {
		Random random = new Random();
        StringBuilder sb = new StringBuilder(13);
        for (int i = 0; i < 13; i++) {
            sb.append(random.nextInt(10));
        }
		return sb.toString();
	}	
	
	private String CBUGenerate(String accountNumber) {
		String CBU;
		String bankCode = "017";
		String branchOfficeCode = "0052";
		String checkDigitHeader = "4";
		String checkDigitbody = "2";
		CBU = bankCode + branchOfficeCode + checkDigitHeader + accountNumber + checkDigitbody;
		return CBU;
	}

}
