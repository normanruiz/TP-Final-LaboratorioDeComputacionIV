package dominio.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;

import dominio.entity.BankAccount;
import dominio.entity.BankLoans;
import dominio.entity.Client;
import dominio.entity.Enums.BANKLOANSSTATUS;

public class BankLoansDAO implements IBankLoansDAO{
	
	private IDatabaseConnection connection;
	
	public BankLoansDAO() {
		super();
		this.connection = new DatabaseConnectionMySQL();
	}

	@Override
	public boolean Create(int clientId, BankLoans newBankLoans) {

		System.out.println("Solicitando prestamo bancario...");
		
		String insert;
		int result;
		
		try {
			
			BigDecimal requestedAmount = newBankLoans.getRequestedAmount();
			BigDecimal bankInterest = new BigDecimal("25.00");
			
			BigDecimal bankInterestAux = (bankInterest.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP)).add(new BigDecimal("1"));
			BigDecimal amountWithInterest = requestedAmount.multiply(bankInterestAux);
			System.out.println(amountWithInterest);
			
			int quotas = newBankLoans.getQuotas();
			BigDecimal amountQuota = amountWithInterest.divide(new BigDecimal(quotas), 2, RoundingMode.HALF_UP);
			
			insert = "INSERT INTO TPFinalLaboratorioDeComputacionIV.bankLoans\n"
					+ "( clientId, bankAccountsId, requestedAmount, bankInterest, amountWithInterest, quotas, amountQuota, status, applyDate, approvalDate, updatedAt )\n"
					+ "VALUES( ?, ?, ?, ?, ?, ?, ?, 'PENDING', NOW(), null, NOW() );";
			
				this.connection.Connect();
				this.connection.setPreparedStatement(insert);
				
				this.connection.setParameter(1, clientId);
				this.connection.setParameter(2, newBankLoans.getBankAccount().getId());
				this.connection.setParameter(3, newBankLoans.getRequestedAmount().toString());
				this.connection.setParameter(4, bankInterest.toString());
				this.connection.setParameter(5, amountWithInterest.toString());
				this.connection.setParameter(6, newBankLoans.getQuotas());
				this.connection.setParameter(7, amountQuota.toString());

				result = this.connection.executeUpdate();
				if (result == 1) {
					return true;
				} else {
					return false;
				}			
			
		} catch (Exception e) {
			System.out.println("Falló al intentar dar de alta la salicitud de prestamo...");
			System.out.println(e.getMessage());
			return false;
		} finally {
			this.connection.Disconnect();
		}
	}

	@Override
	public ArrayList<BankLoans> ListWithClientId(int clientId) {
		String query;
		BankLoans bankLoans;
		ArrayList<BankLoans> bankLoansList = new ArrayList<BankLoans>();
		Client client;
		ClientDAO clientDAO;
		BankAccount bankAccount;
		BankAccountDAO bankAccountDAO;
		
		try {
			System.out.println("Obteniendo lista de prestamos bancarios para el usuario ...");
			
			query = "SELECT id, clientId, bankAccountsId, requestedAmount, bankInterest, amountWithInterest, quotas, amountQuota, status, applyDate, approvalDate, updatedAt\n"
					+ "	FROM TPFinalLaboratorioDeComputacionIV.bankLoans\n"
					+ "	WHERE clientId = " + clientId + "\n"
					+ "	ORDER BY applyDate DESC;";
			
			this.connection.Connect();
			ResultSet result = this.connection.executeQuery(query);
			while(result.next()) {
		
				bankLoans = new BankLoans();
				bankLoans.setId(result.getInt("id"));
				
				client = new Client();
				clientDAO = new ClientDAO();
				client = clientDAO.FindWithId(result.getInt("clientId"));
				bankLoans.setClient(client);
				
				bankAccount = new BankAccount();
				bankAccountDAO = new BankAccountDAO();
				bankAccount = bankAccountDAO.FindWithId(result.getInt("bankAccountsId"));
				bankLoans.setBankAccount(bankAccount);
				
				bankLoans.setRequestedAmount(result.getBigDecimal("requestedAmount"));
				bankLoans.setBankInterest(result.getBigDecimal("bankInterest"));
				bankLoans.setAmountWithInterest(result.getBigDecimal("amountWithInterest"));
				bankLoans.setQuotas(result.getInt("quotas"));
				bankLoans.setAmountQuota(result.getBigDecimal("amountQuota"));
				
				String status = result.getString("status");
				if (status.equals("PENDING")) {
					bankLoans.setStatus(BANKLOANSSTATUS.PENDING);					
				} else if (status.equals("AUTHORIZED")) {
					bankLoans.setStatus(BANKLOANSSTATUS.AUTHORIZED);					
				} else if (status.equals("CLOSE")) {
					bankLoans.setStatus(BANKLOANSSTATUS.CLOSE);
				} else if (status.equals("REFUSED")) {
					bankLoans.setStatus(BANKLOANSSTATUS.REFUSED);			
				} else {
					bankLoans.setStatus(null);
				}
				
				//bankLoans.setApplyDate(result.getTimestamp("applyDate").toLocalDateTime());
				//bankLoans.setApprovalDate(result.getTimestamp("approvalDate").toLocalDateTime());
				//bankLoans.setUpdatedAt(result.getTimestamp("updatedAt").toLocalDateTime());
				

				bankLoansList.add(bankLoans);
				
			}
			return bankLoansList;
			
		} catch (Exception e) {
			System.out.println("Falló al intentar obtener los prestamos bancarios del cliente...");
			System.out.println(e.getMessage());
			return null;
		} finally {
			this.connection.Disconnect();
		}
	}

	@Override
	public BankLoans FindWithId(int id) {
		
		String query;
		BankLoans bankLoans;
		Client client;
		ClientDAO clientDAO;
		BankAccount bankAccount;
		BankAccountDAO bankAccountDAO;
		
		try {
			System.out.println("Obteniendo prestamo bancario con id ...");
			
			query = "SELECT id, clientId, bankAccountsId, requestedAmount, bankInterest, amountWithInterest, quotas, amountQuota, status, applyDate, approvalDate, updatedAt\n"
					+ "	FROM TPFinalLaboratorioDeComputacionIV.bankLoans\n"
					+ "	WHERE id = " + id + ";";
			
			this.connection.Connect();
			ResultSet result = this.connection.executeQuery(query);
			result.next();
			
			bankLoans = new BankLoans();
			bankLoans.setId(result.getInt("id"));
			
			client = new Client();
			clientDAO = new ClientDAO();
			client = clientDAO.FindWithId(result.getInt("clientId"));
			bankLoans.setClient(client);
			
			bankAccount = new BankAccount();
			bankAccountDAO = new BankAccountDAO();
			bankAccount = bankAccountDAO.FindWithId(result.getInt("bankAccountsId"));
			bankLoans.setBankAccount(bankAccount);
			
			bankLoans.setRequestedAmount(result.getBigDecimal("requestedAmount"));
			bankLoans.setBankInterest(result.getBigDecimal("bankInterest"));
			bankLoans.setAmountWithInterest(result.getBigDecimal("amountWithInterest"));
			bankLoans.setQuotas(result.getInt("quotas"));
			bankLoans.setAmountQuota(result.getBigDecimal("amountQuota"));
			
			String status = result.getString("status");
			if (status.equals("PENDING")) {
				bankLoans.setStatus(BANKLOANSSTATUS.PENDING);					
			} else if (status.equals("AUTHORIZED")) {
				bankLoans.setStatus(BANKLOANSSTATUS.AUTHORIZED);					
			} else if (status.equals("CLOSE")) {
				bankLoans.setStatus(BANKLOANSSTATUS.CLOSE);
			} else if (status.equals("REFUSED")) {
				bankLoans.setStatus(BANKLOANSSTATUS.REFUSED);			
			} else {
				bankLoans.setStatus(null);
			}
			
			//bankLoans.setApplyDate(result.getTimestamp("applyDate").toLocalDateTime());
			//bankLoans.setApprovalDate(result.getTimestamp("approvalDate").toLocalDateTime());
			//bankLoans.setUpdatedAt(result.getTimestamp("updatedAt").toLocalDateTime());
				
			return bankLoans;
			
		} catch (Exception e) {
			System.out.println("Falló al intentar obtener el prestamo bancario con id...");
			System.out.println(e.getMessage());
			return null;
		} finally {
			this.connection.Disconnect();
		}
		
	}

	@Override
	public boolean Authorized(int id) {

		System.out.println("Autorizando solicitud de prestamo bancario...");
		
		String update;
		int result;
		
		try {
			
			update = "UPDATE TPFinalLaboratorioDeComputacionIV.bankLoans\n"
					+ "SET  status='REFUSED', updatedAt=NOW()\n"
					+ "WHERE id=?;";
			
				this.connection.Connect();
				this.connection.setPreparedStatement(update);
				 
				this.connection.setParameter(1, id);


				result = this.connection.executeUpdate();
				if (result == 1) {
					return true;
				} else {
					return false;
				}			
			
		} catch (Exception e) {
			System.out.println("Falló al intentar autorizar la salicitud de prestamo...");
			System.out.println(e.getMessage());
			return false;
		} finally {
			this.connection.Disconnect();
		}
	}

	@Override
	public boolean Refused(int id) {

		System.out.println("Rechazando solicitud de prestamo bancario...");
		
		String update;
		int result;
		
		try {
			
			update = "UPDATE TPFinalLaboratorioDeComputacionIV.bankLoans\n"
					+ "SET  status='REFUSED', updatedAt=NOW()\n"
					+ "WHERE id=?;";
			
				this.connection.Connect();
				this.connection.setPreparedStatement(update);
				
				this.connection.setParameter(1, id);


				result = this.connection.executeUpdate();
				if (result == 1) {
					return true;
				} else {
					return false;
				}			
			
		} catch (Exception e) {
			System.out.println("Falló al intentar rechazar la salicitud de prestamo...");
			System.out.println(e.getMessage());
			return false;
		} finally {
			this.connection.Disconnect();
		}
	}

	@Override
	public ArrayList<BankLoans> ListPending() {
		String query;
		BankLoans bankLoans;
		ArrayList<BankLoans> bankLoansList = new ArrayList<BankLoans>();
		Client client;
		ClientDAO clientDAO;
		BankAccount bankAccount;
		BankAccountDAO bankAccountDAO;
		
		try {
			System.out.println("Obteniendo lista de prestamos bancarios pendientes ...");
			
			query = "SELECT id, clientId, bankAccountsId, requestedAmount, bankInterest, amountWithInterest, quotas, amountQuota, status, applyDate, approvalDate, updatedAt\n"
					+ "	FROM TPFinalLaboratorioDeComputacionIV.bankLoans\n"
					+ "	WHERE status = 'PENDING'\n"
					+ "	ORDER BY applyDate DESC;";
			
			this.connection.Connect();
			ResultSet result = this.connection.executeQuery(query);
			while(result.next()) {
		
				bankLoans = new BankLoans();
				bankLoans.setId(result.getInt("id"));
				
				client = new Client();
				clientDAO = new ClientDAO();
				client = clientDAO.FindWithId(result.getInt("clientId"));
				bankLoans.setClient(client);
				
				
				bankAccount = new BankAccount();
				bankAccountDAO = new BankAccountDAO();
				bankAccount = bankAccountDAO.FindWithId(result.getInt("bankAccountsId"));
				bankLoans.setBankAccount(bankAccount);
				
				bankLoans.setRequestedAmount(result.getBigDecimal("requestedAmount"));
				bankLoans.setBankInterest(result.getBigDecimal("bankInterest"));
				bankLoans.setAmountWithInterest(result.getBigDecimal("amountWithInterest"));
				bankLoans.setQuotas(result.getInt("quotas"));
				bankLoans.setAmountQuota(result.getBigDecimal("amountQuota"));
				String status = result.getString("status");
				if (status.equals("PENDING")) {
					bankLoans.setStatus(BANKLOANSSTATUS.PENDING);					
				} else if (status.equals("AUTHORIZED")) {
					bankLoans.setStatus(BANKLOANSSTATUS.AUTHORIZED);					
				} else if (status.equals("CLOSE")) {
					bankLoans.setStatus(BANKLOANSSTATUS.CLOSE);
				} else if (status.equals("REFUSED")) {
					bankLoans.setStatus(BANKLOANSSTATUS.REFUSED);			
				} else {
					bankLoans.setStatus(null);
				}
				//bankLoans.setApplyDate(result.getTimestamp("applyDate").toLocalDateTime());
				//bankLoans.setApprovalDate(result.getTimestamp("approvalDate").toLocalDateTime());
				//bankLoans.setUpdatedAt(result.getTimestamp("updatedAt").toLocalDateTime());
				

				bankLoansList.add(bankLoans);
				
			}
			return bankLoansList;
			
		} catch (Exception e) {
			System.out.println("Falló al intentar obtener los prestamos bancarios pendientes...");
			System.out.println(e.getMessage());
			return null;
		} finally {
			this.connection.Disconnect();
		}
	}
	
	

}
