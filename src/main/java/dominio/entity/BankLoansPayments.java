package dominio.entity;

import java.time.LocalDateTime;

public class BankLoansPayments {
	
	private int id;
	private double amount;
	private short quotaNumber;
	private short outstandingQuotas;
	private LocalDateTime paymentDate;
	
	public BankLoansPayments( int id, double amount, short quotaNumber, short outstandingQuotas, LocalDateTime paymentDate ) {
		super();
		this.id = id;
		this.amount = amount;
		this.quotaNumber = quotaNumber;
		this.outstandingQuotas = outstandingQuotas;
		this.paymentDate = paymentDate;
	}

	public BankLoansPayments() {
		this( -1, 0.0, ( short )-1, ( short )-1, null );
	}
	
	public BankLoansPayments( BankLoansPayments bankLoansPayments ) {
		super();
		this.id = bankLoansPayments.id;
		this.amount = bankLoansPayments.amount;
		this.quotaNumber = bankLoansPayments.quotaNumber;
		this.outstandingQuotas = bankLoansPayments.outstandingQuotas;
		this.paymentDate = bankLoansPayments.paymentDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public short getQuotaNumber() {
		return quotaNumber;
	}

	public void setQuotaNumber(short quotaNumber) {
		this.quotaNumber = quotaNumber;
	}

	public short getOutstandingQuotas() {
		return outstandingQuotas;
	}

	public void setOutstandingQuotas(short outstandingQuotas) {
		this.outstandingQuotas = outstandingQuotas;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
	
}
