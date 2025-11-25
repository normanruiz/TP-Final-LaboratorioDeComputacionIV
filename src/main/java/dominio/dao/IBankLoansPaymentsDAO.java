package dominio.dao;

import java.util.ArrayList;

import dominio.entity.BankLoansPayments;


public interface IBankLoansPaymentsDAO {
	
	public boolean Pay(int idBankLoansPayments, int idBankAccount);

	public ArrayList<BankLoansPayments> ListWithBankLoanId(int bankLoanId);

}
