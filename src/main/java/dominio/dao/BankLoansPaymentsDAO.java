package dominio.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dominio.entity.BankLoans;
import dominio.entity.BankLoansPayments;
import dominio.entity.Client;

public class BankLoansPaymentsDAO implements IBankLoansPaymentsDAO {
	
	private IDatabaseConnection connection;
	
	public BankLoansPaymentsDAO() {
		super();
		this.connection = new DatabaseConnectionMySQL();
	}

	@Override
	public boolean Pay(int idBankLoansPayments, int idBankAccount) {

		System.out.println("Efectuando pago de cuota de prestamo bancario...");

		String call;
		
		try {
			
			call = "CALL TPFinalLaboratorioDeComputacionIV.BankLoanQuotaPay( " + idBankLoansPayments + ", " + idBankAccount + " )";
			this.connection.Connect();
			this.connection.setPrepareCall(call);
			this.connection.setParameterPC(1, idBankLoansPayments);
			this.connection.setParameterPC(1, idBankAccount);
			this.connection.execute();
			return true;
			
		} catch (Exception e) {
			System.out.println("Falló al intentar realizar el pago de la cuota...");
			System.out.println(e.getMessage());
			return false;
		} finally {
			this.connection.Disconnect();
		}
	}

	@Override
	public ArrayList<BankLoansPayments> ListWithBankLoanId(int bankLoanId) {
		String query;
		BankLoansPayments bankLoansPayments;
		ArrayList<BankLoansPayments> bankLoansPaymentsList = new ArrayList<BankLoansPayments>();
		Client client;
		ClientDAO clientDAO;
		BankLoans bankLoans;
		BankLoansDAO bankLoansDAO;
		
		try {
			System.out.println("Obteniendo chequera de pagos para prestamos...");
			
			query = "SELECT id, bankLoandsId, clientId, quotaNumber, amountQuota, paid, paymentDate\n"
					+ "	FROM TPFinalLaboratorioDeComputacionIV.bankLoansPayments\n"
					+ "	WHERE bankLoandsId = " + bankLoanId + " ORDER BY paid ASC, id ASC;";
			
			this.connection.Connect();
			ResultSet result = this.connection.executeQuery(query);
			while(result.next()) {
		
				bankLoansPayments = new BankLoansPayments();
				bankLoansPayments.setId(result.getInt("id"));

				bankLoans = new BankLoans();
				bankLoansDAO = new BankLoansDAO();
				bankLoans = bankLoansDAO.FindWithId(result.getInt("bankLoandsId"));
				bankLoansPayments.setBankLoans(bankLoans);
				
				client = new Client();
				clientDAO = new ClientDAO();
				client = clientDAO.FindWithId(result.getInt("clientId"));
				bankLoansPayments.setClient(client);
				
				bankLoansPayments.setQuotaNumber(result.getInt("quotaNumber"));
				bankLoansPayments.setAmountQuota(result.getBigDecimal("amountQuota"));
				bankLoansPayments.setPaid(result.getBoolean("paid"));
		
				if ( result.getTimestamp("paymentDate") != null ) {
					bankLoansPayments.setPaymentDate(result.getTimestamp("paymentDate").toLocalDateTime());
				} else {
					bankLoansPayments.setPaymentDate(null);
				}
				
				bankLoansPaymentsList.add(bankLoansPayments);
				
			}
			System.out.println();
			return bankLoansPaymentsList;
			
		} catch (Exception e) {
			System.out.println("Falló al intentar obtener la chequera de pagos para prestamos...");
			System.out.println(e.getMessage());
			return null;
		} finally {
			this.connection.Disconnect();
		}
	}

}
