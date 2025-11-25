package dominio.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankLoansPayments {
	
	private int id;
	private BankLoans bankLoans;
	private Client client;
	private int quotaNumber;
	private BigDecimal amountQuota;
	private boolean paid;
	private LocalDateTime paymentDate;
	
	public BankLoansPayments(int id, dominio.entity.BankLoans bankLoans, Client client, int quotaNumber,
			BigDecimal amountQuota, boolean paid, LocalDateTime paymentDate) {
		super();
		this.id = id;
		this.bankLoans = bankLoans;
		this.client = client;
		this.quotaNumber = quotaNumber;
		this.amountQuota = amountQuota;
		this.paid = paid;
		this.paymentDate = paymentDate;
	}

	public BankLoansPayments() {
		this(-1, null, null, 0, new BigDecimal("0.00"), false, null);
	}
	
	public BankLoansPayments(BankLoansPayments bankLoansPayments) {
		super();
		this.id = bankLoansPayments.id;
		this.bankLoans = bankLoansPayments.bankLoans;
		this.client = bankLoansPayments.client;
		this.quotaNumber = bankLoansPayments.quotaNumber;
		this.amountQuota = bankLoansPayments.amountQuota;
		this.paid = bankLoansPayments.paid;
		this.paymentDate = bankLoansPayments.paymentDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BankLoans getBankLoans() {
		return bankLoans;
	}

	public void setBankLoans(BankLoans bankLoans) {
		this.bankLoans = bankLoans;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getQuotaNumber() {
		return quotaNumber;
	}

	public void setQuotaNumber(int quotaNumber) {
		this.quotaNumber = quotaNumber;
	}

	public BigDecimal getAmountQuota() {
		return amountQuota;
	}

	public void setAmountQuota(BigDecimal amountQuota) {
		this.amountQuota = amountQuota;
	}

	public boolean getPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
	
}
